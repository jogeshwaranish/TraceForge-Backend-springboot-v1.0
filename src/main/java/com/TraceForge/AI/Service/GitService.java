package com.TraceForge.AI.Service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class GitService {
    private final Git git;

    public GitService(File filepath) throws IOException {
        this.git = Git.open(filepath);
    }

    public void printCommits() throws GitAPIException {
        String filename = "generated_readme.md";
        Iterable<RevCommit> commits = git.log().call();
        GeminiService geminiService = new GeminiService();
        String output_md = "";

        for (RevCommit commit : commits) {
            String codeSnapshot = readCommitCode(commit.getTree());
            String commitmsg = commit.name() + commit.getShortMessage() + codeSnapshot;
            output_md = geminiService.generateMarkdown(commitmsg);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(output_md);
            System.out.println("Successfully wrote to the file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        System.out.println(output_md);

    }

    private String readCommitCode(RevTree tree) {
        StringBuilder codeSnapshot = new StringBuilder();

        try (TreeWalk treeWalk = new TreeWalk(git.getRepository())) {
            treeWalk.addTree(tree);
            treeWalk.setRecursive(true);

            while (treeWalk.next()) {
                ObjectId objectId = treeWalk.getObjectId(0);
                byte[] bytes = git.getRepository().open(objectId).getBytes();
                codeSnapshot.append(new String(bytes, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return codeSnapshot.toString();
    }
}
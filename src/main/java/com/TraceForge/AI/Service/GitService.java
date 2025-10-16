package com.TraceForge.AI.Service;

import lombok.NoArgsConstructor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
@NoArgsConstructor
public class GitService {
    Git git;

    {
        try {
            git = Git.open(new File("/home/anishbellamkonda/Downloads/Expense_tracker/.git"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void oldCommits() throws GitAPIException {
        Iterable<RevCommit> commits = git.log().call();
        for (RevCommit commit : commits) {
            System.out.println(commit.getName());
            System.out.println(commit.getShortMessage());
            System.out.println(commit.getAuthorIdent().getName());
            System.out.println(commit.getAuthorIdent().getEmailAddress());
        }

    }

}

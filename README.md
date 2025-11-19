# TraceForge Backend

Spring Boot microservice that powers the TraceForge desktop client. It ingests a local Git repository path, produces AI-generated Markdown summaries for its commit history, and exposes the generated files through a small REST surface.

## Architecture
- **Framework**: Spring Boot 3 (Java 17) with Web + Data JPA + Redis starters.
- **AI layer**: `GeminiService` wraps Google GenAI (`gemini-2.5-flash`). Prompts are constructed from each commit’s metadata plus a code snapshot collected via JGit.
- **Git ingestion**: `GitService` opens any provided repo path, walks every commit, and emits Markdown plus an on-disk `generated_readme.md`.
- **Persistence**: `repositoryService` stores `document` entities in Redis (configured via `@RedisHash`). Each row tracks the repo name, markdown body, and local file path so the Flutter UI can later open it directly.
- **API surface**: `controller` exposes endpoints for generating docs, retrieving the markdown by id, returning the repo list, and fetching saved file paths.

```
Client → POST /generateMarkDown → GitService + GeminiService → repositoryService/Redis
Client → GET /getList → repositoryService → Redis → repo metadata
Client → POST /getMarkDown (identity header) → cached markdown string
```

## Prerequisites
- Java 17+
- Maven 3.9+
- Redis instance (cloud or local). Default config points to `redis-17154.c74.us-east-1-4.ec2.cloud.redislabs.com:17154`.
- Google AI Studio API key with access to `gemini-2.5-flash`.

## Environment Variables
Create an `.env` file in the project root (not committed) with:

```
GOOGLE_API_KEY=your-google-genai-key
redis_password=your-redis-password
```

The Dotenv loader also supports additional keys if you want to parameterize hostnames, ports, or switch models.

## Running Locally
1. **Install dependencies**
   ```bash
   cd /home/anishbellamkonda/Downloads/AI
   mvn clean install
   ```
2. **Start Redis**  
   - Use the managed endpoint referenced in `config.java`, or  
   - Run a local instance and update `redisStandaloneConfiguration` (host, port, credentials).
3. **Launch the service**
   ```bash
   mvn spring-boot:run
   ```
   The app binds to `http://localhost:8080` by default, matching the Flutter client’s expectations.
4. **(Optional) Build an executable jar**
   ```bash
   mvn -DskipTests package
   java -jar target/AI-0.0.1-SNAPSHOT.jar
   ```

## API Reference
| Method | Path | Description | Notes |
| ------ | ---- | ----------- | ----- |
| `POST` | `/generateMarkDown/` | Accepts a JSON body `{ "path": "/absolute/path/to/git/repo" }`, streams the repo’s commit history into Gemini, saves the resulting markdown, and returns it directly. | Requires filesystem access to the provided path on the server host. |
| `POST` | `/getMarkDown` | Returns the stored markdown for a document. Supply the numeric `id` via the `identity` header. | Used by the Docs screen to render markdown inline. |
| `GET` | `/getList` | Lists every stored `document` object (id, repo name, file path). | Powers the front-end’s catalog. |
| `GET` | `/getPath` | Supply `repo_name` header; returns the saved file path on disk. | Useful if another client needs to open the generated file locally. |

### Sample Request
```bash
curl -X POST http://localhost:8080/generateMarkDown/ \
  -H "Content-Type: application/json" \
  -d '{"path":"/path/to/your/repo"}'
```

## Development Tips
- **Rate limits**: Gemini requests are issued per commit. For large repos, consider throttling or aggregating commits before hitting production quotas.
- **Error handling**: `GitService` currently processes every commit sequentially and overwrites `generated_readme.md`. You can extend it to keep per-commit context or skip binary blobs in `TreeWalk`.
- **Persistence**: `document` mixes JPA and Redis annotations. If you prefer a relational backing store, swap `@RedisHash` for `@Entity` and configure `spring.datasource.*` in `application.properties`.
- **Testing**: `src/test/java/.../AiApplicationTests.java` is the default Spring Boot smoke test. Add service-level tests to ensure prompt construction and repository lookups behave as expected.

## Connecting from the Flutter Client
Point the UI to this backend (default `localhost:8080`) via:
```
flutter run --dart-define TRACEFORGE_API_BASE_URL=http://localhost:8080
```

Once the service is up, the Flutter generator screen can scan user-specified directories, send repo paths here, and list the generated markdown returned from `/getList`.


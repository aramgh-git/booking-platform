# booking-platform

Kotlin + Spring Boot backend skeleton with CI and auto-deploy wired up.

## 1. One-time local setup (before your first push)

I couldn't reach the network from here, so two things need a local command:

```bash
# generates gradlew, gradlew.bat, and the wrapper jar
gradle wrapper --gradle-version 8.10

git init
git add .
git commit -m "Initial commit: Spring Boot skeleton, CI, Docker"
```

Sanity check it builds:

```bash
./gradlew bootRun
# in another terminal
curl localhost:8080/
```

## 2. Push to GitHub

```bash
gh repo create booking-platform --private --source=. --push
# or manually: create the repo on github.com, then
git remote add origin git@github.com:<you>/booking-platform.git
git branch -M main
git push -u origin main
```

`.github/workflows/ci.yml` runs automatically on every push/PR to `main`: build, test, upload test report. No extra setup needed — it's already in the repo.

## 3. Hosting — pick one

Both Railway and Render watch your GitHub repo directly and redeploy on every push to `main`. Neither needs a custom GitHub Actions deploy step — the Dockerfile is all they need.

### Railway (fastest to set up)
1. railway.app → New Project → Deploy from GitHub repo → select `booking-platform`.
2. Railway detects the `Dockerfile` automatically and builds from it.
3. Settings → Networking → Generate Domain (gives you a public URL).
4. Every push to `main` redeploys automatically — nothing else to configure.
5. Env vars: Variables tab. `PORT` is injected automatically and `application.yml` already reads it.

### Render (more generous free tier for low-traffic apps)
1. render.com → New → Web Service → connect the repo.
2. Runtime: Docker (it'll find the Dockerfile).
3. Auto-Deploy: On Commit (default) — pushes to `main` redeploy automatically.
4. Render also injects `PORT` automatically.

### Hetzner (only if you want a VPS you manage yourself)
More control, more ops work — no managed auto-deploy-on-push out of the box. If you want this route, the usual pattern is: CI builds and pushes a Docker image to GHCR, then a `deploy.yml` job SSHs into the box and runs `docker compose pull && up -d`. Worth doing once the project is past prototype stage; overkill for day one. Say the word and I'll add that workflow.

## Project layout

```
src/main/kotlin/am/bookingplatform/
  Application.kt              # entry point
  controller/HealthController.kt
src/test/kotlin/...ApplicationTests.kt
.github/workflows/ci.yml      # build+test on every push/PR
Dockerfile                    # used by Railway/Render to build & run
```

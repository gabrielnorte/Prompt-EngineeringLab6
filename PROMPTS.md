# Copilot Prompts Log

This file documents the prompts used with Copilot (or a similar assistant) to produce the Java Surveys console application in this repo. Use these prompts to reproduce and refine the app.

1) Initial scaffold prompt

```
Create a simple Java console application called `Project` that implements a Surveys app.
- The app should present a menu (create survey, list surveys, answer survey, show results, save, exit).
- Surveys contain a title and a list of questions.
- Responses are lists of answers (one per question).
- Include simple persistence: save/load surveys to a local file named `surveys.dat`.
- Keep the implementation single-file (Project.java) and runnable with `javac`/`java`.
```

2) Persistence refinement prompt

```
Add serialization-based persistence to the Surveys app: write the `surveys` map and `nextId` to `surveys.dat` using `ObjectOutputStream`, and load them on startup with `ObjectInputStream` if the file exists. Provide user feedback on save/load success or failure.
```

3) Tests and README prompt

```
Add a small test harness `TestSurvey.java` that programmatically creates a survey, records a response, saves to disk, loads back, and verifies the loaded survey exists. Also add README instructions for compiling, running, and running the tests.
```

4) Iteration/cleanup prompts

```
Refactor code for clarity: separate classes `Survey`, `Question`, `SurveyManager`, and `SurveyApp` all in the same file; improve menu loop and input handling; ensure null/empty inputs are handled gracefully.
```

Notes:
- These prompts are examples that guided the implementation. You can refine them to add features (export CSV, web UI, authentication, etc.).
- When recording prompts for your lab submission, include any exact Copilot suggestions you accepted and any follow-up clarifications you provided.

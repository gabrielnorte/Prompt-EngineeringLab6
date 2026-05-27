# Prompt-EngineeringLab6

This repository contains a simple console-based Surveys application implemented in Java for Lab 6. The app lets you create surveys, collect responses, and view results. Surveys are stored to `surveys.dat` when you save or exit.

To compile and run (Java 11+):

```bash
javac Project.java
java Project
```

Or run directly with single-file execution (Java 11+):

```bash
java Project.java
```

The program will present a menu to create, list, answer, and view survey results.

Basic tests
-----------

Run the included test harness to verify save/load and response recording:

```bash
javac Project.java TestSurvey.java
java TestSurvey
```

Prompts and screenshots
-----------------------

The prompts used to generate and refine this project are in `PROMPTS.md`.

A recorded sample interactive session (menu-driven run that creates a demo survey, answers it, shows results, and saves) is captured in `screenshots/session1.txt`.

To view the sample session output:

```bash
cat screenshots/session1.txt
```

Prompts used
------------

Below are the prompts that were used (or that you can use) with Copilot or a similar assistant to generate and refine this project. You can paste your own prompts here if you want the README to contain the exact conversation for submission.

1) Scaffold the app

```text
Create a simple Java console application named Project.
- It should implement a Surveys app with a menu: create survey, list surveys, answer survey, show results, save, exit.
- Keep the implementation in a single file `Project.java` with small classes: `Survey`, `Question`, `SurveyManager`, `SurveyApp`.
- Make it runnable with `javac`/`java` and persist surveys to a file named `surveys.dat`.
```

2) Add persistence

```text
Add serialization-based persistence: when the user saves or exits, write the surveys map and nextId to `surveys.dat` using `ObjectOutputStream`. On startup, load the file if present using `ObjectInputStream` and restore state.
```

3) Improve UX and validation

```text
Improve input handling so blank or malformed input is handled gracefully. Show friendly messages when no surveys exist. Allow creating surveys by entering multiple questions (end with a blank line).
```

4) Add tests

```text
Add a small test harness `TestSurvey.java` that programmatically creates a survey, records a response, saves to disk, loads back, and verifies the loaded survey exists. Print a clear "TESTS PASSED" message on success.
```

5) Refactor and document

```text
Refactor code for clarity: separate responsibilities into classes, keep methods small, and add comments where necessary. Update `README.md` with compile/run/test instructions and a brief description of the prompts used to produce the code.
```

Notes
- Replace the example prompts above with the exact prompts you used for your submission if required.
- If you want me to include the exact Copilot suggestion texts you accepted, paste them here and I'll add them verbatim.


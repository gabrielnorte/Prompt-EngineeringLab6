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


# Lab1-DECIDE
* This is an implementation of a decision-making function, DECIDE(), for a (fictional) anti-ballistic missile system. The program processes radar tracking information and determines whether a missile interceptor should be launched. The decision is based on a combination of 15 predefined conditions referred to as Launch Interceptor Conditions (LICs).
## Requirements
* Java 21+ compatible JDK
## Running
* All steps assume you are in project root
* To build: ./gradlew build (./gradlew.bat build on windows, run clean build to test changes)
* To run: ./gradlew run (./gradlew.bat run on windows)
* To test: ./gradlew test (./gradlew.bat test on windows)
## Workflow
To add a feature or fix something:
1. Make an issue describing it
2. Make a branch of main or relevant branch if applicable
3. Work on the branch and once "done" make sure it builds (run ./gradlew clean build, if windows ./gradlew.bat clean build)
4. Make sure the tests pass when having ran ./gradlew clean build!
5. Push up the changes to your branch and make a PR, do not merge until review has been done
6. Always one commit per PR, commits can be squashed though in case there are several
7. Commit message should be of the form: "feature/fix/doc/refactor: #issue-number Did the thing that makes the PR relevant"
8. If your PR fixes an issue, make sure to [link the issue that you are fixing](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/linking-a-pull-request-to-an-issue)
## Statement of contributions
* Leo Lundberg: FUV and corresponding tests
* Alix Peigue: LIC 5-9 and corresponding tests
* Samer Jameel: LIC 0-4 and corresponding tests
* Adam Fridén Rasmussen: LIC 10-14 and corresponding tests
* Anass Inani: PUM and corresponding tests
* Everyone participated in code reviews to varying degrees
## Statement of Essence
State : Foundations Established

In summary, we have established a common workflow with practices that
are non-negotiable, but some parts remain not clearly formalised like
suggestions towards workflow practices.
- We selected the tools that form the foundation of the way-of-working : Git, Github, Discord.
- Enough practices are agreed by the team to start working : Github workflow, communication on discord, basic code review and code structure
- Non negotiable practices : current PR strategy, git
- The gaps between the needs and the capabilities are understood : some members are new to Github and that needs to be respected.
- The selected practices are integrated and in use : PR workflow and merge strategy.

What are the obstacles to reach the next state? The way of working is missing a few key points :
- It misses a centralized knowledge database where the practices are
documented, currently some practices have been decided through discord,
Github issues and they are not documented elsewhere. 
- There is no documented way to give feedback on the work and practices. 
- If the practices are documented, it will be easier to hold the members in the
group accountable, and the team will be more consistent.


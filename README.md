# Lab1-DECIDE
* (Fictional) Gradle repo containing the Launch Interceptor Programme that decides if missiles should be launched based on input
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
6. Always one commit per PR, commits can be squashed though in case there are more
7. Commit message should be of the form: "feature/fix/doc/refactor: #issue-number Did the thing that makes the PR relevant"
## Statement of contributions
* Leo Lundberg: FUV and corresponding tests
* Alix Peigue: LIC 5-9 and corresponding tests
* Samer Jameel: LIC 0-4 and corresponding tests
* Adam Fridén Rasmussen: LIC 10-14 and corresponding tests
* Anass Inani: PUM and corresponding tests
* Everyone participated in code reviews to varying degrees


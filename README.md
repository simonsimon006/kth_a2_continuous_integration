<p align="center">
    <a href="https://github.com/simonsimon006/kth_a2_continuous_integration/commits/master">
        <img alt="Last Commit" src="https://img.shields.io/github/last-commit/simonsimon006/kth_a2_continuous_integration.svg?style=flat-square&logo=github&logoColor=white">
    </a>
    <a href="https://github.com/simonsimon006/kth_a2_continuous_integration/issues">
        <img alt="Issues" src="https://img.shields.io/github/issues-raw/simonsimon006/kth_a2_continuous_integration.svg?style=flat-square&logo=github&logoColor=white">
    </a>
    <a href="https://github.com/simonsimon006/kth_a2_continuous_integration/pulls">
        <img alt="Pull Requests" src="https://img.shields.io/github/issues-pr-raw/simonsimon006/kth_a2_continuous_integration.svg?style=flat-square&logo=github&logoColor=white">
    </a>
    <a href="https://opensource.org/licenses/Apache-2.0">
        <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg">
    </a>
</p>

# Assignment #2: Continuous Integration
<img src="https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/KTH_Royal_Institute_of_Technology_logo.svg/1200px-KTH_Royal_Institute_of_Technology_logo.svg.png" alt="KTH Logo" align="left" width="90" height="90" style="vertical-align:middle;margin:0px 20px">

This is the repository for *Assignment #2: Continuous Integration* in the course DD2480 Software Engineering Fundamentals at KTH Royal Institute of Technology, Sweden. This project is written in Java and uses Gradle as the build tool.

<span style="color: transparent">

## Table of Contents
- [Assignment #2: Continuous Integration](#assignment-2-continuous-integration)
  - [Table of Contents](#table-of-contents)
  - [Description of the Assignment](#description-of-the-assignment)
  - [How to Run Compilation and Testing](#how-to-run-compilation-and-testing)
  - [Description of Implementation and Unit-Testing](#description-of-implementation-and-unit-testing)
    - [Compilation](#compilation)
    - [Test Execution](#test-execution)
    - [Notification](#notification)
  - [Build List](#build-list)
  - [Statement of Contributions](#statement-of-contributions)
  - [License](#license)

</span>

## Description of the Assignment
The goal of this assignment is to master the core of continuous integration. To achieve this goal, a small continuous integration CI server is implemented. This CI server only contains the core features of continuous integration.

<p align="center">
    <img src="https://thumbs.gfycat.com/MammothAgreeableIchthyostega-size_restricted.gif">
</p>

## How to Run Compilation and Testing
Install Gradle according to [this guide](https://docs.gradle.org/current/samples/sample_building_java_applications.html) and the Java JDK from [here](https://www.oracle.com/java/technologies/downloads/). We can only guarantee support for Gradle v7.6 and Java version 19, although it would likely work with different versions of Java.  

After installing Gradle and Java download the repository and open a terminal or command prompt. Use **cd** to change directory to the same one where the **gradlew.bat** file is. Run, bundle or test the program by using the gradlew tool. Platform specific commands are listed below.

**Linux/OSX:**  
- **Run without bundling:** ./gradlew run  
- **Bundle application:** ./gradlew build  
- **Test application:** ./gradlew test  
  
**Windows:** 
- **Run without bundling:** gradlew run  
- **Bundle application:** gradlew build  
- **Test application:** gradlew test 

To use the program, set up a github webhook with a payload URL indicating the server and its associated port (default 9000). Content type should be application/json. To receive emails, you need to ensure that you do not have your email set to private when you commit. More info [here](https://docs.github.com/en/account-and-profile/setting-up-and-managing-your-personal-account-on-github/managing-email-preferences/setting-your-commit-email-address).

The server will build and test the branch where your latest commit was made and send an email to your **non-private** email address.

## Description of Implementation and Unit-Testing
### Compilation
==[TO BE ADDED: how compilation has been implemented and unit-tested]==

### Test Execution
==[TO BE ADDED: how test execution has been implemented and unit-tested]==

### Notification
==[TO BE ADDED: how notification has been implemented and unit-tested]==

## Build List
==[TO BE ADDED: the students document the build list URL in the README, insert URL below]==
The CI server keeps the history of past builds, see [build list]().

## Statement of Contributions
We are proud of this project and what we have accomplished in such a short amount of time. The team's creativity has been extremely useful in this project since it facilitated overcoming various predicaments that arised during the imlementation of this CI. Even though this project proved to be quite difficult, we are all convinced that it did yield profound knowledge in how CI servers are implemented. 

<a href="https://github.com/simonsimon006">
    <img src="https://avatars.githubusercontent.com/u/1763926?v=4" width="25" height="25" style="vertical-align:middle;margin:0px 10px" alt="Simon Grunwald" align="left"/>
</a>

[Simon Grunwald](https://github.com/simonsimon006)
- Implementation of the handling of the webhook triggers and logging
- Part of combining everything together
- Server Administration

<a href="https://github.com/gustafssonlinnea">
    <img src="https://avatars.githubusercontent.com/u/70338667?v=4" width="25" height="25" style="vertical-align:middle;margin:0px 10px" alt="Linnéa Gustafsson" align="left"/>
</a>

[Linnéa Gustafsson](https://github.com/gustafssonlinnea)
- Managing issues that correspond to the requirements
- Foundation for README

<a href="https://github.com/oscols">
    <img src="https://avatars.githubusercontent.com/u/83168314?v=4" width="25" height="25" style="vertical-align:middle;margin:0px 10px" alt="Oscar Olsson" align="left"/>
</a>
  
[Oscar Olsson](https://github.com/oscols)
- Created project email
- Implemented the EmailSender class

<a href="https://github.com/JesperSlagarp">
    <img src="https://avatars.githubusercontent.com/u/61503896?v=4" width="25" height="25" style="vertical-align:middle;margin:0px 10px" alt="Jesper Slagarp" align="left"/>
</a>

[Jesper Slagarp](https://github.com/JesperSlagarp)
- Code for building and testing programatically
- Did part of combining the different components together

<a href="https://github.com/hansstammler">
    <img src="https://avatars.githubusercontent.com/u/81676569?v=4" width="25" height="25" style="vertical-align:middle;margin:0px 10px" alt="Hans Stammler" align="left"/>
</a>

[Hans Stammler](https://github.com/hansstammler)
- Initialization of gradle project and dependencies
- GitInteractions.java
- General skeleton work and bug fixing

## License
Copyright 2023 Simon Grunwald, Linnéa Gustafsson, Oscar Olsson, Jesper Slagarp & Hans Stammler

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.1

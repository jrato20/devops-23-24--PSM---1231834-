# devops-23-24--PSM---1231834-
# Technical Report for CA1

## Introduction

This is a technical report for de Class Assignment 1, of the DevOps class from the "Switch - Curso de Especialização em Desenvolvimento de Software" made by Simão Campos, student nº 1231859.

The following report is a technical document that provides a detailed account of the steps taken to complete the assignment, including the commands used, the issues encountered, and the solutions implemented. The report is divided into two main sections: the tutorial and the procedures. The tutorial section provides a step-by-step guide to the assignment tasks, while the procedures section presents the commands and actions taken to fulfill the requirements of the assignment.

## 1. Tutorial
The first step in the assignment is to create a new Git repository and set it up for the project. This involves creating a new repository on GitHub, initializing a local repository, adding a README file, and pushing the initial commit to the remote repository.
1. Create a new repository on GitHub
2. Initialize a local repository
3. Add a README file (on gitHub, the following commands are suggested when a repository is created without a README file.
   Issues must be opened in the repository to track the tasks of the assignment. The issues should be referred in the commits.

```bash
echo "devops-23-24--PSM---1231834-" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:username/repository-name.git
git push -u origin main
```
The .gitignore file should be created to avoid the inclusion of unnecessary files in the repository. The following command can be used to create the file.
On the given website the .gitignore file content can be copied and pasted to the file using the nano command.
I recommend to add the .idea directory to the .gitignore file.

```bash  
touch .gitignore
nano .gitignore
```

## Procedures

On the command line or in Git Bash, use de following commands to respond to the tasks of the assignment:

### Overview

The assignment entails several key tasks:

1. Copying the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1.
2. Copy the Basic directory and the "pom.xml" file to the new folder.
3. Committing the changes and pushing them to the repository.
4. Tagging the initial version as v1.1.0 and pushing the tag to the server.
5. Developing a new feature: to add a "jobYears" field to the Employee entity.
6. Creating unit tests for the new field and ensuring validation for integer values only.
7. Debugging the server and client parts of the solution.
8. Committing the new feature, pushing the changes, and tagging the repository as v1.2.0.
9. End part-one with the tag ca1-part1.
10. Developing new features in branches named after the feature. For example, creating a branch named "email-field" to add a new email field to the application.
11. Tag the repository as v1.3.0.
12. Creating a branch called fix-invalid-email. The server should only accept Employees with a valid email (e.g., an email must have the "@" sign).
13. Merging into the master branch and tagging the repository as v1.3.1.
14. At the end of the assignment, marking the repository with the tag ca1-part2.


#### 1. Copying the Tutorial Application

Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1.

```bash
    git clone https://github.com/spring-guides/tut-react-and-spring-data-rest
````
#### 2. Adding the Basic Directory and the _pom_.xml File
```bash
    cp -r tut-react-and-spring-data-rest/basic CA1
    cp tut-react-and-spring-data-rest/pom.xml CA1
    cd CA1
    git init
    git add .
    git commit -m "Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1. Closes #2"
    git push origin main
```
NOTES: It is also valid to copy and paste the basic folder using the system interface.
To remove a file or a folder that was wrongly commited the following command or a variation of ir could be used. It can be verified by the git status command before it is git pushed.

````bash
git rm -df <file or folder>
git status
git push origin main
````

#### 4.Create a new tag v1.1.0 and push it to the server

```bash
    git tag v1.1.0
    git push origin v1.1.0
```

#### 5.6.7. Adding a New Field to the Employee Entity

Add a new field "jobYears" to the Employee entity in the backend: change constructor, getter, and setter methods, and add validation annotations.
The tests directory must be updated with new tests for the "jobYears" field.
The javascript code must be updated to display the new field in the frontend.
The DatabaseLoader class must be updated to include the new field in the initial data.

NOTES: The following problems were felt during this part of the class assignment.
The project should be added manually to maven if the IDE doesn't do it automatically.
The dependencies of the POM file should be updated and the individual libraries of each class should be also added to the POM.
The Java directory created to contain the tests should be marked as tests directory.
After this the individual tests should work.

#### 8.Finalizing and tagging the New Feature

After implementing features and fixes, commit, push, and tag the repository accordingly:

```bash
    git add .
    git commit -m "Feat: New feature to add jobYears new field closes #3"
    git push origin main
    git tag v1.2.0
    git push origin v1.2.0
```

#### 9. Final Tagging
End part one with the tag ca1-part1:

```bash
    git tag ca1-part1
    git push origin ca1-part1
```

#### 10.11. Email adding

Create a new branch to add a new email field to the application. After implementing the feature, merge it into the master branch and tag the repository:

```bash
    # create a new branch
    git checkout -b email-field
    # add the email field to the Employee entity
    git add .
    git commit -m "Feat: new feature email field"
    git push --set-upstream origin email-field
    git checkout main
    git merge --no-ff email-field
    git push origin main
    git commit --amend "Merge branch 'email-field' closes #4"
    git tag v1.3.0
    git push origin v1.3.0
```
#### 12.13. Fixing Invalid Email Issue

Create a new branch to fix the invalid email issue and merge it into the master branch:
A method to validate email addresses should be added to the Employee entity.
Tests should be created to ensure that only valid email addresses are accepted.

```bash
    # create a new branch
    git checkout -b fix-invalid-email
    # fix the issue
    git add .
    git commit -m "creating new branch fix-invalid-email, fixing invalid email and testing it"
    git push --set-upstream origin fix-invalid-email
    git checkout main
    git merge --no-ff fix-invalid-email
    git push origin main
    git commit --amend "Merge branch 'fix-invalid-email' closes #5"
    git tag v1.3.1
    git push origin v1.3.1
```

#### 14. Final Tagging

At the end of the assignment, mark the repository with the tag ca1-part2:

```bash
    git tag ca1-part2
    git push origin ca1-part2
```

## 2. Analysis of an Alternative Version Control Solution: Mercurial SCM
This following section provides an analysis of Mercurial as an alternative version control solution to Git, focusing on its features, differences from Git, and its application to the assignment goals.
It was not implemented in the assignment.

Mercurial, like Git, is a distributed version control system (DVCS) that enables developers to track and manage changes to their codebase. However, there are key differences and similarities between the two, influencing their usage based on project needs and team preferences.

### Comparison to Git

1. **Ease of Use**: Mercurial is often praised for its simplicity and straightforward command set. New users might find Mercurial to be more approachable than Git, which has a steeper learning curve due to its more extensive set of features and commands.

2. **Branching and Merging**: Both Git and Mercurial support branching and merging, but Git's model allows for more flexibility. Git branches are lightweight and can be created, merged, and deleted with ease, which encourages experimenting with new features. Mercurial uses a slightly different approach, where branches are permanent and clones are often used for feature development.

3. **Performance**: Git generally offers better performance for large projects due to its efficient handling of branches and its compressed data format. However, Mercurial provides sufficient performance for most projects and can be simpler to use for basic operations.

4. **Tooling and Integration**: Git has a broader adoption, which means more tools and integrations are available, including popular platforms like GitHub, GitLab, and Bitbucket. Mercurial is supported by many tools as well, but the ecosystem is smaller.

### Applying Mercurial to the Assignment Goals

To achieve the same goals as presented in this assignment using Mercurial, one would follow a similar workflow with some differences in commands and concepts:

1. **Repository Initialization**: To initialize a Mercurial repository, use `hg init` instead of `git init`.

    ```bash
    hg init
    echo "# repository-name" >> README.md
    hg add README.md
    hg commit -m "first commit"
    ```

2. **Pushing Changes**: Mercurial uses `hg push` to send changes to a remote repository, similar to `git push`.

3. **Tagging Versions**: In Mercurial, tags are created with `hg tag <tagname>` and pushed with `hg push --tags`.

4. **Branching for Features and Fixes**: To create a new branch in Mercurial, use `hg branch <branchname>`. Feature development and bug fixes would follow a similar branching model to Git, with the merge process using `hg merge` and commit changes with `hg commit`.

5. **Finalizing with Tags**: Mark the end of the assignment with `hg tag ca1-part2` and push the tag as shown above.
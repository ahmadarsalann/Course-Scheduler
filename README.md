# Course-Scheduler

The program idea belongs to my professor: Ian Finlayson

DataStructure Used:
Graph

The program will take a list of courses and their pre-requisites as input, and print out a topological ordering that one can take the courses in without violating any pre-requisites. If the set of courses can not be taken in any order, you should print out that there is no way to take those courses.

The program will be given the name of an input file on the command line. The file consists of a list of courses with their pre-requisites in the following format:

number-of-courses
course1 number-of-prereqs prereq1 prereq2 ...
course2 number-of-prereqs prereq1 prereq2 ...
...
Each course will be specified with four letters followed by 3 digits. Some courses will of course have no pre-requisites.

# StudentFileService

StudentFileService organizes students by scores such as their GPA which is calculated from their test scores.

## Features

- Takes two .txt files in a certain format (student.txt and courseScore.txt) and sorts them into a result.txt file
- Calculates the GPA for the test scores of each class that a student has
- Combines the scores to an overall GPA per student
- Keeps track of student scores to the student's id
- Sorts by the overall GPA of each student
- Also provides .txt files for sorted students by GPA per discipline
- Result files update from initial .txt file changes

## Technologies Used
- Java

## Installation
- Have two text fields ready to add in the project:

### student.txt
- This .txt file must have the following information in order:
- StudentID, Student Name, Discipline, Year of Study, Gender
- Example of a student.txt file:
<img width="265" height="213" alt="image" src="https://github.com/user-attachments/assets/f90c7793-d474-4a5d-9910-123fd3ebc3b5" />

### courseScore.txt
- This .txt file must have the following information in order:
- CourseID, Course Name, StudentID, Grade, TeacherID
- Example of a courseScore.txt:
<img width="296" height="319" alt="image" src="https://github.com/user-attachments/assets/9edabf12-c8ca-4979-a89a-0af71855b319" />

### IMPORTANT
- The github repository already has a preset student.txt and courseScore.txt file.
- It is not needed to remove the result files as they update from modifications of the original .txt files

## Requirements
- Tested on Java zulu 25.0.1

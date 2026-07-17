import java.io.*;
import java.util.*;

public class FileServiceImpl implements FileServiceI {
    /**
     * This method reads the student information except for the courseList, which will be added later
     * The information is saved through a string array that is split from each line of the .txt file with the delimiter ,
     * A Scanner is used to get the information in order to split it into the information String array.
     * The information in the String array is implicitly cleared after each student as the .split() method returns a new String array. By setting it, the past data is lost
     * The data from the information is saved by creating a Student object and by adding the object to a list
     * ArrayList is used, as there is only adding data, not searching, removing, or inserting data
     * After adding the information to the list, Collections.sort(list) is used. This method basically acts like a Quicksort.
     * The Quicksort worst case is n^2 only when the students are already sorted by Id, which is what we are looking for. Therefore, it is easy to manage it.
     * The average case would be O(nlog(n)) which is efficient for datasets which are not too much. Since the students.txt file has not a lot of data, Quicksort can be used.
     * Mergesort could be a better option, but it is usually used for large datasets. The students.txt file is not a large dataset
     * A try catch is used to get an IOException, more specifically a FileNotFoundException. Printing out the exception explains well the reason for the error
     *
     * @param filename is the filename used for reading the .txt file containing the students information
     * @return linkedList of Student sorted by id (LinkedList better for sorting algorithms)
     */
    @Override
    public List<Student> readStudents(String filename) {
        //ArrayList is used since it only stores data for now
        List<Student> list = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);

            //Students don't have classList yet
            String[] studentInformation;
            while (input.hasNext()) {
                //Splitting the information
                studentInformation = input.nextLine().split(",");

                //Attributing each part of the information to the corresponding student data
                int studentId = Integer.parseInt(studentInformation[0]);
                String studentName = studentInformation[1];
                String discipline = studentInformation[2];
                int yearOfStudy = Integer.parseInt(studentInformation[3]);
                char gender = studentInformation[4].charAt(0);

                //Adding to the list a new Student object with all the information
                list.add(new Student(studentId, studentName, gender, discipline, yearOfStudy));
            }

            //Quick sort the list with Collections.sort(list) O(n*log(n))
            Collections.sort(list);


            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        //List transformed into a linkedList so that the sorting is easier (sorting always needs swapping which needs to remove/swap items
        List<Student> linkedList = new LinkedList<>(list);


        //Sorts them by student ID
        //Collections.sort() is by default a QuickSort --> O(n*log(n)) to O(n^2) (Worst case)
        Collections.sort(linkedList);

        return linkedList;
    }

    /**
     * This method reads the courseScore.txt file and saves its data in a list of CourseScore objects
     * The information is saved through a string array that is split from each line of the .txt file with the delimiter ,
     * A Scanner is used to get the information in order to split it into the scoreInformation String array.
     * The information in the String array is implicitly cleared after each student as the .split() method returns a new String array. By setting it, the past data is lost
     * The data from the information is saved by creating a CourseScore object and by adding the object to a list
     * ArrayList is used, as there is only adding data, not searching, removing, or inserting data
     * After adding the information to the list, Collections.sort(list) is used. This method basically acts like a Quicksort.
     * The Quicksort worst case is n^2 only when the courseScore is already sorted by the student id, which is what we are looking for. Therefore, it is easy to manage it.
     * The average case would be O(nlog(n)) which is efficient for datasets which are not too much. Since the courseScore.txt file has not a lot of data, Quicksort can be used.
     * Mergesort could be a better option, but it is usually used for large datasets. The students.txt file is not a large dataset.
     * A try catch is used to get an IOException, more specifically a FileNotFoundException. Printing out the exception explains well the reason for the error
     *
     * @param filename is the filename used for reading the .txt file containing the courseScore information
     * @return a list of CourseScore objects which are all sorted by student id
     */
    @Override
    public List<CourseScore> readScores(String filename) {
        //ArrayList is used since it only stores data for now
        List<CourseScore> list = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            String[] scoreInformation;

            while (input.hasNextLine()) {
                scoreInformation = input.nextLine().split(",");
                //The information is directly added to the list with the new CourseScore object instead of saving variables
                list.add(new CourseScore(scoreInformation[0], scoreInformation[1], Integer.parseInt(scoreInformation[2]), Double.parseDouble(scoreInformation[3]), scoreInformation[4]));
            }

            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        //Linked list is used for more efficiency when sorting
        LinkedList<CourseScore> linkedList = new LinkedList<>(list);

        //Sorts them by student ID
        //Collections.sort() is by default a QuickSort --> O(n*log(n)) to O(n^2) (Worst case)
        Collections.sort(linkedList);

        return linkedList;
    }

    /**
     * This method writes to the chosen file the student with their GPA as the last element after the last comma in descending order
     * Try catch can catch IOException, more specifically FileNotFoundException. It is explained briefly and clearly when printed out.
     *
     * @param filename is the fileName to write the result for every student
     * @param students is a list of Students
     */
    @Override
    public void writeToFile(String filename, List<Student> students) {
        try {
            File file = new File(filename);
            PrintWriter output = new PrintWriter(file);
            for (Student student : students) {
                //Prints the student in result.txt file
                output.println(student);
            }
            output.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method goes through the students list to get every type of discipline. 1 of each type is added in the disciplines ArrayList
     * This method also prints out the students in the respective discipline with their GPA in descending order of GPA
     * FileWriter is used to keep the data in the .txt file to add onto it with more PrintWriter
     * Although it might take memory, the efficiency of the program is O(n)
     * It first clears up the file by creating a printWriter
     * It then uses FileWriter to save the printing.
     * Try catch is used to get IOException, more specifically FileNotFoundException which is well explained when printed out
     *
     * @param students is a list of Students representing the students that were read from the .txt file
     */
    public void disciplineFileWriting(List<Student> students) {
        List<String> disciplines = new ArrayList<>();
        for (Student student : students) {

            //If statement to get all possible disciplines from students
            if (!(disciplines.contains(student.getDiscipline()))) {
                disciplines.add(student.getDiscipline());
            }
        }


        try {
            FileWriter fileWriter;
            PrintWriter output;

            //Creating the files
            for (String discipline : disciplines) {
                output = new PrintWriter(discipline + ".txt");
                output.close();
            }

            //Adding to the .txt files of each discipline
            for (Student student : students) {
                fileWriter = new FileWriter(student.getDiscipline() + ".txt", true);
                output = new PrintWriter(fileWriter);
                output.println(student);
                output.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method links the courses to the Student's courseList
     * It first checks with Collections.binarySearch the index of the associated studentId in the CourseScore associated object
     * It then checks if the binarySearch gave a negative number (meaning that there was no result). If there was no result, it iterates to the next student
     * Sometimes, the student has multiple classes, but the binarySearch only gets one of them. So, the left and right side of the binarySearch are searched to see if there are others.
     * This binarySearch use works because the courseScores list is sorted by the student id, making it so that if one course is found, the others next to it are the only ones that can be other courses from the student.
     * All the valid CourseScore objects corresponding to the student are added in an ArrayList of student. Since it is only adding, the ArrayList is used.
     * Time complexity is O(nlog(m)) since binary search is log(m) and the student list is iterated (n)
     *
     * @param students     is list of Student objects representing the students in the .txt file
     * @param courseScores is list of CourseScore objects representing the courseScores for students in the other .txt file
     */
    public void linkStudentToClasses(List<Student> students, List<CourseScore> courseScores) {
        List<CourseScore> listOfCourses;
        int studentId;
        int binarySearchIndex;
        int indexBefore;
        int indexAfter;

        for (Student student : students) {
            listOfCourses = new ArrayList<>();
            studentId = student.getId();

            binarySearchIndex = Collections.binarySearch(courseScores, new CourseScore("", "", studentId, 0, ""));

            if (binarySearchIndex >= 0) {
                //Adds the found term already
                listOfCourses.add(courseScores.get(binarySearchIndex));

                //Checks before
                indexBefore = binarySearchIndex - 1;
                while (indexBefore >= 0 && courseScores.get(indexBefore).getStudentId() == studentId) {
                    listOfCourses.add(courseScores.get(indexBefore--));
                }

                //Checks after
                indexAfter = binarySearchIndex - 1;
                while (indexAfter < courseScores.size() && courseScores.get(indexAfter).getStudentId() == studentId) {
                    listOfCourses.add(courseScores.get(indexAfter++));
                }
                student.setListOfCourses(listOfCourses);
            }
        }
    }
}
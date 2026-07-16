package FinalProject;

import java.util.Arrays;
import java.util.List;

public class Student extends Person implements Comparable<Student> {
    private String discipline;
    private int yearOfStudy;
    private List<CourseScore> listOfCourses;
    private double GPA;

    /**
     * This constructor constructs a student without the list of courses
     * The list of courses must be added later, as the specific courses are determined after creating the student
     *
     * @param id          is integer representing the id of a student
     * @param name        is String representing the name of a student
     * @param gender      is character representing the gender of the student (M for male and F for female)
     * @param discipline  is String representing the discipline of the student
     * @param yearOfStudy is integer representing the student's number of years studied
     */
    Student(int id, String name, char gender, String discipline, int yearOfStudy) {
        super(id, name, gender);
        this.discipline = discipline;
        this.yearOfStudy = yearOfStudy;
    }

    /*Setters
    The GPA is not modified by itself. By setting the courseList, the GPA is set according to the list.
    Therefore, GPA is not necessary to get a setter method of its own, as it gets set in another method automatically.
     */

    /**
     * This method sets the discipline of a Student
     *
     * @param discipline is String representing the discipline of a Student
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    /**
     * This method sets the yearOfStudy of a Student
     *
     * @param yearOfStudy is integer representing the student's number of years studied
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * This method sets the listOfCourses of a Student
     * After modifying the courses, the GPA is automatically calculated by calling the calculateGPA() method
     *
     * @param listOfCourses is List of CourseScore objects representing the student's courses
     */
    public void setListOfCourses(List<CourseScore> listOfCourses) {
        this.listOfCourses = listOfCourses;
        calculateGPA();
    }

    //Getters

    /**
     * This method gets the discipline of a Student
     *
     * @return String representing the discipline of a Student
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * This method gets the yearOfStudy of a Student
     *
     * @return integer representing the yearOfStudy of a Student
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * This method gets the listOfCourses of a Student
     *
     * @return a List of CourseScore objects representing the student's courses
     */
    public List<CourseScore> getListOfCourses() {
        return listOfCourses;
    }

    /**
     * This method gets the GPA of the student
     *
     * @return double representing the GPA of the student
     */
    public double getGPA() {
        return GPA;
    }

    //Other methods

    /**
     * This method calculates the GPA
     * It is essentially the setter of the GPA of the student
     * The number is rounded by 2 decimals
     */
    public void calculateGPA() {
        double sum = 0;
        double size = 0;
        double GPA;


        for (CourseScore score : listOfCourses) {
            sum += score.getScore();
            size++;
        }
        //GPA calculation
        GPA = (int) ((sum / size / 25) * 100) / 100.0;


        this.GPA = GPA;
    }

    /**
     * The next 3 methods are key for sorting by GPA, since Student object implements Comparable interface to compare only its id.
     * Therefore, the sorting method used is merge sort, as the .txt file can become very big. Merge sort is the best for big data, so it is ideal to use it in this case.
     * This method gets an array of Student objects and calls the mergeSort() method in order to put the array in descending order
     * The array is then put back into the students list. An array is used and not a list because it is a recursive and it uses System.arraycopy which works well with arrays
     *
     * @param students is a list of Student object representing the students
     */
    public static void sortByGPA(List<Student> students) {
        Student[] studentArray = new Student[students.size()];
        for (int i = 0; i < studentArray.length; i++) {
            studentArray[i] = students.get(i);
        }

        mergeSort(studentArray);

        students.clear();

        students.addAll(Arrays.asList(studentArray));
    }

    /**
     * This method is a recursive method which breaks everything until there are only arrays with 1 student each.
     * The breaking algorithm is called through the mergeSort method
     * After breaking to 1 element each array, two are compared to merge together into 1 array
     * This merging is repeated until the array is fully merged.
     * The base case of the mergeSort is when the students array length is smaller or equal to 1
     *
     * @param students is array of Student objects representing the students from the .txt file
     */
    public static void mergeSort(Student[] students) {

        if (students.length > 1) {
            //Merge sort the first half
            Student[] firstHalf = new Student[students.length / 2];
            System.arraycopy(students, 0, firstHalf, 0, students.length / 2);
            mergeSort(firstHalf);

            //Merge sort the second half
            int secondHalfLength = students.length - students.length / 2;
            Student[] secondHalf = new Student[secondHalfLength];
            System.arraycopy(students, students.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            //Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, students);
        }
    }

    /**
     * This method merges two lists together until 1 list, which is the temp list, is completely formed
     *
     * @param list1 is the first list of Student objects to compare with the second list of Student objects in order to organise the array in decreasing order
     * @param list2 is the second list of Student objects to compare with the first list of Student objects in order to organise the array in decreasing order
     * @param temp  is a temporary array that is the two lists merged together in decreasing order. This array will be used with other arrays to merge together again.
     */
    public static void merge(Student[] list1, Student[] list2, Student[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            //The > of the if statement is usually reversed. However, the GPA needs to be sorted in a decreasing order which made the sign be >
            if (list1[current1].getGPA() > list2[current2].getGPA())
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /**
     * This method is taken from the Comparable interface
     * It compares two Student objects and returns a result depending on the id
     *
     * @param o the object to be compared.
     * @return integer representing the result of the comparison
     */
    @Override
    public int compareTo(Student o) {
        if (this.getId() < o.getId()) {
            return -1;
        } else if (this.getId() == o.getId()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * This method returns a string with all Student information
     * It is used to print it out in a .txt file
     *
     * @return String representing the Student information
     */
    @Override
    public String toString() {
        return this.getId() + "," + getName() + "," + getDiscipline() + "," + getYearOfStudy() + "," + getGender() + "," + getGPA();
    }


}

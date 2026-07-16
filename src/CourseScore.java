package FinalProject;

public class CourseScore implements Comparable<CourseScore> {
    private String courseId;
    private String courseName;
    private int studentId;
    private double score;
    private String teacherId;

    /**
     * This constructor creates a CourseScore object with the following parameters
     *
     * @param courseId   is String representing the course id
     * @param courseName is String representing the course name
     * @param studentId  is integer representing the student id
     * @param score      is double representing the score of the student
     * @param teacherId  is String representing the teacher's id
     */
    CourseScore(String courseId, String courseName, int studentId, double score, String teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentId = studentId;
        this.score = score;
        this.teacherId = teacherId;
    }

    /*Setters
    There is only Score as a setter since others are id, which should not change, and names, which should also not change
     */

    /**
     * This method sets the score of the course for the associated studentId
     *
     * @param score is double representing the score of the student on the Course
     */
    public void setScore(double score) {
        this.score = score;
    }

    //Getters

    /**
     * This method gets the course id
     *
     * @return String representing the course id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method gets the course name
     *
     * @return String representing the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method gets the student id associated to the course
     *
     * @return integer representing the student id
     */
    public int getStudentId() {
        return this.studentId;
    }

    /**
     * This method gets the score of the student for the course
     *
     * @return double representing the score of the student on the course
     */
    public double getScore() {
        return this.score;
    }

    /**
     * This method gets the teacher's id
     *
     * @return String representing the teacher's id for the course
     */
    public String getTeacherId() {
        return this.teacherId;
    }

    /**
     * This method comes from the Comparable interface
     * It compares two CourseScore objects by their student id
     *
     * @param o the CourseScore object to be compared.
     * @return integer representing the result of the comparison
     */
    @Override
    public int compareTo(CourseScore o) {
        if (this.studentId < o.studentId) {
            return -1;
        } else if (this.studentId == o.studentId) {
            return 0;
        } else {
            return 1;
        }
    }
}

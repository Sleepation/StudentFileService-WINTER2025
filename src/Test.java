import java.util.List;

public class Test {
    public static void main(String[] args) {
        FileServiceImpl service = new FileServiceImpl();
        List<Student> students = service.readStudents("student.txt");
        List<CourseScore> scores = service.readScores("courseScore.txt");
        service.linkStudentToClasses(students, scores);
        Student.sortByGPA(students);
        service.writeToFile("result.txt", students);
        service.disciplineFileWriting(students);
    }
}

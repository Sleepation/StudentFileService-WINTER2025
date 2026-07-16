package FinalProject;

import java.util.List;

public interface FileServiceI {
    List<Student> readStudents(String filename);

    List<CourseScore> readScores(String filename);

    void writeToFile(String filename, List<Student> students);
}

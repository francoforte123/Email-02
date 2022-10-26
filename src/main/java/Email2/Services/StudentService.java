package Email2.Services;

import Email2.Entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final List<Student> students = List.of(
            new Student("A001", "Mario", "Rossi", "mario.rossi@gmail.com"),
            new Student("A002", "Luca", "Verdi", "luca.verdi@gmail.com"),
            new Student("A003", "Anna", "Celeste", "anna.celeste@gmail.com"),
            new Student("A004", "Emmanuele", "La Duca", "laducaemanuele20@gmail.com")
    );

    public Student getStudentById(String id) {
        Optional<Student> foundStudent = students
                .stream()
                .filter(student -> student.getId().equals(id)).findFirst();
        if (foundStudent.isPresent()) {
            return foundStudent.get();
        }
        return null;
    }
}

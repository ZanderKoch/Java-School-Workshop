package data_access_objects;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoListTest {

    private StudentDaoList studentDao;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDaoList();
        student1 = new Student(1, "Alice", "alice@example.com", "123 Main St");
        student2 = new Student(2, "Bob", "bob@example.com", "456 Elm St");
    }

    @Test
    void saveStudentShouldAddStudentSuccessfully() {
        assertEquals(student1, studentDao.saveStudent(student1));
        assertTrue(studentDao.findAll().contains(student1));
    }

    @Test
    void saveStudentShouldNotAddDuplicateStudent() {
        Student duplicateAlice = new Student(1, "Alice", "alice2@example.com", "789 Oak St");
        studentDao.saveStudent(student1);
        assertNull(studentDao.saveStudent(duplicateAlice), "Duplicate ID should not be added");
    }

    @Test
    void saveStudentShouldThrowOnNullStudent(){
        assertThrows(IllegalArgumentException.class, () -> studentDao.saveStudent(null));
    }

    @Test
    void findByEmailShouldReturnCorrectStudent() {
        studentDao.saveStudent(student1);
        assertEquals(student1, studentDao.findByEmail("alice@example.com"));
        assertNull(studentDao.findByEmail("nonexistent@example.com"));
    }

    @Test
    void findByNameShouldReturnListOfMatchingStudents() {
        Student anotherAlice = new Student(3, "Alice", "alice3@example.com", "987 Pine St");
        studentDao.saveStudent(student1);
        studentDao.saveStudent(anotherAlice);

        List<Student> found = studentDao.findByName("Alice");
        assertEquals(2, found.size());
        assertTrue(found.contains(student1));
        assertTrue(found.contains(anotherAlice));

        assertTrue(studentDao.findByName("Charlie").isEmpty());
    }

    @Test
    void findByIdShouldReturnCorrectStudent() {
        studentDao.saveStudent(student1);
        assertEquals(student1, studentDao.findById(1));
        assertNull(studentDao.findById(999)); // Non-existent ID
    }

    @Test
    void findAllShouldReturnAllStudents() {
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);

        List<Student> allStudents = studentDao.findAll();
        assertEquals(2, allStudents.size());
        assertTrue(allStudents.contains(student1));
        assertTrue(allStudents.contains(student2));
    }

    @Test
    void deleteStudentShouldRemoveStudentIfExists() {
        studentDao.saveStudent(student1);
        assertTrue(studentDao.deleteStudent(student1));
        assertNull(studentDao.findById(1)); // Should be removed
        assertFalse(studentDao.deleteStudent(student1)); // Already removed, should return false
    }
}

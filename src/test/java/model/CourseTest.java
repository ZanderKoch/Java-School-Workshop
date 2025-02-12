package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course course;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        course = new Course(1, "Mathematics", LocalDate.of(2025, 3, 1), 10);
        student1 = new Student(101, "Alice", "alice@example.com", "123 Street");
        student2 = new Student(102, "Bob", "bob@example.com", "456 Avenue");
    }

    @Test
    void constructorShouldInitializeFieldsCorrectly() {
        assertEquals(1, course.getId());
        assertEquals("Mathematics", course.getCourseName());
        assertEquals(LocalDate.of(2025, 3, 1), course.getStartDate());
        assertEquals(10, course.getWeekDuration());
        assertTrue(course.getStudents().isEmpty());
    }

    @Test
    void constructorShouldThrowExceptionForBlankCourseName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course(2, "", LocalDate.of(2025, 3, 1), 10));
    }

    @Test
    void constructorShouldThrowExceptionForNullStartDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course(2, "Physics", null, 10));
    }

    @Test
    void constructorShouldThrowExceptionForInvalidWeekDuration() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course(2, "Chemistry", LocalDate.of(2025, 3, 1), 0));
    }

    @Test
    void setCourseNameShouldUpdateName() {
        course.setCourseName("Physics");
        assertEquals("Physics", course.getCourseName());
    }

    @Test
    void setStartDateShouldUpdateStartDate() {
        LocalDate newDate = LocalDate.of(2025, 6, 1);
        course.setStartDate(newDate);
        assertEquals(newDate, course.getStartDate());
    }

    @Test
    void setWeekDurationShouldUpdateDuration() {
        course.setWeekDuration(15);
        assertEquals(15, course.getWeekDuration());
    }

    @Test
    void registerShouldAddStudent() {
        course.register(student1);
        assertTrue(course.getStudents().contains(student1));
    }

    @Test
    void registerShouldThrowExceptionForDuplicateStudent() {
        course.register(student1);
        assertThrows(IllegalArgumentException.class, () -> course.register(student1));
    }

    @Test
    void unregisterShouldRemoveStudent() {
        course.register(student1);
        course.unregister(student1);
        assertFalse(course.getStudents().contains(student1));
    }

    @Test
    void unregisterShouldNotThrowExceptionForNonexistentStudent() {
        assertDoesNotThrow(() -> course.unregister(student1));
    }

    @Test
    void getStudentsShouldReturnUnmodifiableList() {
        course.register(student1);
        course.register(student2);
        List<Student> students = course.getStudents();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }
}
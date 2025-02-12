package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void constructorShouldInitializeFieldsCorrectly() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");

        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals("123 Main St", student.getAddress());
    }

    @Test
    void constructorShouldThrowExceptionForBlankName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Student(1, "", "john.doe@example.com", "123 Main St"));
    }

    @Test
    void constructorShouldThrowExceptionForBlankEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Student(1, "John Doe", "", "123 Main St"));
    }

    @Test
    void constructorShouldThrowExceptionForBlankAddress() {
        assertThrows(IllegalArgumentException.class, () ->
                new Student(1, "John Doe", "john.doe@example.com", ""));
    }

    @Test
    void setNameShouldUpdateName() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        student.setName("Jane Doe");
        assertEquals("Jane Doe", student.getName());
    }

    @Test
    void setNameShouldThrowExceptionForBlankName() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> student.setName(""));
    }

    @Test
    void setEmailShouldUpdateEmail() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        student.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", student.getEmail());
    }

    @Test
    void setEmailShouldThrowExceptionForBlankEmail() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> student.setEmail(""));
    }

    @Test
    void setAddressShouldUpdateAddress() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        student.setAddress("456 Elm St");
        assertEquals("456 Elm St", student.getAddress());
    }

    @Test
    void setAddressShouldThrowExceptionForBlankAddress() {
        Student student = new Student(1, "John Doe", "john.doe@example.com", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> student.setAddress(""));
    }
}

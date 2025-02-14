package data_access_objects;

import model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDaoListTest {
    private CourseDaoList courseDao;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    void setUp() {
        courseDao = new CourseDaoList();
        course1 = new Course(1, "Math", LocalDate.of(2024, 3, 1),8);
        course2 = new Course(2, "Physics", LocalDate.of(2024, 4, 1),6);
        course3 = new Course(3, "Math", LocalDate.of(2024, 3, 1),10);
    }

    @Test
    void testSaveCourse() {
        assertNotNull(courseDao.saveCourse(course1));
        assertNull(courseDao.saveCourse(course1)); // Duplicate course should return null
    }

    @Test
    void testFindById() {
        courseDao.saveCourse(course1);
        assertEquals(course1, courseDao.findById(1));
        assertNull(courseDao.findById(999)); // Non-existent ID
    }

    @Test
    void testFindByName() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        courseDao.saveCourse(course3);
        List<Course> mathCourses = courseDao.findByName("Math");
        assertEquals(2, mathCourses.size());
    }

    @Test
    void testFindByDate() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        courseDao.saveCourse(course3);
        List<Course> coursesOnDate = courseDao.findByDate(LocalDate.of(2024, 3, 1));
        assertEquals(2, coursesOnDate.size());
    }

    @Test
    void testFindAll() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        assertEquals(2, courseDao.findAll().size());
    }

    @Test
    void testRemoveCourse() {
        courseDao.saveCourse(course1);
        assertTrue(courseDao.removeCourse(course1));
        assertFalse(courseDao.removeCourse(course1)); // Already removed
    }
}

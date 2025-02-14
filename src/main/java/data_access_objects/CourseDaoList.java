package data_access_objects;


import model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CourseDaoList implements CourseDao{
    private static final Set<Course> courses = new LinkedHashSet<>();

    /**
     * Tries to save a new course. Returns the saved course
     * if successful or {@code null} if the course could not be
     * saved due to a duplicate already existing.
     *
     * @param course the course to try to save.
     * @return the saved course if successful or {@code null} if a
     * duplicate already exists.
     */
    @Override
    public Course saveCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("course may not be null");
        }

        boolean success = courses.add(course);

        if (!success) {
            return null;
        }
        return course;
    }

    /**
     * Returns the course whose id matches the supplied one, or
     * {@code null} if no course with a matching id is found.
     *
     * @param id id to search for
     * @return the first course by order of insertion with a matching id,
     * or {@code null} if no match is found
     */
    @Override
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == (id)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Returns a list of course objects whose name matches the
     * supplied one. If no courses with a matching name are found, an empty
     * list is returned instead.
     *
     * @param name the name to search for
     * @return a list of courses with the matching name, or an empty list if no
     * match is found
     */
    @Override
    public List<Course> findByName(String name) {
        List<Course> found = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                found.add(course);
            }
        }
        return found;
    }

    /**
     * Returns a list of course objects whose start date matches the
     * supplied one. If no courses with a matching start date are found, an
     * empty list is returned instead.
     *
     * @param date the name to search for
     * @return a list of courses with the matching starting date, or an empty
     * list if no match is found
     */
    @Override
    public List<Course> findByDate(LocalDate date) {
        List<Course> found = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStartDate().equals(date)) {
                found.add(course);
            }
        }
        return found;
    }

    /**
     * Returns a list containing all course objects.
     * The returned list reflects the order of insertion.
     *
     * @return a list of all courses
     */
    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    /**
     * Removes the specified course from the collection.
     * If the course is found and successfully removed, returns {@code true}.
     * If the course is not in the collection, returns {@code false}.
     *
     * @param course the course to remove
     * @return {@code true} if the course was successfully removed, {@code false} if the student was not found
     */
    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }
}

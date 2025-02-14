package data_access_objects;

import model.Student;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class StudentDaoList implements StudentDao {
    /*I was originally going to use HashSet since they enforce uniqueness,
    * but since the instructions don't explicitly state that email addresses
    * are also unique I switched to LinkedHashSet so that the Student returned
    * by findByEmail/findByName isn't essentially random based on id in the
    * case that two students share the same email/name */
    private static final Set<Student> students = new LinkedHashSet<>();

    /**
     * Tries to save a new student. Returns the saved student
     * if successful or {@code null} if the student could not be
     * saved due to a duplicate already existing.
     *
     * @param student the student to try to save.
     * @return the saved student if successful or {@code null} if a
     * duplicate already exists.
     */
    @Override
    public Student saveStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student may not be null");
        }

        boolean success = students.add(student);

        if (!success) {
            return null;
        }
        return student;
    }

    /**
     * Returns <strong>the first by order of insertion</strong> student whose
     * email matches the supplied one, or {@code null} if no student with a
     * matching email is found.
     *
     * @param email email to search for
     * @return the first student by order of insertion with a matching email,
     * or {@code null} if no match is found
     */
    @Override
    public Student findByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Returns a list of student objects whose name matches the
     * supplied one. If no students with a matching name are found, an empty
     * list is returned instead
     *
     * @param name the name to search for
     * @return a list of students with the matching name, or an empty list if no match is found
     */
    @Override
    public List<Student> findByName(String name) {
        List<Student> found = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                found.add(student);
            }
        }
        return found;
    }

    /**
     * Returns the student whose id matches the supplied one, or
     * {@code null} if no student with a matching id is found.
     *
     * @param id id to search for
     * @return the first student by order of insertion with a matching id,
     * or {@code null} if no match is found
     */
    @Override
    public Student findById(int id) {
        /*If I refactor to use a map implementation I could easily check for
        * the id and exit early preventing a potential O(n) worst case scenario.
        * This is nice and simple though, and if you had enough data for this to
        * be too slow in the real world I would strongly recommend switching to a
        * database anyway*/
        for (Student student : students) {
            if (student.getId() == (id)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Returns a list containing all student objects.
     * The returned list reflects the order of insertion.
     *
     * @return a list of all students
     */
    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    /**
     * Removes the specified student from the collection.
     * If the student is found and successfully removed, returns {@code true}.
     * If the student is not in the collection, returns {@code false}.
     *
     * @param student the student to remove
     * @return {@code true} if the student was successfully removed, {@code false} if the student was not found
     */
    @Override
    public boolean deleteStudent(Student student) {
        return students.remove(student);
    }
}

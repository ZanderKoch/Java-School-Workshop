package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students = new ArrayList<>();

    public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
        if (courseName.isBlank()) {
            throw new IllegalArgumentException("courseName may not be blank");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("startDate may not be null");
        }
        if (weekDuration <= 0) {
            throw new IllegalArgumentException("weekDuration must be > 0");
        }

        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName.isBlank()) {
            throw new IllegalArgumentException("courseName may not be blank");
        }
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("startDate may not be null");
        }
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        if (weekDuration <= 0) {
            throw new IllegalArgumentException("weekDuration must be > 0");
        }
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void register(Student student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("student is already registered in this course");
        }
        students.add(student);
    }

    public void unregister(Student student) {
        students.remove(student);
    }
}

package model;

public class Student {
    private final int id;
    private String name;
    private String email;
    private String address;

    public Student(int id, String name, String email, String address) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("name may not be blank");
        }
        if (email.isBlank()) {
            throw new IllegalArgumentException("email may not be blank");
        }
        if (address.isBlank()) {
            throw new IllegalArgumentException("address may not be blank");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("name may not be blank");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("email may not be blank");
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.isBlank()) {
            throw new IllegalArgumentException("address may not be blank");
        }
        this.address = address;
    }
}

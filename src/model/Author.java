package model;

public class Author {
    private String name;
    private String surname;
    private String email;
    private String gender;
    private User registeredUser;
    public Author(String name, String surname, String email, String gender, User registeredUser) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.registeredUser = registeredUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", registeredUser=" + registeredUser +
                '}';
    }
}

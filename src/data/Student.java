package data;

import data.exceptions.MalformedStudentException;

import java.io.Serializable;

/**
 * @author Loris Friedel
 */
public class Student implements Serializable {

  private final String name;
  private final String email;

  /**
   * Create a student from its name and email.
   *
   * @param name  Name of the student.
   * @param email Email of the student.
   */
  public Student(String name, String email) {
    if(!((name != null) && (email != null))) {
      throw new MalformedStudentException("Invalid student args. All fields need to be non-null");
    }
    this.name = name;
    this.email = email;
  }

  /**
   * @return The name of the student.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The email of the student.
   */
  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "Student{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (!getName().equals(student.getName())) return false;
    return getEmail().equals(student.getEmail());

  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getEmail().hashCode();
    return result;
  }
}

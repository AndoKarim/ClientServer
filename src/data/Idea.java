package data;

import data.exceptions.MalformedIdeaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Loris Friedel
 */
public class Idea implements Serializable {

  private final String name;
  private final String description;
  private final ArrayList<String> technologies; // ArrayList to ensure serialization
  private final Student author;
  private final ArrayList<Student> registeredStudents; // ArrayList to ensure serialization

  /**
   * Create an idea from basic information.
   *
   * @param name         The name of the idea.
   * @param author       The author of the idea.
   * @param description  The description of the idea.
   * @param technologies A collection of all technologies used in the project.
   */
  public Idea(String name, Student author, String description, Collection<String> technologies) {
    if (!verifyArgs(name, description, technologies, author)) {
      throw new MalformedIdeaException("Invalid idea args. All fields need to be non-null");
    }
    this.name = name;
    this.description = description;
    this.technologies = new ArrayList<>(technologies);
    this.author = author;
    this.registeredStudents = new ArrayList<>();
  }

  /**
   * Check if all given parameters are legal.
   *
   * @param name         The name of the idea.
   * @param description  The description of the idea.
   * @param technologies A collection of all technologies used in the project.
   * @param author       The author of the idea
   * @return True if all parameters
   */
  private boolean verifyArgs(String name, String description, Collection<String> technologies, Student author) {
    return (name != null)
            && (description != null)
            && (technologies != null)
            && (author != null);
  }

  /**
   * Add a student in the registered student list of this idea.
   * (a list of all students that participate in the idea project)
   *
   * @param student Student who join the project (idea).
   */
  public void registerStudent(Student student) {
    registeredStudents.add(student);
  }

  /**
   * @return The name of the idea.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The description of this idea.
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return A list of all technologies used in the project (idea).
   */
  public List<String> getTechnologies() {
    return technologies;
  }

  /**
   * @return The author of this idea.
   */
  public Student getAuthor() {
    return author;
  }

  /**
   * @return A list of mail of all students that have joined the project (idea).
   */
  public List<Student> getRegisteredStudents() {
    return registeredStudents;
  }

  @Override
  public String toString() {
    return "Idea{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", technologies=" + technologies +
            ", author=" + author +
            ", registeredStudents=" + registeredStudents +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Idea idea = (Idea) o;

    if (!getName().equals(idea.getName())) return false;
    if (!getDescription().equals(idea.getDescription())) return false;
    if (!getTechnologies().equals(idea.getTechnologies())) return false;
    if (!getAuthor().equals(idea.getAuthor())) return false;
    return getRegisteredStudents().equals(idea.getRegisteredStudents());

  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getDescription().hashCode();
    result = 31 * result + getTechnologies().hashCode();
    result = 31 * result + getAuthor().hashCode();
    result = 31 * result + getRegisteredStudents().hashCode();
    return result;
  }
}
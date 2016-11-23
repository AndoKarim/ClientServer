package data.exceptions;

/**
 * @author Loris Friedel
 */
public class MalformedStudentException extends RuntimeException {
  public MalformedStudentException(String msg) {
    super(msg);
  }
}

package telephony.shared.gwtp;

public class FieldVerifier {

  /**
   * Verifies that the specified name is valid for our service.
   *
   * In this example, we only require that the name is at least four
   * characters. In your application, you can use more complex checks to ensure
   * that usernames, passwords, email addresses, URLs, and other fields have the
   * proper syntax.
   *
   * @param name the name to validate
   * @return true if valid, false if invalid
   */
  public static boolean isValidUserName(String name) {
    if (name == null) {
      return false;
    }
    return name.length() > 6;
  }

  /**
   * Verifies that the specified name is valid for our service.
   *
   * In this example, we only require that the name is at least four
   * characters. In your application, you can use more complex checks to ensure
   * that usernames, passwords, email addresses, URLs, and other fields have the
   * proper syntax.
   *
   * @param password the name to validate
   * @return true if valid, false if invalid
   */
  public static boolean isValidPassword(String password) {
    if (password == null) {
      return false;
    }
    return password.length() > 7;
  }
}

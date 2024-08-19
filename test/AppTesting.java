/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author trish
 */
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JTextField;

public class AppTesting {

    private JTextField nameField;
    private JTextField institutionField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField usernameField;
    private JTextField loginPasswordField;
    private JTextField courseNameField;
    private JTextField noticeTitleField;
    private JTextField noticeContentField;
    private JTextField userTypeField;

    @Before
    public void setUp() {
        // Initialize fields
        nameField = new JTextField();
        institutionField = new JTextField();
        emailField = new JTextField();
        passwordField = new JTextField();
        usernameField = new JTextField();
        loginPasswordField = new JTextField();
        courseNameField = new JTextField();
        noticeTitleField = new JTextField();
        noticeContentField = new JTextField();
        userTypeField = new JTextField();
    }

    // Admin Signup Test
    @Test
    public void testSignupFields() {
        Assert.assertFalse("Name is empty", nameField.getText().isEmpty());
        Assert.assertFalse("Institution name is empty", institutionField.getText().isEmpty());
        Assert.assertFalse("Email is empty", emailField.getText().isEmpty());
        Assert.assertFalse("Password is empty", passwordField.getText().isEmpty());

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email regex
        Assert.assertTrue("Invalid email format", emailField.getText().matches(emailRegex));
    }

    // User Login Test
    @Test
    public void testLoginFields() {
        Assert.assertFalse("Username is empty", usernameField.getText().isEmpty());
        Assert.assertFalse("Password is empty", loginPasswordField.getText().isEmpty());

        String username = usernameField.getText();
        Assert.assertTrue("Username must start with ST or TE", username.startsWith("ST")
                || username.startsWith("TE"));
    }

    // Course Management Tests
    @Test
    public void testCourseCreation() {
        courseNameField.setText("Object Oriented Programming");
        Assert.assertFalse("Course name is empty", courseNameField.getText().isEmpty());
        Assert.assertTrue("Course name must be unique", isCourseNameUnique(courseNameField.getText()));
    }

    private boolean isCourseNameUnique(String courseName) {
        // Simulate a check against existing course names
        return !courseName.equals("Object Oriented Programming"); // Example of existing course name
    }

    // Notice Management Tests
    @Test
    public void testNoticeCreation() {
        noticeTitleField.setText("Exam Schedule");
        noticeContentField.setText("The exam will be held on Monday.");
        Assert.assertFalse("Notice title is empty", noticeTitleField.getText().isEmpty());
        Assert.assertFalse("Notice content is empty", noticeContentField.getText().isEmpty());
        Assert.assertTrue("Notice type is invalid", isNoticeTypeValid("teacher")); // Example type
    }

    private boolean isNoticeTypeValid(String type) {
        return type.equals("teacher") || type.equals("student");
    }

    // User Management Tests
    @Test
    public void testUserCreation() {
        nameField.setText("John Doe");
        userTypeField.setText("Student");
        emailField.setText("johndoe@example.com");
        passwordField.setText("securePassword");

        Assert.assertFalse("User name is empty", nameField.getText().isEmpty());
        Assert.assertTrue("User type is invalid", isUserTypeValid(userTypeField.getText()));
        Assert.assertFalse("Email is empty", emailField.getText().isEmpty());
        Assert.assertFalse("Password is empty", passwordField.getText().isEmpty());
    }

    private boolean isUserTypeValid(String userType) {
        return userType.equals("Student") || userType.equals("Teacher");
    }
}

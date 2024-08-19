/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.SessionManagement;

import vidyalaya.Model.StudentData;

/**
 *
 * @author trish
 */
public class StudentSession {

    private static StudentData currentUser;

    public static void setCurrentUser(StudentData userData) {
        currentUser = userData;
    }

    public static StudentData getCurrentUser() {
        return currentUser;
    }
}
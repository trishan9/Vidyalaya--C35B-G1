/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.SessionManagement;

import vidyalaya.Model.TeacherData;

/**
 *
 * @author trish
 */
public class TeacherSession {

    private static TeacherData currentUser;

    public static void setCurrentUser(TeacherData userData) {
        currentUser = userData;
    }

    public static TeacherData getCurrentUser() {
        return currentUser;
    }
}

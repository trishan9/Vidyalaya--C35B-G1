/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.SessionManagement;

import vidyalaya.Model.AdminData;

/**
 *
 * @author trish
 */
public class AdminSession {

    private static AdminData currentUser;

    public static void setCurrentUser(AdminData userData) {
        currentUser = userData;
    }

    public static AdminData getCurrentUser() {
        return currentUser;
    }
}

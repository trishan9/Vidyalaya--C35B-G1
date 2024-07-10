/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Admin;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.Dashboard.Admin.CoursesScreen;

/**
 *
 * @author trishan9
 */
public class CoursesController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final CoursesScreen userView;

    public CoursesController(CoursesScreen userView) {
        this.userView = userView;
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
}

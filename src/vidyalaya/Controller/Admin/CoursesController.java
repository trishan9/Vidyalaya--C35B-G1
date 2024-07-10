/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.Dashboard.Admin.CreateUser;

/**
 *
 * @author trishan9
 */
public class CreateUserController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final CreateUser userView;

    public CreateUserController(CreateUser userView) {
        this.userView = userView;
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
}

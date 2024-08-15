/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Courses.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.ModuleData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Components.Modals.CreateCourseForm;

/**
 *
 * @author trishan9
 */
public class CreateCourseController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final CreateCourseForm userView;

    public CreateCourseController(CreateCourseForm userView) {
        this.userView = userView;
        userView.addCreateCourseListener(new CreateCourseListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CreateCourseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();

                AdminData currentAdmin = AdminSession.getCurrentUser();

                ModuleData course = new ModuleData(currentAdmin.getId(), name);
                moduleDAO.createModule(course);

                vidyalaya.View.Dashboard.Admin.CoursesScreen coursesView = new vidyalaya.View.Dashboard.Admin.CoursesScreen();
                vidyalaya.Controller.Courses.Admin.CoursesController coursesController = new vidyalaya.Controller.Courses.Admin.CoursesController(coursesView);
                Utils.closeAllFrames();
                coursesController.open();
                Utils.success("Course created successfully: " + name);
            } catch (Exception ex) {
                Logger.getLogger(CreateCourseController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }
}

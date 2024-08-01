/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Courses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;
import vidyalaya.Components.Modals.EditCourseForm;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.ModuleData;

import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.Utils.UIUtils;

/**
 *
 * @author trishan9
 */
public class EditCourseController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final EditCourseForm userView;
    private final int moduleCode;

    public EditCourseController(int moduleCode, EditCourseForm userView) {
        this.userView = userView;
        this.moduleCode = moduleCode;
        userView.addEditCourseListener(new EditCourseListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final ModuleData getModuleByCode() {
        try {
            ModuleData data = moduleDAO.getModuleByCode(moduleCode);
            return data;
        } catch (Exception ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
            UIUtils.error(userView, ex.getMessage());
            return null;
        }
    }

    class EditCourseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();

                AdminData currentAdmin = AdminSession.getCurrentUser();

                ModuleData course = new ModuleData(currentAdmin.getId(), name);
                moduleDAO.updateModule(moduleCode, course);

                vidyalaya.View.Dashboard.Admin.CoursesScreen coursesView = new vidyalaya.View.Dashboard.Admin.CoursesScreen();
                vidyalaya.Controller.Courses.CoursesController coursesController = new vidyalaya.Controller.Courses.CoursesController(coursesView);
                UIUtils.closeAllFrames();
                coursesController.open();
                UIUtils.info(coursesView, "Course updated successfully");
            } catch (Exception ex) {
                Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
                UIUtils.error(userView, ex.getMessage());
            }
        }
    }
}

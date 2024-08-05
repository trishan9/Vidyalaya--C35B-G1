/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Courses.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.ModuleData;
import vidyalaya.Model.TeacherData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;
import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Components.Modals.EditCourseForm;

/**
 *
 * @author trishan9
 */
public class EditCourseController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final EditCourseForm userView;
    private final int moduleCode;
    public List<TeacherData> teachersList = new ArrayList<>();
    public List<TeacherData> moduleTeachersList = new ArrayList<>();

    public EditCourseController(int moduleCode, EditCourseForm userView) {
        this.userView = userView;
        this.moduleCode = moduleCode;
        userView.addEditCourseListener(new EditCourseListener());
        getTeachersList();
        getModuleTeachersList();
        populateComboBox(userView.getTeachersField(), teachersList);
        userView.getTeachersField().setSelectedItems(moduleTeachersList);
    }

    public final void getTeachersList() {
        try {
            teachersList = authDAO.getAllTeachers();
        } catch (Exception ex) {
            teachersList = new ArrayList<>();
        }
    }

    public final void getModuleTeachersList() {
        try {
            moduleTeachersList = moduleDAO.getAllModuleTeachers(moduleCode);
        } catch (Exception ex) {
            moduleTeachersList = new ArrayList<>();
        }
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
            Utils.error(ex.getMessage());
            return null;
        }
    }

    private void populateComboBox(JComboBox<TeacherData> combo, List<TeacherData> teachersList) {
        DefaultComboBoxModel<TeacherData> model = new DefaultComboBoxModel<>();
        for (TeacherData teacher : teachersList) {
            model.addElement(teacher);
        }
        combo.setModel(model);
    }

    class EditCourseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();
                List<TeacherData> selectedTeachers = userView.getTeachersField().getSelectedItems();

                AdminData currentAdmin = AdminSession.getCurrentUser();

                ModuleData course = new ModuleData(currentAdmin.getId(), name);
                moduleDAO.updateModule(moduleCode, course);

                moduleDAO.deleteModuleTeachers(moduleCode);

                selectedTeachers.forEach((teacher) -> {
                    try {
                        moduleDAO.createOrUpdateModuleTeacher(moduleCode, teacher.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                vidyalaya.View.Dashboard.Admin.CoursesScreen coursesView = new vidyalaya.View.Dashboard.Admin.CoursesScreen();
                vidyalaya.Controller.Courses.Admin.CoursesController coursesController = new vidyalaya.Controller.Courses.Admin.CoursesController(coursesView);
                Utils.closeAllFrames();
                coursesController.open();
                Utils.success("Course updated successfully");
            } catch (Exception ex) {
                Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }
}

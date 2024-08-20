/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Attendance.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import vidyalaya.Utils.Utils;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;

import vidyalaya.Controller.AdminLoginController;

import vidyalaya.DAO.AttendanceDAO.AttendanceDAO;
import vidyalaya.DAO.AttendanceDAO.AttendanceDAOImplementation;
import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Model.AttendanceData;
import vidyalaya.Model.ModuleData;

import vidyalaya.SessionManagement.AdminSession;
import vidyalaya.View.Dashboard.Admin.DashboardScreen;

/**
 *
 * @author trish
 */
public class AttendanceController {

    private final AttendanceDAO attendanceDAO = new AttendanceDAOImplementation();
    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final AttendanceScreen userView;
    public List<ModuleData> modulesList = new ArrayList<>();

    public AttendanceController(AttendanceScreen userView) {
        this.userView = userView;
        userView.addFetchAttendanceListener(new FetchAttendanceListener());
        userView.addDashboardRedirectListener(new DashboardRedirectListener());
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getModulesList();
        populateComboBox(userView.getModule(), modulesList);
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class FetchAttendanceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel oldModel = (DefaultTableModel) userView.getUserTable().getModel();
                oldModel.setRowCount(0);
                String date = userView.getDateField().getText();
                if (date.isEmpty()) {
                    Utils.warning("Date range must be selected!");
                    return;
                }
                String startDate = Utils.convertDateString(date.substring(0, 10));
                String endDate = Utils.convertDateString(date.substring(14, 24));

                ModuleData moduleData = (ModuleData) userView.getModule().getSelectedItem();
                int moduleCode = moduleData.getCode();

                List<AttendanceData> attendance = attendanceDAO.getAttendanceOfStudentAndCourseWithDateRange(moduleCode, startDate, endDate);

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Student ID");
                model.addColumn("Student Name");
                model.addColumn("Module Code");
                model.addColumn("Attendance Date");
                for (AttendanceData data : attendance) {
                    model.addRow(new Object[]{data.getStudentId(), data.getStudentName(), data.getModuleCode(), data.getAttendanceDate()});
                }

                userView.getUserTable().setDefaultEditor(Object.class, null);
                userView.getUserTable().setModel(model);
            } catch (Exception ex) {
                Utils.error("Please select appropriate date range and module!");
            }

        }
    }

    private void populateComboBox(JComboBox<ModuleData> combo, List<ModuleData> modulesList) {
        DefaultComboBoxModel<ModuleData> model = new DefaultComboBoxModel<>();
        for (ModuleData module : modulesList) {
            model.addElement(module);
        }
        combo.setModel(model);
    }

    public final void getModulesList() {
        try {
            modulesList = moduleDAO.getAllModules(AdminSession.getCurrentUser().getId());
        } catch (Exception ex) {
            modulesList = new ArrayList<>();
        }
    }

    class DashboardRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardScreen dashboardView = new DashboardScreen();
            vidyalaya.Controller.Dashboard.DashboardController dashboardController = new vidyalaya.Controller.Dashboard.DashboardController(dashboardView);
            Utils.closeAllFrames();
            dashboardController.open();
        }
    }

    class CoursesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CoursesScreen coursesView = new CoursesScreen();
            vidyalaya.Controller.Courses.Admin.CoursesController coursesController = new vidyalaya.Controller.Courses.Admin.CoursesController(coursesView);
            Utils.closeAllFrames();
            coursesController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            vidyalaya.Controller.Routine.Admin.RoutineController routineController = new vidyalaya.Controller.Routine.Admin.RoutineController(routineView);
            Utils.closeAllFrames();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
        }
    }

    class UsersRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UsersScreen usersView = new UsersScreen();
            vidyalaya.Controller.Users.UsersController usersController = new vidyalaya.Controller.Users.UsersController(usersView);
            Utils.closeAllFrames();
            usersController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            vidyalaya.Controller.Settings.Admin.SettingsController settingsController = new vidyalaya.Controller.Settings.Admin.SettingsController(settingsView);
            Utils.closeAllFrames();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminLogin adminLoginView = new AdminLogin();
            AdminLoginController adminLoginController = new AdminLoginController(adminLoginView);
            Utils.closeAllFrames();
            adminLoginController.open();
        }
    }
}

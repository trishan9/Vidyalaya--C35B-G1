/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Routine.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.RoutineData;
import vidyalaya.SessionManagement.AdminSession;
import vidyalaya.DAO.RoutineDAO.RoutineDAO;
import vidyalaya.DAO.RoutineDAO.RoutineDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;
import vidyalaya.Components.Modals.CreateRoutineForm;

import vidyalaya.Controller.AdminLoginController;

/**
 *
 * @author trish
 */
public class RoutineController {

    private final RoutineDAO routineDAO = new RoutineDAOImplementation();
    private final RoutineScreen userView;
    public List<RoutineData> routinesList = new ArrayList<>();

    public RoutineController(RoutineScreen userView) {
        this.userView = userView;
        userView.addCreateRoutineListener(new CreateRoutineListener());
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getRoutinesList();
    }

    public final void getRoutinesList() {
        try {
            routinesList = routineDAO.getAllRoutines(AdminSession.getCurrentUser().getId());
        } catch (Exception ex) {
            routinesList = new ArrayList<>();
        }
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void deleteRoutineById(int routineId) {
        try {
            int result = Utils.confirm(userView, "Are you sure you want to delete this routine?");
            if (result == JOptionPane.YES_OPTION) {
                routineDAO.deleteRoutine(routineId);

                vidyalaya.View.Dashboard.Admin.RoutineScreen routineView = new vidyalaya.View.Dashboard.Admin.RoutineScreen();
                vidyalaya.Controller.Routine.Admin.RoutineController routineController = new vidyalaya.Controller.Routine.Admin.RoutineController(routineView);
                Utils.closeAllFrames();
                routineController.open();
                Utils.success("Routine deleted successfully!");
            }
        } catch (Exception ex) {
            Logger.getLogger(RoutineController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(ex.getMessage());
        }
    }

    class CreateRoutineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateRoutineForm createRoutineView = new CreateRoutineForm();
            CreateRoutineController createRoutineController = new CreateRoutineController(createRoutineView);
            createRoutineController.open();
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

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
        }
    }

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Admin.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Admin.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
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

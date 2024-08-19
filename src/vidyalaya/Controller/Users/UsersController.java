/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.StudentData;
import vidyalaya.Model.TeacherData;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;
import vidyalaya.View.Dashboard.Admin.DashboardScreen;

import vidyalaya.Components.Modals.CreateNewUserForm;

import vidyalaya.Controller.AdminLoginController;

/**
 *
 * @author trish
 */
public class UsersController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final UsersScreen userView;
    public List<StudentData> studentsList = new ArrayList<>();
    public List<TeacherData> teachersList = new ArrayList<>();

    public UsersController(UsersScreen userView) {
        this.userView = userView;
        userView.addCreateNewUserListener(new CreateNewUserListener());
        userView.addDashboardRedirectListener(new DashboardRedirectListener());
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getStudentsList();
        getTeachersList();
    }

    public final void getStudentsList() {
        try {
            studentsList = authDAO.getAllStudents();
        } catch (Exception ex) {
            studentsList = new ArrayList<>();
        }
    }

    public final void getTeachersList() {
        try {
            teachersList = authDAO.getAllTeachers();
        } catch (Exception ex) {
            teachersList = new ArrayList<>();
        }
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void deleteUserById(int userId, String userType) {
        try {
            int result = Utils.confirm(userView, "Are you sure you want to delete this user?");
            if (result == JOptionPane.YES_OPTION) {
                authDAO.deleteUser(userId, userType);

                vidyalaya.View.Dashboard.Admin.UsersScreen usersView = new vidyalaya.View.Dashboard.Admin.UsersScreen();
                vidyalaya.Controller.Users.UsersController usersController = new vidyalaya.Controller.Users.UsersController(usersView);
                Utils.closeAllFrames();
                usersController.open();
                Utils.success("User deleted successfully!");
            }
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(ex.getMessage());
        }
    }

    class CreateNewUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateNewUserForm createNewUserView = new CreateNewUserForm();
            CreateNewUserController createNewUserController = new CreateNewUserController(createNewUserView);
            createNewUserController.open();
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

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Admin.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Admin.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vidyalaya.Components.Modals.CreateCourseForm;

import vidyalaya.Controller.AdminLoginController;
import vidyalaya.Controller.CreateCourseController;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;

/**
 *
 * @author trishan9
 */
public class CoursesController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final CoursesScreen userView;

    public CoursesController(CoursesScreen userView) {
        this.userView = userView;
        userView.addCreateCourseListener(new CreateCourseListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
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
            CreateCourseForm createCourseView = new CreateCourseForm();
            CreateCourseController createCourseController = new CreateCourseController(createCourseView);
            createCourseController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            vidyalaya.Controller.Admin.RoutineController routineController = new vidyalaya.Controller.Admin.RoutineController(routineView);
            close();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Admin.NoticesController noticesController = new vidyalaya.Controller.Admin.NoticesController(noticesView);
            close();
            noticesController.open();
        }
    }

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Admin.AttendanceController attendanceController = new vidyalaya.Controller.Admin.AttendanceController(attendanceView);
            close();
            attendanceController.open();
        }
    }

    class UsersRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UsersScreen usersView = new UsersScreen();
            vidyalaya.Controller.Admin.UsersController usersController = new vidyalaya.Controller.Admin.UsersController(usersView);
            close();
            usersController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            vidyalaya.Controller.Admin.SettingsController settingsController = new vidyalaya.Controller.Admin.SettingsController(settingsView);
            close();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminLogin adminLoginView = new AdminLogin();
            AdminLoginController adminLoginController = new AdminLoginController(adminLoginView);
            close();
            adminLoginController.open();
        }
    }
}

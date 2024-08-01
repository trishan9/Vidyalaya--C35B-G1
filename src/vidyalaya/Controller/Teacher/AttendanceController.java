/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vidyalaya.Controller.UserLoginController;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.UserLogin;
import vidyalaya.View.Dashboard.Teacher.AttendanceScreen;
import vidyalaya.View.Dashboard.Teacher.MyCoursesScreen;
import vidyalaya.View.Dashboard.Teacher.NoticesScreen;
import vidyalaya.View.Dashboard.Teacher.RoutineScreen;
import vidyalaya.View.Dashboard.Teacher.SettingsScreen;

/**
 *
 * @author trish
 */
public class AttendanceController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final AttendanceScreen userView;

    public AttendanceController(AttendanceScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CoursesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyCoursesScreen coursesView = new MyCoursesScreen();
            MyCoursesController coursesController = new MyCoursesController(coursesView);
            close();
            coursesController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            RoutineController routineController = new RoutineController(routineView);
            close();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            NoticesController noticesController = new NoticesController(noticesView);
            close();
            noticesController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            SettingsController settingsController = new SettingsController(settingsView);
            close();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UserLogin userLoginView = new UserLogin();
            UserLoginController userLoginController = new UserLoginController(userLoginView);
            close();
            userLoginController.open();
        }
    }
}

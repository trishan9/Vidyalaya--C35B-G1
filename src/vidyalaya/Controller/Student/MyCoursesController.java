/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vidyalaya.Controller.UserLoginController;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.Dashboard.Student.AttendanceScreen;
import vidyalaya.View.Dashboard.Student.NoticesScreen;
import vidyalaya.View.Dashboard.Student.RoutineScreen;
import vidyalaya.View.Dashboard.Student.SettingsScreen;
import vidyalaya.View.Dashboard.Student.MyCoursesScreen;
import vidyalaya.View.UserLogin;

/**
 *
 * @author trishan9
 */
public class MyCoursesController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final MyCoursesScreen userView;

    public MyCoursesController(MyCoursesScreen userView) {
        this.userView = userView;
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            vidyalaya.Controller.Student.RoutineController routineController = new vidyalaya.Controller.Student.RoutineController(routineView);
            close();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Student.NoticesController noticesController = new vidyalaya.Controller.Student.NoticesController(noticesView);
            close();
            noticesController.open();
        }
    }

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Student.AttendanceController attendanceController = new vidyalaya.Controller.Student.AttendanceController(attendanceView);
            close();
            attendanceController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            vidyalaya.Controller.Student.SettingsController settingsController = new vidyalaya.Controller.Student.SettingsController(settingsView);
            close();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UserLogin studentLoginView = new UserLogin();
            UserLoginController studentLoginController = new UserLoginController(studentLoginView);
            close();
            studentLoginController.open();
        }
    }
}

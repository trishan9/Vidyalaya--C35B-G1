/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Attendance.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vidyalaya.Utils.Utils;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.UserLogin;
import vidyalaya.View.Dashboard.Student.AttendanceScreen;
import vidyalaya.View.Dashboard.Student.MyCoursesScreen;
import vidyalaya.View.Dashboard.Student.NoticesScreen;
import vidyalaya.View.Dashboard.Student.RoutineScreen;
import vidyalaya.View.Dashboard.Student.SettingsScreen;

import vidyalaya.Controller.Users.UserLoginController;

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
            vidyalaya.Controller.Courses.Student.MyCoursesController coursesController = new vidyalaya.Controller.Courses.Student.MyCoursesController(coursesView);
            Utils.closeAllFrames();
            coursesController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            vidyalaya.Controller.Routine.Student.RoutineController routineController = new vidyalaya.Controller.Routine.Student.RoutineController(routineView);
            Utils.closeAllFrames();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Student.NoticesController noticesController = new vidyalaya.Controller.Notices.Student.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            vidyalaya.Controller.Settings.Student.SettingsController settingsController = new vidyalaya.Controller.Settings.Student.SettingsController(settingsView);
            Utils.closeAllFrames();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UserLogin userLoginView = new UserLogin();
            UserLoginController userLoginController = new UserLoginController(userLoginView);
            Utils.closeAllFrames();
            userLoginController.open();
        }
    }
}

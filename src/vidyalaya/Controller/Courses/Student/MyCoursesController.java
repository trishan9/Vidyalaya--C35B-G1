/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Courses.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import vidyalaya.Controller.Users.UserLoginController;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Model.ModuleData;

import vidyalaya.SessionManagement.StudentSession;

import vidyalaya.Utils.Utils;

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

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final MyCoursesScreen userView;
    public List<ModuleData> modulesList = new ArrayList<>();

    public MyCoursesController(MyCoursesScreen userView) {
        this.userView = userView;
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getModulesList();
    }

    public final void getModulesList() {
        try {
            modulesList = moduleDAO.getAllModules(StudentSession.getCurrentUser().getAdminId());
        } catch (Exception ex) {
            modulesList = new ArrayList<>();
        }
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

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Student.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Student.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
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
            UserLogin studentLoginView = new UserLogin();
            UserLoginController studentLoginController = new UserLoginController(studentLoginView);
            Utils.closeAllFrames();
            studentLoginController.open();
        }
    }
}

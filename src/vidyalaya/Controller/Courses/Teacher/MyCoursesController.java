/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Courses.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.ModuleData;
import vidyalaya.SessionManagement.TeacherSession;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.View.Dashboard.Teacher.AttendanceScreen;
import vidyalaya.View.Dashboard.Teacher.NoticesScreen;
import vidyalaya.View.Dashboard.Teacher.RoutineScreen;
import vidyalaya.View.Dashboard.Teacher.SettingsScreen;
import vidyalaya.View.Dashboard.Teacher.MyCoursesScreen;
import vidyalaya.View.UserLogin;

import vidyalaya.Controller.Users.UserLoginController;

/**
 *
 * @author trishan9
 */
public class MyCoursesController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final MyCoursesScreen userView;
    public List<ModuleData> myModulesList = new ArrayList<>();

    public MyCoursesController(MyCoursesScreen userView) {
        this.userView = userView;
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getMyModulesList();
    }

    public final void getMyModulesList() {
        try {
            myModulesList = moduleDAO.getAllTeacherModules(TeacherSession.getCurrentUser().getId());
        } catch (Exception ex) {
            myModulesList = new ArrayList<>();
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
            vidyalaya.Controller.Routine.Teacher.RoutineController routineController = new vidyalaya.Controller.Routine.Teacher.RoutineController(routineView);
            Utils.closeAllFrames();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Teacher.NoticesController noticesController = new vidyalaya.Controller.Notices.Teacher.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
        }
    }

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Teacher.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Teacher.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            vidyalaya.Controller.Settings.Teacher.SettingsController settingsController = new vidyalaya.Controller.Settings.Teacher.SettingsController(settingsView);
            Utils.closeAllFrames();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = Utils.confirm(userView, "Are you sure you want to logout?");
            if (result == JOptionPane.YES_OPTION) {
                UserLogin studentLoginView = new UserLogin();
                UserLoginController studentLoginController = new UserLoginController(studentLoginView);
                Utils.closeAllFrames();
                studentLoginController.open();
            }
        }
    }
}

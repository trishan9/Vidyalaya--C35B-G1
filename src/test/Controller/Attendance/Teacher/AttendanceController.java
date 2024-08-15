/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Attendance.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.ModuleData;
import vidyalaya.SessionManagement.TeacherSession;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.View.UserLogin;
import vidyalaya.View.Dashboard.Teacher.AttendanceScreen;
import vidyalaya.View.Dashboard.Teacher.MyCoursesScreen;
import vidyalaya.View.Dashboard.Teacher.NoticesScreen;
import vidyalaya.View.Dashboard.Teacher.RoutineScreen;
import vidyalaya.View.Dashboard.Teacher.SettingsScreen;

import vidyalaya.Controller.Users.UserLoginController;

/**
 *
 * @author trish
 */
public class AttendanceController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final AttendanceScreen userView;
    public List<ModuleData> myModulesList = new ArrayList<>();

    public AttendanceController(AttendanceScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
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

    class CoursesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyCoursesScreen coursesView = new MyCoursesScreen();
            vidyalaya.Controller.Courses.Teacher.MyCoursesController coursesController = new vidyalaya.Controller.Courses.Teacher.MyCoursesController(coursesView);
            Utils.closeAllFrames();
            coursesController.open();
        }
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
            UserLogin userLoginView = new UserLogin();
            UserLoginController userLoginController = new UserLoginController(userLoginView);
            Utils.closeAllFrames();
            userLoginController.open();
        }
    }
}

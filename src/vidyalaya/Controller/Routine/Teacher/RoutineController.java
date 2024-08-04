/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Routine.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import vidyalaya.Controller.Users.UserLoginController;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;
import vidyalaya.DAO.RoutineDAO.RoutineDAO;
import vidyalaya.DAO.RoutineDAO.RoutineDAOImplementation;
import vidyalaya.Model.RoutineData;
import vidyalaya.SessionManagement.TeacherSession;

import vidyalaya.Utils.Utils;

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
public class RoutineController {

    private final RoutineDAO routineDAO = new RoutineDAOImplementation();
    private final RoutineScreen userView;
    public List<RoutineData> myRoutinesList = new ArrayList<>();

    public RoutineController(RoutineScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getMyModulesList();
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void getMyModulesList() {
        try {
            myRoutinesList = routineDAO.getAllRoutinesByTeacher(TeacherSession.getCurrentUser().getId());
        } catch (Exception ex) {
            myRoutinesList = new ArrayList<>();
        }
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
            UserLogin userLoginView = new UserLogin();
            UserLoginController userLoginController = new UserLoginController(userLoginView);
            Utils.closeAllFrames();
            userLoginController.open();
        }
    }
}

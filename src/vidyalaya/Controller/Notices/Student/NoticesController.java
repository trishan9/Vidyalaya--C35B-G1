/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Notices.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.NoticeData;
import vidyalaya.SessionManagement.StudentSession;

import vidyalaya.DAO.NoticeDAO.NoticeDAO;
import vidyalaya.DAO.NoticeDAO.NoticeDAOImplementation;

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
public class NoticesController {

    private final NoticeDAO noticeDAO = new NoticeDAOImplementation();
    private final NoticesScreen userView;
    public List<NoticeData> myNoticesList = new ArrayList<>();

    public NoticesController(NoticesScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getMyNoticesList();
    }

    public final void getMyNoticesList() {
        try {
            myNoticesList = noticeDAO.getNoticesByType(StudentSession.getCurrentUser().getAdminId(), "student");
        } catch (Exception ex) {
            myNoticesList = new ArrayList<>();
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
            int result = Utils.confirm(userView, "Are you sure you want to logout?");
            if (result == JOptionPane.YES_OPTION) {
                UserLogin userLoginView = new UserLogin();
                UserLoginController userLoginController = new UserLoginController(userLoginView);
                Utils.closeAllFrames();
                userLoginController.open();
            }
        }
    }
}

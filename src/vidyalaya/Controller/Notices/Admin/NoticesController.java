/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Notices.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.NoticeData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.NoticeDAO.NoticeDAO;
import vidyalaya.DAO.NoticeDAO.NoticeDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;
import vidyalaya.Components.Modals.CreateNoticeForm;

import vidyalaya.Controller.AdminLoginController;
import vidyalaya.View.Dashboard.Admin.DashboardScreen;

/**
 *
 * @author trish
 */
public class NoticesController {

    private final NoticeDAO noticeDAO = new NoticeDAOImplementation();
    private final NoticesScreen userView;
    public List<NoticeData> noticesList = new ArrayList<>();

    public NoticesController(NoticesScreen userView) {
        this.userView = userView;
        userView.addCreateNoticeListener(new CreateNoticeListener());
        userView.addDashboardRedirectListener(new DashboardRedirectListener());
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
        getNoticesList();
    }

    public final void getNoticesList() {
        try {
            noticesList = noticeDAO.getAllNotices(AdminSession.getCurrentUser().getId());
        } catch (Exception ex) {
            noticesList = new ArrayList<>();
        }
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CreateNoticeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateNoticeForm createNoticeView = new CreateNoticeForm();
            CreateNoticeController createNoticeController = new CreateNoticeController(createNoticeView);
            createNoticeController.open();
        }
    }

    public final void deleteNoticeById(int noticeId) {
        try {
            int result = Utils.confirm(userView, "Are you sure you want to delete this notice?");
            if (result == JOptionPane.YES_OPTION) {
                noticeDAO.deleteNotice(noticeId);

                vidyalaya.View.Dashboard.Admin.NoticesScreen noticesView = new vidyalaya.View.Dashboard.Admin.NoticesScreen();
                vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
                Utils.closeAllFrames();
                noticesController.open();
                Utils.success("Notice deleted successfully!");
            }
        } catch (Exception ex) {
            Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(ex.getMessage());
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

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Admin.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Admin.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
        }
    }

    class UsersRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UsersScreen usersView = new UsersScreen();
            vidyalaya.Controller.Users.UsersController usersController = new vidyalaya.Controller.Users.UsersController(usersView);
            Utils.closeAllFrames();
            usersController.open();
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

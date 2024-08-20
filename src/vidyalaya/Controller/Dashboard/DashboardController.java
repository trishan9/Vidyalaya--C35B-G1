/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Dashboard;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import vidyalaya.Utils.Utils;

import vidyalaya.DAO.AttendanceDAO.AttendanceDAOImplementation;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;
import vidyalaya.Model.ModelChart;
import vidyalaya.Model.ModelPieChart;
import vidyalaya.Model.StudentData;
import vidyalaya.Model.TeacherData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.View.Dashboard.Admin.DashboardScreen;
import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;
import vidyalaya.Components.Charts.PieChart;

import vidyalaya.Controller.AdminLoginController;

/**
 *
 * @author trishan9
 */
public class DashboardController {

    private final DashboardScreen userView;

    AuthDAOImplementation authDAO = new AuthDAOImplementation();
    ModuleDAOImplementation moduleDAO = new ModuleDAOImplementation();
    AttendanceDAOImplementation attendanceDAO = new AttendanceDAOImplementation();

    public DashboardController(DashboardScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());

        fetchCharts();

    }

    private void fetchCharts() {
        try {
            List<StudentData> studentsList = authDAO.getAllStudents();
            List<TeacherData> teachersList = authDAO.getAllTeachers();
            Map<String, Integer> moduleCounts = moduleDAO.getModuleCounts(AdminSession.getCurrentUser().getId());
            Map<String, Integer> last7DaysData = attendanceDAO.getLast7DaysAttendanceData();
            this.userView.getDonutChart().setChartType(PieChart.PeiChartType.DONUT_CHART);
            this.userView.getDonutChart().addData(new ModelPieChart("Teacher", teachersList.size(), new Color(23, 126, 238)));
            this.userView.getDonutChart().addData(new ModelPieChart("Student", studentsList.size(), new Color(221, 65, 65)));

            this.userView.getPieChart().setChartType(PieChart.PeiChartType.DEFAULT);
            this.userView.getPieChart().addData(new ModelPieChart("With Teachers", moduleCounts.get("modules_with_teacher"), new Color(47, 157, 64)));
            this.userView.getPieChart().addData(new ModelPieChart("Without Teachers", moduleCounts.get("modules_without_teacher"), new Color(196, 151, 58)));

            this.userView.getLineChart().setTitle("Last 7 Days Attendance Data");
            this.userView.getLineChart().addLegend("Present Students", Color.decode("#e65c00"), Color.decode("#F9D423"));
            this.userView.getLineChart().addLegend("Absent Students", Color.decode("#0099F7"), Color.decode("#F11712"));
            this.userView.getLineChart().clear();
            for (Map.Entry<String, Integer> entry : last7DaysData.entrySet()) {
                String date = entry.getKey();
                int totalPresentStudents = entry.getValue();
                int totalAbsentStudents = studentsList.size() - totalPresentStudents;
                if (totalAbsentStudents < 0) {
                    totalAbsentStudents = 0;
                }
                this.userView.getLineChart().addData(new ModelChart(date, new double[]{totalPresentStudents, totalAbsentStudents}));
            }
            this.userView.getLineChart().start();
        } catch (Exception ex) {
            Utils.error(ex.getMessage());
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

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
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
            int result = Utils.confirm(userView, "Are you sure you want to logout?");
            if (result == JOptionPane.YES_OPTION) {
                AdminLogin adminLoginView = new AdminLogin();
                AdminLoginController adminLoginController = new AdminLoginController(adminLoginView);
                Utils.closeAllFrames();
                adminLoginController.open();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AttendanceDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vidyalaya.Database.MySqlConnection;
import vidyalaya.Model.AttendanceData;
import vidyalaya.SessionManagement.AdminSession;
import vidyalaya.SessionManagement.StudentSession;
import vidyalaya.SessionManagement.TeacherSession;
import vidyalaya.Utils.Utils;

/**
 *
 * @author trish
 */
public class AttendanceDAOImplementation implements AttendanceDAO {

    MySqlConnection mysql = new MySqlConnection();

    @Override
    public void markAttendance(int studentId, int courseId, Date attendanceDate) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement checkStatement = dbConnection.prepareStatement(
                "SELECT * FROM attendance WHERE student_id = ? AND module_code = ? AND attendance_date = ?"
        );
        checkStatement.setInt(1, studentId);
        checkStatement.setInt(2, courseId);
        checkStatement.setDate(3, (java.sql.Date) attendanceDate);

        ResultSet result = checkStatement.executeQuery();
        if (result.next()) {
            Utils.warning("Attendance already marked for today.");
        } else {
            final PreparedStatement insertStatement = dbConnection.prepareStatement(
                    "INSERT INTO attendance (admin_id, student_id, module_code, attendance_date) VALUES (?, ?, ?, ?)"
            );
            insertStatement.setInt(1, TeacherSession.getCurrentUser().getAdminId());
            insertStatement.setInt(2, studentId);
            insertStatement.setInt(3, courseId);
            insertStatement.setDate(4, (java.sql.Date) attendanceDate);
            insertStatement.executeUpdate();
            Utils.success("Attendance marked successfully.");
        }

        checkStatement.close();
        dbConnection.close();
    }

    @Override
    public List<AttendanceData> viewAttendanceByStudentAndCourse(int studentId, int courseId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT * FROM attendance WHERE student_id = ? AND module_code = ?"
        );
        statement.setInt(1, studentId);
        statement.setInt(2, courseId);

        List<AttendanceData> attendanceList = new ArrayList<>();
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            attendanceList.add(new AttendanceData(result));
        }

        statement.close();
        dbConnection.close();
        return attendanceList;
    }

    @Override
    public int getCourseTaughtDays(int courseId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(DISTINCT attendance_date) AS total_days "
                + "FROM attendance WHERE module_code = ?"
        );
        statement.setInt(1, courseId);

        int totalDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            totalDays = result.getInt("total_days");
        }

        statement.close();
        dbConnection.close();
        return totalDays;
    }

    @Override
    public int getCourseAbsentDays(int studentId, int courseId) throws Exception {
        int totalTaughtDays = getCourseTaughtDays(courseId);

        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(DISTINCT attendance_date) AS attended_days "
                + "FROM attendance WHERE student_id = ? AND module_code = ?"
        );
        statement.setInt(1, studentId);
        statement.setInt(2, courseId);

        int attendedDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            attendedDays = result.getInt("attended_days");
        }

        statement.close();
        dbConnection.close();
        return totalTaughtDays - attendedDays;
    }

    @Override
    public int getTotalTaughtDaysAcrossAllCourses() throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(DISTINCT attendance_date) AS total_days "
                + "FROM attendance WHERE admin_id = ?"
        );
        statement.setInt(1, AdminSession.getCurrentUser().getId());

        int totalDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            totalDays = result.getInt("total_days");
        }

        statement.close();
        dbConnection.close();
        return totalDays;
    }

    @Override
    public int getTotalAbsentDaysAcrossAllCourses() throws Exception {
        int totalTaughtDays = getTotalTaughtDaysAcrossAllCourses();

        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(*) AS attended_days FROM attendance WHERE admin_id = ?"
        );
        statement.setInt(1, AdminSession.getCurrentUser().getId());

        int attendedDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            attendedDays = result.getInt("attended_days");
        }

        statement.close();
        dbConnection.close();
        return totalTaughtDays - attendedDays;
    }

    @Override
    public int getStudentTaughtDaysAcrossAllCourses(int studentId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(DISTINCT attendance_date) AS total_days "
                + "FROM attendance WHERE admin_id = ? AND student_id = ?"
        );
        statement.setInt(1, StudentSession.getCurrentUser().getAdminId());
        statement.setInt(2, StudentSession.getCurrentUser().getId());

        int totalDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            totalDays = result.getInt("total_days");
        }

        statement.close();
        dbConnection.close();
        return totalDays;
    }

    @Override
    public int getStudentAbsentDaysAcrossAllCourses(int studentId) throws Exception {
        int totalTaughtDays = getTotalTaughtDaysAcrossAllCourses();

        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(*) AS attended_days FROM attendance WHERE admin_id = ? AND student_id = ?"
        );
        statement.setInt(1, StudentSession.getCurrentUser().getAdminId());
        statement.setInt(2, StudentSession.getCurrentUser().getId());

        int attendedDays = 0;
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            attendedDays = result.getInt("attended_days");
        }

        statement.close();
        dbConnection.close();
        return totalTaughtDays - attendedDays;
    }

    @Override
    public List<AttendanceData> getAttendanceOfStudentAndCourseWithDateRange(int studentId, int courseId, Date startDate, Date endDate) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT * FROM attendance WHERE student_id = ? AND module_code = ? AND attendance_date BETWEEN ? AND ?"
        );
        statement.setInt(1, studentId);
        statement.setInt(2, courseId);
        statement.setDate(3, (java.sql.Date) startDate);
        statement.setDate(4, (java.sql.Date) endDate);

        List<AttendanceData> attendanceList = new ArrayList<>();
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            attendanceList.add(new AttendanceData(result));
        }

        statement.close();
        dbConnection.close();
        return attendanceList;
    }
}

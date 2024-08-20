/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AttendanceDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import vidyalaya.Database.MySqlConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import vidyalaya.Utils.Utils;

import vidyalaya.Model.AttendanceData;
import vidyalaya.SessionManagement.AdminSession;
import vidyalaya.SessionManagement.StudentSession;
import vidyalaya.SessionManagement.TeacherSession;

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
        checkStatement.setDate(3, new java.sql.Date(attendanceDate.getTime()));

        ResultSet result = checkStatement.executeQuery();
        if (result.next()) {
            Utils.warning("Attendance already marked!");
            return;
        } else {
            final PreparedStatement insertStatement = dbConnection.prepareStatement(
                    "INSERT INTO attendance (admin_id, student_id, module_code, attendance_date) VALUES (?, ?, ?, ?)"
            );
            insertStatement.setInt(1, TeacherSession.getCurrentUser().getAdminId());
            insertStatement.setInt(2, studentId);
            insertStatement.setInt(3, courseId);
            insertStatement.setDate(4, new java.sql.Date(attendanceDate.getTime()));
            insertStatement.executeUpdate();
            Utils.success("Attendance marked successfully.");
        }

        checkStatement.close();
        dbConnection.close();
    }

    @Override
    public List<AttendanceData> getAttendanceOfStudentAndCourseWithDateRange(int courseId, String startDate, String endDate) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT a.*, s.id as student_id, s.name as student_name "
                + "FROM attendance a "
                + "JOIN student s ON a.student_id = s.id "
                + "WHERE a.module_code = ? AND a.attendance_date BETWEEN ? AND ?"
        );
        statement.setInt(1, courseId);
        statement.setString(2, startDate);
        statement.setString(3, endDate);

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

    public int getTotalTaughtDaysAcrossAllCourses(int adminId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT COUNT(DISTINCT attendance_date) AS total_days "
                + "FROM attendance WHERE admin_id = ?"
        );
        statement.setInt(1, adminId);

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
    public int getStudentAbsentDaysAcrossAllCourses() throws Exception {
        int totalTaughtDays = getTotalTaughtDaysAcrossAllCourses(StudentSession.getCurrentUser().getAdminId());

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

        int absentDays = totalTaughtDays - attendedDays;
        if (absentDays < 0) {
            return 0;
        }
        return absentDays;
    }

    public Map<String, Integer> getLast7DaysAttendanceData() throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "SELECT attendance_date, "
                + "SUM(CASE WHEN attended = 1 THEN 1 ELSE 0 END) AS present_students "
                + "FROM attendance "
                + "WHERE attendance_date >= ? "
                + "GROUP BY attendance_date "
                + "ORDER BY attendance_date DESC"
        );

        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        statement.setDate(1, java.sql.Date.valueOf(sevenDaysAgo).valueOf(sevenDaysAgo));

        ResultSet result = statement.executeQuery();
        Map<String, Integer> attendanceData = new TreeMap<>();

        while (result.next()) {
            String date = result.getDate("attendance_date").toString();
            int presentStudents = result.getInt("present_students");
            attendanceData.put(date, presentStudents);
        }

        statement.close();
        dbConnection.close();
        return attendanceData;
    }
}

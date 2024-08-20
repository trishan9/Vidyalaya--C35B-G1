/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vidyalaya.DAO.AttendanceDAO;

import java.util.Date;
import java.util.List;

import vidyalaya.Model.AttendanceData;

/**
 *
 * @author trish
 */
public interface AttendanceDAO {

    public void markAttendance(int studentId, int courseId, Date attendanceDate) throws Exception;

    public List<AttendanceData> getAttendanceOfStudentAndCourseWithDateRange(int courseId, String startDate, String endDate) throws Exception;

    public int getCourseTaughtDays(int courseId) throws Exception;

    public int getCourseAbsentDays(int studentId, int courseId) throws Exception;

    public int getTotalTaughtDaysAcrossAllCourses() throws Exception;

    public int getStudentAbsentDaysAcrossAllCourses() throws Exception;
}

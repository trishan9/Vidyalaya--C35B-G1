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

    public List<AttendanceData> viewAttendanceByStudentAndCourse(int studentId, int courseId) throws Exception;

    public int getCourseTaughtDays(int courseId) throws Exception;

    public int getCourseAbsentDays(int studentId, int courseId) throws Exception;

    public int getTotalTaughtDaysAcrossAllCourses() throws Exception;

    public int getTotalAbsentDaysAcrossAllCourses() throws Exception;

    public int getStudentTaughtDaysAcrossAllCourses(int studentId) throws Exception;

    public int getStudentAbsentDaysAcrossAllCourses(int studentId) throws Exception;

    public List<AttendanceData> getAttendanceOfStudentAndCourseWithDateRange(int studentId, int courseId, Date startDate, Date endDate) throws Exception;
}

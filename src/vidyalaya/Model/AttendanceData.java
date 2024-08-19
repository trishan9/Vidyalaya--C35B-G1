/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author trishan9
 */
public class AttendanceData {

    private int id;
    private int student_id;
    private int module_code;
    private Date attendance_date;
    private String student_name;

    public AttendanceData(int student_id, int module_code, Date attendance_date) {
        this.student_id = student_id;
        this.module_code = module_code;
        this.attendance_date = attendance_date;
    }

    public AttendanceData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.student_id = result.getInt("student_id");
        this.module_code = result.getInt("module_code");
        this.attendance_date = result.getDate("attendance_date");
        this.student_name = result.getString("student_name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getModuleCode() {
        return module_code;
    }

    public void setModuleCode(int module_code) {
        this.module_code = module_code;
    }

    public Date getAttendanceDate() {
        return attendance_date;
    }

    public void setAttendanceDate(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getStudentName() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }
}

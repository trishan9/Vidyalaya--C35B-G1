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
    private boolean is_present;

    public AttendanceData(int student_id, int module_code, Date attendance_date, boolean is_present) {
        this.student_id = student_id;
        this.module_code = module_code;
        this.attendance_date = attendance_date;
        this.is_present = is_present;
    }

    public AttendanceData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.student_id = result.getInt("student_id");
        this.module_code = result.getInt("module_code");
        this.attendance_date = result.getDate("attendance_date");
        this.is_present = result.getBoolean("is_present");
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

    public boolean getIsPresent() {
        return is_present;
    }

    public void setIsPresent(boolean is_present) {
        this.is_present = is_present;
    }
}

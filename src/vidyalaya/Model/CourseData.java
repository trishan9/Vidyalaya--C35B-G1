/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author trishan9
 */
public class CourseData {

    private int id;
    private int admin_id;
    private String name;
    private String faculty;

    public CourseData(int admin_id, String name, String faculty) {
        this.admin_id = admin_id;
        this.name = name;
        this.faculty = faculty;
    }

    public CourseData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.admin_id = result.getInt("admin_id");
        this.name = result.getString("name");
        this.faculty = result.getString("faculty");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return admin_id;
    }

    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

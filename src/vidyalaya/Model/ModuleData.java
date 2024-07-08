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
public class ModuleData {

    private int code;
    private int course_id;
    private String name;

    public ModuleData(int course_id, String name) {
        this.course_id = course_id;
        this.name = name;
    }

    public ModuleData(ResultSet result) throws SQLException {
        this.code = result.getInt("code");
        this.course_id = result.getInt("course_id");
        this.name = result.getString("name");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCourseId() {
        return course_id;
    }

    public void setCourseId(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

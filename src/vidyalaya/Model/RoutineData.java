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
public class RoutineData {

    private int id;
    private String weekday;
    private int module_code;
    private String time;
    private String routine_content;

    public RoutineData(String weekday, int module_code, String time, String routine_content) {
        this.weekday = weekday;
        this.module_code = module_code;
        this.time = time;
        this.routine_content = routine_content;
    }

    public RoutineData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.weekday = result.getString("weekday");
        this.module_code = result.getInt("module_code");
        this.time = result.getString("time");
        this.routine_content = result.getString("routine_content");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getModuleCode() {
        return module_code;
    }

    public void setModuleCode(int module_code) {
        this.module_code = module_code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoutineContent() {
        return routine_content;
    }

    public void setRoutineContent(String routine_content) {
        this.routine_content = routine_content;
    }
}

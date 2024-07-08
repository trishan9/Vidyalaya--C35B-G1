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
    private int module_code;
    private String routine_content;

    public RoutineData(int module_code, String routine_content) {
        this.module_code = module_code;
        this.routine_content = routine_content;
    }

    public RoutineData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.module_code = result.getInt("module_code");
        this.routine_content = result.getString("routine_content");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleCode() {
        return module_code;
    }

    public void setModuleCode(int module_code) {
        this.module_code = module_code;
    }

    public String getRoutineContent() {
        return routine_content;
    }

    public void setRoutineContent(String routine_content) {
        this.routine_content = routine_content;
    }
}

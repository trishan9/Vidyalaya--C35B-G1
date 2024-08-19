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
public class ModuleTeacherData {

    private int teacher_id;
    private int module_code;

    public ModuleTeacherData(int teacher_id, int module_code) {
        this.teacher_id = teacher_id;
        this.module_code = module_code;
    }

    public ModuleTeacherData(ResultSet result) throws SQLException {
        this.teacher_id = result.getInt("teacher_id");
        this.module_code = result.getInt("module_code");
    }

    public int getTeacherId() {
        return teacher_id;
    }

    public void setTeacherId(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getModuleCode() {
        return module_code;
    }

    public void setModuleCode(int module_code) {
        this.module_code = module_code;
    }
}

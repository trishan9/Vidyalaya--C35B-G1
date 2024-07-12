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
    private int admin_id;
    private String name;

    public ModuleData(int admin_id, String name) {
        this.admin_id = admin_id;
        this.name = name;
    }

    public ModuleData(ResultSet result) throws SQLException {
        this.code = result.getInt("code");
        this.admin_id = result.getInt("admin_id");
        this.name = result.getString("name");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}

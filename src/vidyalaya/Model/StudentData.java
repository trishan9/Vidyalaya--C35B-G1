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
public class StudentData implements UserData {

    private int id;
    private String student_id;
    private int admin_id;
    private String name;
    private String email;
    private String password;

    public StudentData(int admin_id, String name, String email, String password) {
        this.admin_id = admin_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public StudentData(int admin_id, String name, String email) {
        this.admin_id = admin_id;
        this.name = name;
        this.email = email;
    }

    public StudentData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public StudentData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.student_id = result.getString("student_id");
        this.admin_id = result.getInt("admin_id");
        this.name = result.getString("name");
        this.email = result.getString("email");
        this.password = result.getString("password");
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public int getAdminId() {
        return admin_id;
    }

    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

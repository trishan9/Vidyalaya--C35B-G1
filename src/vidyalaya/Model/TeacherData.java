/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Objects;

/**
 *
 * @author trishan9
 */
public class TeacherData implements UserData {

    private int id;
    private String teacher_id;
    private int admin_id;
    private String name;
    private String email;
    private String password;

    public TeacherData(int admin_id, String name, String email, String password) {
        this.admin_id = admin_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public TeacherData(int admin_id, String name, String email) {
        this.admin_id = admin_id;
        this.name = name;
        this.email = email;
    }

    public TeacherData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.teacher_id = result.getString("teacher_id");
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

    public String getTeacherId() {
        return teacher_id;
    }

    public void setTeacherId(String teacher_id) {
        this.teacher_id = teacher_id;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherData that = (TeacherData) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

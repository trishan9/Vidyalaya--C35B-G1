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
public class AdminData {

    private int id;
    private String name;
    private String email;
    private String username;
    private String institution_name;
    private String password;
    final public boolean is_admin;

    public AdminData(String name, String email, String institution_name, String password) {
        this.name = name;
        this.email = email;
        this.institution_name = institution_name;
        this.password = password;
        this.is_admin = true;
    }

    public AdminData(String name, String email, String institution_name) {
        this.name = name;
        this.email = email;
        this.institution_name = institution_name;
        this.is_admin = true;
    }

    public AdminData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.name = result.getString("name");
        this.email = result.getString("email");
        this.username = result.getString("username");
        this.institution_name = result.getString("institution_name");
        this.password = result.getString("password");
        this.is_admin = result.getBoolean("is_admin");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInstitutionName() {
        return institution_name;
    }

    public void setInstitutionName(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AuthDAO;

import vidyalaya.Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.LoginRequest;

/**
 *
 * @author trish
 */
public class AuthDAOImplementation implements AuthDAO {

    MySqlConnection mysql = new MySqlConnection();

    @Override
    public AdminData loginAdmin(LoginRequest loginModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM admin where username = ? AND password = ? LIMIT 1");
        statement.setString(1, loginModel.getUsername());
        statement.setString(2, loginModel.getPassword());

        try {
            boolean doesExist = checkIfAdminExistsByUsername(dbConnection, loginModel.getUsername());
            if (!doesExist) {
                throw new Exception("Admin with username " + loginModel.getUsername() + " doesn't exists");
            }

            final var response = statement.executeQuery();
            if (response.next()) {
                return new AdminData(response);
            }
            statement.close();
            throw new Exception("Password is invalid, Please try again!");
        } catch (Exception ex) {
            throw ex;
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void registerAdmin(AdminData registerModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO admin (name,email,institution_name,password) VALUES (?,?,?,?)");
        statement.setString(1, registerModel.getName());
        statement.setString(2, registerModel.getEmail());
        statement.setString(3, registerModel.getInstitutionName());
        statement.setString(4, registerModel.getPassword());

        try {
            boolean doesExist = checkIfAdminExistsByEmail(dbConnection, registerModel.getEmail());
            if (doesExist) {
                throw new Exception("Admin with email " + registerModel.getEmail()+ " already exists");
            }

            statement.execute();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void deleteUser(int userId) throws Exception {
    }

    private boolean checkIfAdminExistsByEmail(Connection dbConnection, String email) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(id) as count FROM admin where email = ? LIMIT 1")) {
            statement.setString(1, email);
            final var response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }

    private boolean checkIfAdminExistsByUsername(Connection dbConnection, String username) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(id) as count FROM admin where username = ? LIMIT 1")) {
            statement.setString(1, username);
            final var response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }
}

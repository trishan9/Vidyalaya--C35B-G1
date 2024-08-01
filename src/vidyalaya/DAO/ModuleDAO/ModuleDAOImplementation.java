/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.ModuleDAO;

import vidyalaya.Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vidyalaya.Model.ModuleData;

import vidyalaya.SessionManagement.AdminSession;

/**
 *
 * @author trish
 */
public class ModuleDAOImplementation implements ModuleDAO {

    MySqlConnection mysql = new MySqlConnection();

    @Override
    public void createModule(ModuleData moduleModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO module (admin_id,name) VALUES (?,?)");
        statement.setInt(1, moduleModel.getAdminId());
        statement.setString(2, moduleModel.getName());

        try {
            boolean doesExist = checkIfModuleExists(dbConnection, "name", moduleModel.getName(), moduleModel.getAdminId());
            if (doesExist) {
                throw new Exception("Course with name " + moduleModel.getName() + " already exists");
            }

            statement.execute();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<ModuleData> getAllModules() throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM module WHERE admin_id = ?");
        statement.setInt(1, AdminSession.getCurrentUser().getId());

        try {
            List<ModuleData> moduleData = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    moduleData.add(new ModuleData(data));
                }
            }
            statement.close();
            return moduleData;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public ModuleData getModuleByCode(int moduleCode) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "SELECT * FROM module WHERE code = ? LIMIT 1";

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setInt(1, moduleCode);

            try (ResultSet data = statement.executeQuery()) {
                if (data.next()) {
                    return new ModuleData(data);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void updateModule(int moduleCode, ModuleData moduleModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE module SET name = ? WHERE code = ?");
        statement.setString(1, moduleModel.getName());
        statement.setInt(2, moduleCode);

        try {
            boolean doesExist = checkIfModuleExists(dbConnection, "code", moduleCode);
            if (!doesExist) {
                throw new Exception("Course with code " + moduleCode + " doesn't exist");
            }

            statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void deleteModule(int moduleCode) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM module WHERE code = ?");
        statement.setInt(1, moduleCode);

        try {
            boolean doesExist = checkIfModuleExists(dbConnection, "code", moduleCode);
            if (!doesExist) {
                throw new Exception("Course with code " + moduleCode + " doesn't exist");
            }

            statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    private boolean checkIfModuleExists(Connection dbConnection, String columnName, String value, int adminId) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(code) as count FROM module" + " WHERE admin_id = ? AND " + columnName + " = ? LIMIT 1")) {
            statement.setInt(1, adminId);
            statement.setString(2, value);
            final ResultSet response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }

    private boolean checkIfModuleExists(Connection dbConnection, String columnName, int value) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(code) as count FROM module" + " WHERE " + columnName + " = ? LIMIT 1")) {
            statement.setInt(1, value);
            final ResultSet response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }

}

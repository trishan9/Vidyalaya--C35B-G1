/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.ModuleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vidyalaya.Database.MySqlConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vidyalaya.Model.MaterialData;
import vidyalaya.Model.ModuleData;
import vidyalaya.Model.TeacherData;
import vidyalaya.SessionManagement.TeacherSession;

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
    public List<ModuleData> getAllModules(int adminId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM module WHERE admin_id = ?");
        statement.setInt(1, adminId);

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
    public List<TeacherData> getAllModuleTeachers(int moduleCode) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String sql = "SELECT t.* FROM teacher t "
                + "JOIN module_teacher mt ON t.id = mt.teacher_id "
                + "WHERE mt.module_code = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, moduleCode);

        try {
            List<TeacherData> teacherDataList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    teacherDataList.add(new TeacherData(data));
                }
            }
            return teacherDataList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            statement.close();
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<ModuleData> getAllTeacherModules(int teacherId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String sql = "SELECT m.* FROM module m "
                + "JOIN module_teacher mt ON m.code = mt.module_code "
                + "WHERE mt.teacher_id = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, teacherId);

        try {
            List<ModuleData> moduleDataList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    ModuleData module = new ModuleData(data);
                    moduleDataList.add(module);
                }
            }
            statement.close();
            return moduleDataList;
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
    public void createOrUpdateModuleTeacher(int moduleCode, int teacherId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO module_teacher (teacher_id, module_code) VALUES (?, ?) "
                + "ON DUPLICATE KEY UPDATE teacher_id = VALUES(teacher_id)");

        statement.setInt(1, teacherId);
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

    @Override
    public void deleteModuleTeachers(int moduleCode) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "DELETE FROM module_teacher WHERE module_code = ?");

        statement.setInt(1, moduleCode);

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void createMaterial(MaterialData materialData) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "INSERT INTO material (module_code, uploader_id, material_title, material_text) VALUES (?, ?, ?, ?)";
        final PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setInt(1, materialData.getModuleCode());
        statement.setInt(2, TeacherSession.getCurrentUser().getId());
        statement.setString(3, materialData.getMaterialTitle());
        statement.setString(4, materialData.getMaterialText());

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<MaterialData> getAllMaterials(int moduleCode) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "SELECT * FROM material WHERE module_code = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setInt(1, moduleCode);

        try {
            List<MaterialData> materials = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                materials.add(new MaterialData(result));
            }
            statement.close();
            return materials;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void updateMaterial(int materialId, MaterialData materialData) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "UPDATE material SET material_title = ?, material_text = ? WHERE id = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, materialData.getMaterialTitle());
        statement.setString(2, materialData.getMaterialText());
        statement.setInt(3, materialId);

        try {
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new Exception("Material not found or you do not have permission to update this material");
            }
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void deleteMaterial(int materialId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "DELETE FROM material WHERE id = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setInt(1, materialId);

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    public Map<String, Integer> getModuleCounts(int adminId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String sql = "SELECT "
                + "(SELECT COUNT(*) FROM module m LEFT JOIN module_teacher mt ON m.code = mt.module_code WHERE mt.module_code IS NOT NULL AND m.admin_id = ?) AS modules_with_teacher, "
                + "(SELECT COUNT(*) FROM module m LEFT JOIN module_teacher mt ON m.code = mt.module_code WHERE mt.module_code IS NULL AND m.admin_id = ?) AS modules_without_teacher";

        final PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, adminId);
        statement.setInt(2, adminId);

        try {
            Map<String, Integer> moduleCounts = new HashMap<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    moduleCounts.put("modules_with_teacher", resultSet.getInt("modules_with_teacher"));
                    moduleCounts.put("modules_without_teacher", resultSet.getInt("modules_without_teacher"));
                }
            }
            return moduleCounts;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            statement.close();
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

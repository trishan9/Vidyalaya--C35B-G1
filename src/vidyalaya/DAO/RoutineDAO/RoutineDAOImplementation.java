/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.RoutineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vidyalaya.Database.MySqlConnection;

import java.util.ArrayList;
import java.util.List;

import vidyalaya.Model.RoutineData;

/**
 *
 * @author trish
 */
public class RoutineDAOImplementation implements RoutineDAO {

    MySqlConnection mysql = new MySqlConnection();

    @Override
    public void createRoutine(RoutineData routineModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO routine (weekday, module_code, time, routine_content) VALUES (?, ?, ?, ?)");
        statement.setString(1, routineModel.getWeekday());
        statement.setInt(2, routineModel.getModuleCode());
        statement.setString(3, routineModel.getTime());
        statement.setString(4, routineModel.getRoutineContent());

        try {
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<RoutineData> getAllRoutines(int adminId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT r.* FROM routine r JOIN module m ON r.module_code = m.code WHERE m.admin_id = ?");
        statement.setInt(1, adminId);

        try {
            List<RoutineData> routineDataList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    routineDataList.add(new RoutineData(data));
                }
            }
            statement.close();
            return routineDataList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<RoutineData> getAllRoutinesByTeacher(int teacherId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String sql = "SELECT r.* FROM routine r "
                + "JOIN module_teacher mt ON r.module_code = mt.module_code "
                + "WHERE mt.teacher_id = ?";
        final PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, teacherId);

        try {
            List<RoutineData> routineDataList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    routineDataList.add(new RoutineData(data));
                }
            }
            statement.close();
            return routineDataList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public RoutineData getRoutineById(int routineId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final String query = "SELECT * FROM routine WHERE id = ? LIMIT 1";

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setInt(1, routineId);

            try (ResultSet data = statement.executeQuery()) {
                if (data.next()) {
                    return new RoutineData(data);
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
    public void updateRoutine(int routineId, RoutineData routineModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE routine SET weekday = ?, module_code = ?, time = ?, routine_content = ? WHERE id = ?");
        statement.setString(1, routineModel.getWeekday());
        statement.setInt(2, routineModel.getModuleCode());
        statement.setString(3, routineModel.getTime());
        statement.setString(4, routineModel.getRoutineContent());
        statement.setInt(5, routineId);

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
    public void deleteRoutine(int routineId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM routine WHERE id = ?");
        statement.setInt(1, routineId);

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }
}

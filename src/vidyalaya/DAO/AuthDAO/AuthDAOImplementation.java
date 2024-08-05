/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AuthDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vidyalaya.Database.MySqlConnection;

import java.util.ArrayList;
import java.util.List;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.LoginRequest;
import vidyalaya.Model.StudentData;
import vidyalaya.Model.TeacherData;

import vidyalaya.SessionManagement.AdminSession;
import vidyalaya.SessionManagement.StudentSession;
import vidyalaya.SessionManagement.TeacherSession;

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
            boolean doesExist = checkIfUserExists(dbConnection, "admin", "username", loginModel.getUsername());
            if (!doesExist) {
                throw new Exception("Admin with username " + loginModel.getUsername() + " doesn't exists");
            }

            final ResultSet response = statement.executeQuery();
            if (response.next()) {
                AdminData adminData = new AdminData(response);
                AdminSession.setCurrentUser(adminData);
                return adminData;
            }
            statement.close();
            throw new Exception("Password is invalid, Please try again!");
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public TeacherData loginTeacher(LoginRequest loginModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM teacher where teacher_id = ? AND password = ? LIMIT 1");
        statement.setString(1, loginModel.getUsername());
        statement.setString(2, loginModel.getPassword());

        try {
            boolean doesExist = checkIfUserExists(dbConnection, "teacher", "teacher_id", loginModel.getUsername());
            if (!doesExist) {
                throw new Exception("Teacher with teacher id " + loginModel.getUsername() + " doesn't exists");
            }

            final ResultSet response = statement.executeQuery();
            if (response.next()) {
                TeacherData teacherData = new TeacherData(response);
                TeacherSession.setCurrentUser(teacherData);
                return teacherData;
            }
            statement.close();
            throw new Exception("Password is invalid, Please try again!");
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public StudentData loginStudent(LoginRequest loginModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM student where student_id = ? AND password = ? LIMIT 1");
        statement.setString(1, loginModel.getUsername());
        statement.setString(2, loginModel.getPassword());

        try {
            boolean doesExist = checkIfUserExists(dbConnection, "student", "student_id", loginModel.getUsername());
            if (!doesExist) {
                throw new Exception("Student with student id " + loginModel.getUsername() + " doesn't exists");
            }

            final ResultSet response = statement.executeQuery();
            if (response.next()) {
                StudentData studentData = new StudentData(response);
                StudentSession.setCurrentUser(studentData);
                return studentData;
            }
            statement.close();
            throw new Exception("Password is invalid, Please try again!");
        } catch (Exception ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
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
            boolean doesExist = checkIfUserExists(dbConnection, "admin", "email", registerModel.getEmail());
            if (doesExist) {
                throw new Exception("Admin with email " + registerModel.getEmail() + " already exists");
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
    public void registerTeacher(TeacherData registerModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO teacher (admin_id,name,email,password) VALUES (?,?,?,?)");
        statement.setInt(1, registerModel.getAdminId());
        statement.setString(2, registerModel.getName());
        statement.setString(3, registerModel.getEmail());
        statement.setString(4, registerModel.getPassword());

        try {
            boolean doesExist = checkIfUserExists(dbConnection, "teacher", "email", registerModel.getEmail());
            if (doesExist) {
                throw new Exception("Teacher with email " + registerModel.getEmail() + " already exists");
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
    public void registerStudent(StudentData registerModel) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO student (admin_id,name,email,password) VALUES (?,?,?,?)");
        statement.setInt(1, registerModel.getAdminId());
        statement.setString(2, registerModel.getName());
        statement.setString(3, registerModel.getEmail());
        statement.setString(4, registerModel.getPassword());

        try {
            boolean doesExist = checkIfUserExists(dbConnection, "student", "email", registerModel.getEmail());
            if (doesExist) {
                throw new Exception("Student with email " + registerModel.getEmail() + " already exists");
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
    public List<StudentData> getAllStudents() throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM student WHERE admin_id = ?");
        statement.setInt(1, AdminSession.getCurrentUser().getId());

        try {
            List<StudentData> studentData = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    studentData.add(new StudentData(data));
                }
            }
            statement.close();
            return studentData;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<TeacherData> getAllTeachers() throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM teacher WHERE admin_id = ?");
        statement.setInt(1, AdminSession.getCurrentUser().getId());

        try {
            List<TeacherData> teacherData = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    teacherData.add(new TeacherData(data));
                }
            }
            statement.close();
            return teacherData;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void updateTeacher(int teacherId, TeacherData teacher) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE teacher SET name = ?, email = ? WHERE id = ?");
        statement.setString(1, teacher.getName());
        statement.setString(2, teacher.getEmail());
        statement.setInt(3, teacherId);

        try {
            boolean doesExist = checkIfUserExistsById(dbConnection, "teacher", teacherId);
            if (!doesExist) {
                throw new Exception("Teacher doesn't exist");
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
    public void updateStudent(int studentId, StudentData student) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE student SET name = ?, email = ? WHERE id  = ?");
        statement.setString(1, student.getName());
        statement.setString(2, student.getEmail());
        statement.setInt(3, studentId);

        try {
            boolean doesExist = checkIfUserExistsById(dbConnection, "student", studentId);
            if (!doesExist) {
                throw new Exception("Student doesn't exist");
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
    public void updateAdmin(int adminId, AdminData admin) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE admin SET name = ?, email = ?, institution_name = ? WHERE id  = ?");
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getEmail());
        statement.setString(3, admin.getInstitutionName());
        statement.setInt(4, adminId);

        try {
            boolean doesExist = checkIfUserExistsById(dbConnection, "admin", adminId);
            if (!doesExist) {
                throw new Exception("Admin doesn't exist");
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
    public void changePassword(String userType, int userId, String newPassword) throws Exception {
        Connection dbConnection = mysql.openConnection();
        String tableName = userType;

        final PreparedStatement statement = dbConnection.prepareStatement("UPDATE " + tableName + " SET password = ? WHERE id = ?");
        statement.setString(1, newPassword);
        statement.setInt(2, userId);
        try {
            boolean doesExist = checkIfUserExistsById(dbConnection, tableName, userId);
            if (!doesExist) {
                throw new Exception("User with ID " + userId + " doesn't exist in " + tableName + " table");
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
    public void deleteUser(int userId, String userType) throws Exception {
        Connection dbConnection = mysql.openConnection();

        String tableName = userType;

        final PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?");
        statement.setInt(1, userId);

        try {
            boolean doesExist = checkIfUserExistsById(dbConnection, tableName, userId);
            if (!doesExist) {
                throw new Exception("User with ID " + userId + " doesn't exist in " + tableName + " table");
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
    public StudentData getStudentById(int studentId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM student WHERE id = ? LIMIT 1");
        statement.setInt(1, studentId);

        try {
            final ResultSet response = statement.executeQuery();
            if (response.next()) {
                StudentData studentData = new StudentData(response);
                return studentData;
            } else {
                throw new Exception("Student with ID " + studentId + " doesn't exist");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            statement.close();
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public TeacherData getTeacherById(int teacherId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM teacher WHERE id = ? LIMIT 1");
        statement.setInt(1, teacherId);

        try {
            final ResultSet response = statement.executeQuery();
            if (response.next()) {
                TeacherData teacherData = new TeacherData(response);
                return teacherData;
            } else {
                throw new Exception("Teacher with ID " + teacherId + " doesn't exist");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            statement.close();
            mysql.closeConnection(dbConnection);
        }
    }

    private boolean checkIfUserExists(Connection dbConnection, String tableName, String columnName, String value) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(id) as count FROM " + tableName + " WHERE " + columnName + " = ? LIMIT 1")) {
            statement.setString(1, value);
            final ResultSet response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }

    private boolean checkIfUserExistsById(Connection dbConnection, String tableName, int userId) throws SQLException {
        final boolean data;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(id) as count FROM " + tableName + " WHERE id = ? LIMIT 1")) {
            statement.setInt(1, userId);
            final ResultSet response = statement.executeQuery();
            data = (response.next() && response.getInt("count") > 0);
        }
        return data;
    }

}

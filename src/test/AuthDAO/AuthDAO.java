/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AuthDAO;

import java.util.List;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.LoginRequest;
import vidyalaya.Model.StudentData;
import vidyalaya.Model.TeacherData;

/**
 *
 * @author trishan9
 */
public interface AuthDAO {

    public AdminData loginAdmin(LoginRequest loginModel) throws Exception;

    public TeacherData loginTeacher(LoginRequest loginModel) throws Exception;

    public StudentData loginStudent(LoginRequest loginModel) throws Exception;

    public AdminData registerAdmin(AdminData registerModel) throws Exception;

    public TeacherData registerTeacher(TeacherData registerModel) throws Exception;

    public StudentData registerStudent(StudentData registerModel) throws Exception;

    public List<StudentData> getAllStudents() throws Exception;

    public List<TeacherData> getAllTeachers() throws Exception;

    public void updateTeacher(int teacherId, TeacherData teacher) throws Exception;

    public void updateStudent(int studentId, StudentData student) throws Exception;

    public void updateAdmin(int adminId, AdminData admin) throws Exception;

    public void changePassword(String userType, int userId, String newPassword) throws Exception;

    public void deleteUser(int userId, String userType) throws Exception;

    public StudentData getStudentById(int studentId) throws Exception;

    public TeacherData getTeacherById(int teacherId) throws Exception;
}

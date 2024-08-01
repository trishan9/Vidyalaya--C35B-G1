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
import vidyalaya.Model.UserTypeEnum;

/**
 *
 * @author trishan9
 */
public interface AuthDAO {
    
    public AdminData loginAdmin(LoginRequest loginModel) throws Exception;
    
    public TeacherData loginTeacher(LoginRequest loginModel) throws Exception;
    
    public StudentData loginStudent(LoginRequest loginModel) throws Exception;

    public void registerAdmin(AdminData registerModel) throws Exception;
    
    public void registerTeacher(TeacherData registerModel) throws Exception;
    
    public void registerStudent(StudentData registerModel) throws Exception;
    
    public List<StudentData> getAllStudents() throws Exception;
    
    public List<TeacherData> getAllTeachers() throws Exception;
    
    public void deleteUser(int userId, UserTypeEnum userType) throws Exception;
}

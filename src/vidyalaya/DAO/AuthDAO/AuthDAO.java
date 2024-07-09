/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AuthDAO;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.LoginRequest;
import vidyalaya.Model.TeacherData;

/**
 *
 * @author trishan9
 */
public interface AuthDAO {
    
    public AdminData loginAdmin(LoginRequest loginModel) throws Exception;
    
    public TeacherData loginTeacher(LoginRequest loginModel) throws Exception;

    public void registerAdmin(AdminData registerModel) throws Exception;
    
    public void registerTeacher(TeacherData registerModel) throws Exception;

    public void deleteUser(int userId) throws Exception;
}

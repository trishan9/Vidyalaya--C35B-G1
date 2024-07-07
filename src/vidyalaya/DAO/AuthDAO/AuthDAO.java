/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.AuthDAO;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.LoginRequest;

/**
 *
 * @author trishan9
 */
public interface AuthDAO {

//    public AdminData loginUser(LoginRequest loginModel) throws Exception;
    
    public AdminData loginAdmin(LoginRequest loginModel) throws Exception;
    
//    public void registerUser(AdminData registerModel) throws Exception;

    public void registerAdmin(AdminData registerModel) throws Exception;

    public void deleteUser(int userId) throws Exception;
}

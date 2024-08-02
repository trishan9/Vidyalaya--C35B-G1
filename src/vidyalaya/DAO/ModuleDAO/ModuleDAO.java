/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vidyalaya.DAO.ModuleDAO;

import java.util.List;

import vidyalaya.Model.ModuleData;

/**
 *
 * @author trish
 */
public interface ModuleDAO {
        
    public void createModule(ModuleData moduleModel) throws Exception;
            
    public List<ModuleData> getAllModules(int adminId) throws Exception;
    
    public ModuleData getModuleByCode(int moduleCode) throws Exception;
    
    public void updateModule(int moduleCode, ModuleData moduleModel) throws Exception;
    
    public void deleteModule(int moduleCode) throws Exception;
}

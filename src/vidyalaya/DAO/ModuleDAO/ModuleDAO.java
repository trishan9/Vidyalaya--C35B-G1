/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vidyalaya.DAO.ModuleDAO;

import java.util.List;
import vidyalaya.Model.MaterialData;

import vidyalaya.Model.ModuleData;
import vidyalaya.Model.TeacherData;

/**
 *
 * @author trish
 */
public interface ModuleDAO {

    public void createModule(ModuleData moduleModel) throws Exception;

    public List<ModuleData> getAllModules(int adminId) throws Exception;

    public List<TeacherData> getAllModuleTeachers(int moduleCode) throws Exception;

    public List<ModuleData> getAllTeacherModules(int teacherId) throws Exception;

    public ModuleData getModuleByCode(int moduleCode) throws Exception;

    public void updateModule(int moduleCode, ModuleData moduleModel) throws Exception;

    public void createOrUpdateModuleTeacher(int moduleCode, int teacherId) throws Exception;

    public void deleteModuleTeachers(int moduleCode) throws Exception;

    public void deleteModule(int moduleCode) throws Exception;

    public void createMaterial(MaterialData materialData) throws Exception;

    public List<MaterialData> getAllMaterials(int moduleCode) throws Exception;

    public void updateMaterial(int materialId, MaterialData materialData) throws Exception;

    public void deleteMaterial(int materialId) throws Exception;

}

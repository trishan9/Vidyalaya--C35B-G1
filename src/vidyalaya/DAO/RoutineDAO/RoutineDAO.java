/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vidyalaya.DAO.RoutineDAO;

import java.util.List;
import vidyalaya.Model.RoutineData;

/**
 *
 * @author trish
 */
public interface RoutineDAO {

    void createRoutine(RoutineData routineModel) throws Exception;

    List<RoutineData> getAllRoutines(int adminId) throws Exception;

    List<RoutineData> getAllRoutinesByTeacher(int teacherId) throws Exception;

    RoutineData getRoutineById(int routineId) throws Exception;

    void updateRoutine(int routineId, RoutineData routineModel) throws Exception;

    void deleteRoutine(int routineId) throws Exception;
}

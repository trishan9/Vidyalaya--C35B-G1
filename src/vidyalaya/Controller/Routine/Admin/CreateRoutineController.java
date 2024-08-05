/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Routine.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.ModuleData;
import vidyalaya.Model.RoutineData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;
import vidyalaya.DAO.RoutineDAO.RoutineDAO;
import vidyalaya.DAO.RoutineDAO.RoutineDAOImplementation;

import vidyalaya.Components.Modals.CreateRoutineForm;

/**
 *
 * @author trishan9
 */
public class CreateRoutineController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final RoutineDAO routineDAO = new RoutineDAOImplementation();
    private final CreateRoutineForm userView;
    public List<ModuleData> modulesList = new ArrayList<>();

    public CreateRoutineController(CreateRoutineForm userView) {
        this.userView = userView;
        userView.addCreateRoutineListener(new CreateRoutineListener());
        getModulesList();
        populateComboBox(userView.getModule(), modulesList);
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    private void populateComboBox(JComboBox<ModuleData> combo, List<ModuleData> modulesList) {
        DefaultComboBoxModel<ModuleData> model = new DefaultComboBoxModel<>();
        for (ModuleData module : modulesList) {
            model.addElement(module);
        }
        combo.setModel(model);
    }

    public final void getModulesList() {
        try {
            modulesList = moduleDAO.getAllModules(AdminSession.getCurrentUser().getId());
        } catch (Exception ex) {
            modulesList = new ArrayList<>();
        }
    }

    class CreateRoutineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String weekday = userView.getWeekday().getSelectedItem().toString();
                ModuleData moduleData = (ModuleData) userView.getModule().getSelectedItem();
                int moduleCode = moduleData.getCode();
                String time = userView.getTimeField().getText();
                String details = userView.getContentField().getText();
                String content = time + " - " + details;

                RoutineData routine = new RoutineData(weekday, moduleCode, time, content);
                routineDAO.createRoutine(routine);

                vidyalaya.View.Dashboard.Admin.RoutineScreen routineView = new vidyalaya.View.Dashboard.Admin.RoutineScreen();
                vidyalaya.Controller.Routine.Admin.RoutineController routineController = new vidyalaya.Controller.Routine.Admin.RoutineController(routineView);
                Utils.closeAllFrames();
                routineController.open();
                Utils.info(routineView, "Routine created successfully");
            } catch (Exception ex) {
                Logger.getLogger(CreateRoutineController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(userView, ex.getMessage());
            }
        }
    }
}

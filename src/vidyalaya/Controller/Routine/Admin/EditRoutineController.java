/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Routine.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import vidyalaya.Components.Modals.EditRoutineForm;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;
import vidyalaya.DAO.RoutineDAO.RoutineDAO;
import vidyalaya.DAO.RoutineDAO.RoutineDAOImplementation;

import vidyalaya.Model.ModuleData;
import vidyalaya.Model.RoutineData;

import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.Utils.Utils;

/**
 *
 * @author trishan9
 */
public class EditRoutineController {

    private final ModuleDAO moduleDAO = new ModuleDAOImplementation();
    private final RoutineDAO routineDAO = new RoutineDAOImplementation();
    private final EditRoutineForm userView;
    private final int routineId;
    public List<ModuleData> modulesList = new ArrayList<>();
    public RoutineData currentRoutine;

    public EditRoutineController(int routineId, EditRoutineForm userView) {
        this.userView = userView;
        this.routineId = routineId;

        try {
            userView.addEditRoutineListener(new EditRoutineListener());
            getModulesList();
            getCurrentRoutine();

            populateComboBox(userView.getModule(), modulesList);

            userView.getWeekday().setSelectedItem(currentRoutine.getWeekday());
            userView.getModule().setSelectedItem(new ModuleDAOImplementation().getModuleByCode(currentRoutine.getModuleCode()));

            String timeString = currentRoutine.getTime();
            userView.getTimeField().setValue(timeString);
            userView.getTimePicker().setSelectedTime(Utils.parseTimeString(timeString));

            String details = Utils.getSecondPartAfterFirstSplitter(currentRoutine.getRoutineContent(), "-").strip();
            userView.getContentField().setText(details);
        } catch (Exception ex){
            Logger.getLogger(EditRoutineController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void getCurrentRoutine() {
        try {
            RoutineData data = routineDAO.getRoutineById(routineId);
            currentRoutine = data;
        } catch (Exception ex) {
            Logger.getLogger(EditRoutineController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(userView, ex.getMessage());
            currentRoutine = null;
        }
    }

    class EditRoutineListener implements ActionListener {

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
                routineDAO.updateRoutine(routineId, routine);

                vidyalaya.View.Dashboard.Admin.RoutineScreen routineView = new vidyalaya.View.Dashboard.Admin.RoutineScreen();
                vidyalaya.Controller.Routine.Admin.RoutineController routineController = new vidyalaya.Controller.Routine.Admin.RoutineController(routineView);
                Utils.closeAllFrames();
                routineController.open();
                Utils.info(routineView, "Routine updated successfully");
            } catch (Exception ex) {
                Logger.getLogger(EditRoutineController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(userView, ex.getMessage());
            }
        }
    }
}

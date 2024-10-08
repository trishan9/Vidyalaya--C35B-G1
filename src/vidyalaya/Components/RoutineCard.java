/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vidyalaya.Components;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.CallbackFn;
import vidyalaya.Utils.Utils;

import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;
import vidyalaya.Model.RoutineData;

/**
 *
 * @author trish
 */
public class RoutineCard extends javax.swing.JPanel {

    CallbackFn onEditClick, onDeleteClick;

    /**
     * Creates new form RoutineCard
     *
     * @param routine
     * @param onEditClick
     * @param onDeleteClick
     */
    public RoutineCard(RoutineData routine, CallbackFn onEditClick, CallbackFn onDeleteClick) {
        try {
            initComponents();
            courseTitle.setText(new ModuleDAOImplementation().getModuleByCode(routine.getModuleCode()).getName());
            details.setText(routine.getRoutineContent());
            weekday.setText(routine.getWeekday());
            this.onEditClick = onEditClick;
            this.onDeleteClick = onDeleteClick;

            Utils.setCustomFont(courseTitleHeader, 15f);
            Utils.setCustomFont(courseTitle, 17f);
            Utils.setCustomFont(detailsHeader, 15f);
            Utils.setCustomFont(weekday, 15f);
            Utils.setCustomFont(details, 17f);
        } catch (Exception ex) {
            Logger.getLogger(RoutineCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoutine = new javax.swing.JPanel();
        courseTitleHeader = new javax.swing.JLabel();
        courseTitle = new javax.swing.JLabel();
        detailsHeader = new javax.swing.JLabel();
        details = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        weekday = new javax.swing.JLabel();

        pnlRoutine.setBackground(new java.awt.Color(255, 255, 255));
        pnlRoutine.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(222, 222, 222)));

        courseTitleHeader.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        courseTitleHeader.setForeground(new java.awt.Color(130, 130, 130));
        courseTitleHeader.setText("Course Title");

        courseTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        courseTitle.setText("Object Oriented Programming");

        detailsHeader.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailsHeader.setForeground(new java.awt.Color(130, 130, 130));
        detailsHeader.setText("Details");

        details.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        details.setText("12:00 PM - @LR-10 (3 hours)");

        editButton.setBackground(new java.awt.Color(193, 241, 212));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Edit.png"))); // NOI18N
        editButton.setBorder(null);
        editButton.setBorderPainted(false);
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setFocusPainted(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEditButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(234, 186, 184));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Delete.png"))); // NOI18N
        deleteButton.setBorder(null);
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeleteButtonActionPerformed(evt);
            }
        });

        weekday.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        weekday.setText("Sunday");

        javax.swing.GroupLayout pnlRoutineLayout = new javax.swing.GroupLayout(pnlRoutine);
        pnlRoutine.setLayout(pnlRoutineLayout);
        pnlRoutineLayout.setHorizontalGroup(
            pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoutineLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRoutineLayout.createSequentialGroup()
                        .addComponent(weekday)
                        .addGap(460, 460, 460))
                    .addGroup(pnlRoutineLayout.createSequentialGroup()
                        .addGroup(pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRoutineLayout.createSequentialGroup()
                                .addComponent(courseTitleHeader)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(courseTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRoutineLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(detailsHeader))
                            .addComponent(details, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)))
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlRoutineLayout.setVerticalGroup(
            pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoutineLayout.createSequentialGroup()
                .addGroup(pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRoutineLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(detailsHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(details))
                    .addGroup(pnlRoutineLayout.createSequentialGroup()
                        .addComponent(weekday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoutineLayout.createSequentialGroup()
                                .addComponent(courseTitleHeader)
                                .addGap(10, 10, 10)
                                .addComponent(courseTitle))
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRoutine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRoutine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void onEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEditButtonActionPerformed
        // TODO add your handling code here:
        onEditClick.onTrigger();
    }//GEN-LAST:event_onEditButtonActionPerformed

    private void onDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDeleteButtonActionPerformed
        // TODO add your handling code here:
        onDeleteClick.onTrigger();
    }//GEN-LAST:event_onDeleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel courseTitle;
    private javax.swing.JLabel courseTitleHeader;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel details;
    private javax.swing.JLabel detailsHeader;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel pnlRoutine;
    private javax.swing.JLabel weekday;
    // End of variables declaration//GEN-END:variables
}

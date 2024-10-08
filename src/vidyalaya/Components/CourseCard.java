/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vidyalaya.Components;

import vidyalaya.Model.ModuleData;

import vidyalaya.Utils.CallbackFn;
import vidyalaya.Utils.Utils;

/**
 *
 * @author trish
 */
public class CourseCard extends javax.swing.JPanel {

    CallbackFn onEditClick, onDeleteClick;

    /**
     * Creates new form CourseCard
     *
     * @param module
     * @param onEditClick
     * @param onDeleteClick
     */
    public CourseCard(ModuleData module, CallbackFn onEditClick, CallbackFn onDeleteClick) {
        initComponents();
        courseTitle.setText(module.getName());
        courseCode.setText(String.valueOf(module.getCode()));
        this.onEditClick = onEditClick;
        this.onDeleteClick = onDeleteClick;

        Utils.setCustomFont(courseTitleHeader, 15f);
        Utils.setCustomFont(courseTitle, 17f);
        Utils.setCustomFont(courseCodeHeader, 15f);
        Utils.setCustomFont(courseCode, 17f);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCourse = new javax.swing.JPanel();
        courseTitleHeader = new javax.swing.JLabel();
        courseTitle = new javax.swing.JLabel();
        courseCodeHeader = new javax.swing.JLabel();
        courseCode = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        pnlCourse.setBackground(new java.awt.Color(255, 255, 255));
        pnlCourse.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(222, 222, 222)));

        courseTitleHeader.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        courseTitleHeader.setForeground(new java.awt.Color(130, 130, 130));
        courseTitleHeader.setText("Course Title");

        courseTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        courseTitle.setText("Object Oriented Programming");

        courseCodeHeader.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        courseCodeHeader.setForeground(new java.awt.Color(130, 130, 130));
        courseCodeHeader.setText("Course Code");

        courseCode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        courseCode.setText("200");

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

        javax.swing.GroupLayout pnlCourseLayout = new javax.swing.GroupLayout(pnlCourse);
        pnlCourse.setLayout(pnlCourseLayout);
        pnlCourseLayout.setHorizontalGroup(
            pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCourseLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseTitle)
                    .addComponent(courseTitleHeader))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addGroup(pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCourseLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(courseCode))
                    .addComponent(courseCodeHeader))
                .addGap(385, 385, 385)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlCourseLayout.setVerticalGroup(
            pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCourseLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlCourseLayout.createSequentialGroup()
                            .addComponent(courseCodeHeader)
                            .addGap(10, 10, 10)
                            .addComponent(courseCode))
                        .addGroup(pnlCourseLayout.createSequentialGroup()
                            .addComponent(courseTitleHeader)
                            .addGap(10, 10, 10)
                            .addComponent(courseTitle)))
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel courseCode;
    private javax.swing.JLabel courseCodeHeader;
    private javax.swing.JLabel courseTitle;
    private javax.swing.JLabel courseTitleHeader;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel pnlCourse;
    // End of variables declaration//GEN-END:variables
}

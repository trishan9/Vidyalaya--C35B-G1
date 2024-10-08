/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vidyalaya.Components.Modals;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.MaterialData;

import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

/**
 *
 * @author trish
 */
public class EditMaterialForm extends javax.swing.JFrame {

    MaterialData currentMaterial;

    /**
     * Creates new form EditMaterialForm
     *
     * @param material
     */
    public EditMaterialForm(MaterialData material) {
        initComponents();
        this.currentMaterial = material;
        txtTitle.setText(currentMaterial.getMaterialTitle());
        txtContent.setText(currentMaterial.getMaterialText());

        setTitle("Edit Material - Vidyalaya");
        setLocationRelativeTo(null);
        setResizable(false);

        // Load the icon image
        Utils.setFrameIcon(this, "/vidyalaya/Assets/logo.png");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Utils.setCustomFont(jLabel1, 25f);

    }

    /**
     * @return the txtTitle
     */
    public javax.swing.JTextField getMaterialTitle() {
        return txtTitle;
    }

    /**
     * @return the txtContent
     */
    public javax.swing.JTextArea getContentField() {
        return txtContent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new raven.datetime.component.date.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        updateMaterialBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTitle = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 2, true));

        label3.setText("Content");

        updateMaterialBtn.setBackground(new java.awt.Color(77, 215, 131));
        updateMaterialBtn.setText("Update Material");
        updateMaterialBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        updateMaterialBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateMaterialBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMaterialBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Edit Material");

        label4.setText("Title");

        jSeparator1.setBackground(new java.awt.Color(222, 222, 222));
        jSeparator1.setForeground(new java.awt.Color(222, 222, 222));

        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        txtContent.setColumns(20);
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(updateMaterialBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(label3)
                    .addComponent(label4)
                    .addComponent(txtTitle)
                    .addComponent(jScrollPane1))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateMaterialBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateMaterialBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMaterialBtnActionPerformed
        // TODO add your handling code here:
        try {
            String title = this.getMaterialTitle().getText();
            String text = this.getContentField().getText();

            if (title.isEmpty() || text.isEmpty()) {
                Utils.warning("All the fields are required!");
                return;
            }

            ModuleDAO moduleDAO = new ModuleDAOImplementation();
            MaterialData material = new MaterialData(title, text);
            moduleDAO.updateMaterial(currentMaterial.getId(), material);

            vidyalaya.View.Dashboard.Teacher.MyCoursesScreen coursesView = new vidyalaya.View.Dashboard.Teacher.MyCoursesScreen();
            vidyalaya.Controller.Courses.Teacher.MyCoursesController coursesController = new vidyalaya.Controller.Courses.Teacher.MyCoursesController(coursesView);
            Utils.closeAllFrames();
            coursesController.open();
            Utils.success("Material updated successfully!");
        } catch (Exception ex) {
            Utils.error(ex.getMessage());
        }

    }//GEN-LAST:event_updateMaterialBtnActionPerformed

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JButton updateMaterialBtn;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vidyalaya.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.MaterialData;

/**
 *
 * @author trish
 */
public class MaterialPopup extends javax.swing.JPanel {

    /**
     * Creates new form MaterialPopup
     *
     * @param material
     */
    public MaterialPopup(MaterialData material) {
        initComponents();

        materialTitle.setText(material.getMaterialTitle());
        materialText.setText(String.format("<HTML>%s</HTML>", material.getMaterialText()));

        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(77, 215, 131));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new CustomScrollbar());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);

        Utils.setCustomFont(materialTitle, 23f);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTitle = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        materialText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        materialTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        materialTitle.setText("Material");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setOpaque(false);

        materialText.setText("Content");
        materialText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(materialText, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialText, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
        );

        scroll.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTitle)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(materialTitle)
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel materialText;
    private javax.swing.JLabel materialTitle;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}

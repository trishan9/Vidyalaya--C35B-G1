/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vidyalaya;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.UIManager;

import vidyalaya.View.AdminLogin;

import vidyalaya.Controller.AdminLoginController;
import vidyalaya.Utils.DMSans;

/**
 *
 * @author trishan9
 */
public class Vidyalaya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Set FlatLaf as the Look and Feel
        try {
            FlatLaf.registerCustomDefaultsSource("vidyalaya");
            FlatMacLightLaf.setup();
            UIManager.put("defaultFont", new DMSans(16f).getFont());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminLogin userView = new AdminLogin();
                AdminLoginController userController = new AdminLoginController(userView);
                userController.open();
            }
        });
    }

}

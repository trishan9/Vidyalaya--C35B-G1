/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vidyalaya;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import vidyalaya.View.AdminLogin;

import vidyalaya.Controller.AdminLoginController;

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

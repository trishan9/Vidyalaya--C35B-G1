/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Utils;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;

import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author trishan9
 */
public class UIUtils {

    public static void setFrameIcon(JFrame frame, String path) {
        try {
            Image icon = ImageIO.read(frame.getClass().getResource(path));
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllFrames() {
        Frame[] frames = Frame.getFrames();

        for (Frame frame : frames) {
            frame.dispose();
        }
    }

    public static void setCustomFont(JLabel label, float size) {
        label.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JButton button, float size) {
        button.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JComboBox comobobox, float size) {
        comobobox.setFont(new DMSans(size).getFont());
    }

    public static void setCursorPointer(JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void setCursorPointer(JButton btn) {
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void alert(JFrame userView, String message) {
        JOptionPane.showMessageDialog(userView, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static void error(JFrame userView, String message) {
        JOptionPane.showMessageDialog(userView, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void info(JFrame userView, String message) {
        JOptionPane.showMessageDialog(userView, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int confirm(JFrame userView, String message) {
        return JOptionPane.showConfirmDialog(userView, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Utils;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import raven.datetime.component.time.TimePicker;

/**
 *
 * @author trishan9
 */
public class Utils {

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

    public static void removeAllMouseListeners(JComponent component) {
        MouseListener[] mouseListeners = component.getMouseListeners();
        for (MouseListener ml : mouseListeners) {
            component.removeMouseListener(ml);
        }
    }

    public static String getSecondPartAfterFirstSplitter(String str, String splitter) {
        int index = str.indexOf(splitter);
        if (index == -1) {
            return str;
        }
        return str.substring(index + 1);
    }
    
        public static LocalTime parseTimeString(String timeString) {
        String[] timeComponents = timeString.split("[: ]");
        int hour = Integer.parseInt(timeComponents[0]);
        int minute = Integer.parseInt(timeComponents[1]);
        String period = timeComponents[2];

        if (period.equalsIgnoreCase("PM") && hour != 12) {
            hour += 12;
        } else if (period.equalsIgnoreCase("AM") && hour == 12) {
            hour = 0;
        }

        return LocalTime.of(hour, minute);
    }

    public static void setCustomFont(JLabel label, float size) {
        label.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JTextField txt, float size) {
        txt.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JPasswordField password, float size) {
        password.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JButton button, float size) {
        button.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JComboBox comobobox, float size) {
        comobobox.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(TimePicker timePicker, float size) {
        timePicker.setFont(new DMSans(size).getFont());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Utils;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JPanel;

import java.sql.Timestamp;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import raven.datetime.component.time.TimePicker;
import raven.toast.Notifications;

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
            System.out.println(e);
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

    public static LocalDate parseDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            System.err.println("Invalid date format: " + dateString);
            throw ex;
        }
    }

    public static String parseTimestampAndCalculateDifference(Timestamp timestamp) {
        LocalDateTime dateTime = timestamp.toLocalDateTime();

        LocalDateTime now = LocalDateTime.now();

        long minutes = ChronoUnit.MINUTES.between(dateTime, now);
        long hours = ChronoUnit.HOURS.between(dateTime, now);
        long days = ChronoUnit.DAYS.between(dateTime, now);
        long months = ChronoUnit.MONTHS.between(dateTime, now);

        if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours < 24) {
            return hours + " hours ago";
        } else if (days < 30) {
            return days + " days ago";
        } else {
            return months + " months ago";
        }
    }

    public static String truncateString(String str) {
        if (str.length() > 28) {
            return str.substring(0, 28) + "...";
        } else {
            return str;
        }
    }

    public static void handleButtonVisibility(String str, JButton button) {
        if (str.length() <= 28) {
            button.setVisible(false);
        } else {
            button.setVisible(true);
        }
    }

    public static byte[] bufferedImageToByteArray(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void hidePanelAfterDelay(final JPanel panel) {
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    public static void setCustomFont(JLabel label, float size) {
        label.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JTextField txt, float size) {
        txt.setFont(new DMSans(size).getFont());
    }

    public static void setCustomFont(JTextArea txt, float size) {
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

    public static void error(String message) {
        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, message);
    }

    public static void success(String message) {
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, message);
    }

    public static void info(String message) {
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, message);
    }

    public static void warning(String message) {
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, message);
    }

    public static int confirm(JFrame userView, String message) {
        return JOptionPane.showConfirmDialog(userView, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}

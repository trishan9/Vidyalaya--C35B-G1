/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vidyalaya.View.Dashboard.Student;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vidyalaya.Utils.Utils;

import vidyalaya.DAO.AttendanceDAO.AttendanceDAOImplementation;
import vidyalaya.DAO.ModuleDAO.ModuleDAO;
import vidyalaya.DAO.ModuleDAO.ModuleDAOImplementation;

import vidyalaya.Model.ModuleData;

import vidyalaya.Model.StudentData;
import vidyalaya.SessionManagement.StudentSession;

import vidyalaya.Components.CourseAttendanceCard;

/**
 *
 * @author trish
 */
public class AttendanceScreen extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceScreen
     */
    public AttendanceScreen() {
        try {
            initComponents();
            initializeGrid();

            setTitle("Attendance - Vidyalaya");
            setSize(1400, 954);
            setLocationRelativeTo(null);
            setResizable(false);

            // Load the icon image
            Utils.setFrameIcon(this, "/vidyalaya/Assets/logo.png");

            // Custom Font Setting
            Utils.setCustomFont(jLabelHead, 25f);
            Utils.setCustomFont(lblName, 14f);
            Utils.setCustomFont(lblId, 14f);
            Utils.setCustomFont(lblRole, 14f);
            Utils.setCustomFont(menuCourses, 17f);
            Utils.setCustomFont(menuRoutine, 17f);
            Utils.setCustomFont(menuNotices, 17f);
            Utils.setCustomFont(menuAttendance, 17f);
            Utils.setCustomFont(menuSettings, 17f);
            Utils.setCustomFont(menuLogout, 17f);
            Utils.setCustomFont(jLabel3, 23f);
            Utils.setCustomFont(jLabel4, 18f);
            Utils.setCustomFont(presentDays, 23f);
            Utils.setCustomFont(jLabel6, 18f);
            Utils.setCustomFont(absentDays, 23f);
            Utils.setCustomFont(jLabel8, 18f);
            Utils.setCustomFont(attendancePercentage, 23f);

            StudentData currentUser = StudentSession.getCurrentUser();
            lblName.setText(currentUser.getName());
            lblId.setText(currentUser.getStudentId());

            AttendanceDAOImplementation attendanceDAO = new AttendanceDAOImplementation();
            int totalTaughtDays = attendanceDAO.getTotalTaughtDaysAcrossAllCourses(currentUser.getAdminId());
            int totalAbsentDays = attendanceDAO.getStudentAbsentDaysAcrossAllCourses();
            int totalPresentDays = totalTaughtDays - totalAbsentDays;
            double attendancePer = ((double) totalPresentDays / totalTaughtDays) * 100;

            presentDays.setText("" + totalPresentDays);
            absentDays.setText("" + totalAbsentDays);
            attendancePercentage.setText(attendancePer + "%");
        } catch (Exception ex) {
            Utils.error(ex.getMessage());
        }
    }

    private void initializeGrid() {
        try {
            ModuleDAO moduleDAO = new ModuleDAOImplementation();
            List<ModuleData> modulesList = moduleDAO.getAllModules(StudentSession.getCurrentUser().getAdminId());
            modulesList.forEach((x) -> addGrid(x));
            var grid = new GridLayout(0, 3);
            grid.setHgap(10);
            grid.setVgap(10);
            pnlAttendance.setLayout(grid);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addGrid(ModuleData data) {
        var temp = new CourseAttendanceCard(StudentSession.getCurrentUser().getId(), data.getCode());
        pnlAttendance.add(temp);
    }

    public void addCoursesRedirectListener(ActionListener listener) {
        Utils.removeAllMouseListeners(menuCourses);

        menuCourses.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public void addRoutineRedirectListener(ActionListener listener) {
        Utils.removeAllMouseListeners(menuRoutine);

        menuRoutine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public void addNoticesRedirectListener(ActionListener listener) {
        Utils.removeAllMouseListeners(menuNotices);

        menuNotices.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public void addSettingsRedirectListener(ActionListener listener) {
        Utils.removeAllMouseListeners(menuSettings);

        menuSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    public void addLogoutListener(ActionListener listener) {
        Utils.removeAllMouseListeners(menuLogout);

        menuLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNav = new javax.swing.JPanel();
        jLabelHead = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        pnlSideNav = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        navCourses = new javax.swing.JPanel();
        menuCourses = new javax.swing.JLabel();
        iconCourses = new javax.swing.JLabel();
        navRoutine = new javax.swing.JPanel();
        menuRoutine = new javax.swing.JLabel();
        iconRoutine = new javax.swing.JLabel();
        navNotices = new javax.swing.JPanel();
        menuNotices = new javax.swing.JLabel();
        iconNotices = new javax.swing.JLabel();
        navAttendance = new javax.swing.JPanel();
        menuAttendance = new javax.swing.JLabel();
        iconAttendance = new javax.swing.JLabel();
        navSettings = new javax.swing.JPanel();
        menuSettings = new javax.swing.JLabel();
        iconSettings = new javax.swing.JLabel();
        logOut = new javax.swing.JPanel();
        menuLogout = new javax.swing.JLabel();
        iconLogout = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlPresent = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        presentDays = new javax.swing.JLabel();
        pnlPresent1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        absentDays = new javax.swing.JLabel();
        pnlPresent2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        attendancePercentage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlAttendance = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlNav.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 222, 222), 2));
        pnlNav.setPreferredSize(new java.awt.Dimension(835, 80));

        jLabelHead.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabelHead.setForeground(new java.awt.Color(0, 162, 100));
        jLabelHead.setText("Vidyalaya");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/vidyalaya-logo.png"))); // NOI18N

        javax.swing.GroupLayout pnlNavLayout = new javax.swing.GroupLayout(pnlNav);
        pnlNav.setLayout(pnlNavLayout);
        pnlNavLayout.setHorizontalGroup(
            pnlNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelHead)
                .addContainerGap(1216, Short.MAX_VALUE))
        );
        pnlNavLayout.setVerticalGroup(
            pnlNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelHead)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setPreferredSize(new java.awt.Dimension(0, 800));

        pnlSideNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlSideNav.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(222, 222, 222)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/User.png"))); // NOI18N

        lblName.setText("Trishan Wagle");

        lblId.setText("ST-230352");

        lblRole.setForeground(new java.awt.Color(130, 130, 130));
        lblRole.setText("Student");

        navCourses.setBackground(new java.awt.Color(255, 255, 255));
        navCourses.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        navCourses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navCourses.setPreferredSize(new java.awt.Dimension(100, 52));

        menuCourses.setText("My Courses");

        iconCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Courses.png"))); // NOI18N

        javax.swing.GroupLayout navCoursesLayout = new javax.swing.GroupLayout(navCourses);
        navCourses.setLayout(navCoursesLayout);
        navCoursesLayout.setHorizontalGroup(
            navCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navCoursesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconCourses)
                .addGap(18, 18, 18)
                .addComponent(menuCourses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navCoursesLayout.setVerticalGroup(
            navCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCourses, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navRoutine.setBackground(new java.awt.Color(255, 255, 255));
        navRoutine.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        navRoutine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navRoutine.setPreferredSize(new java.awt.Dimension(100, 52));

        menuRoutine.setText("Routine");

        iconRoutine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Routine.png"))); // NOI18N

        javax.swing.GroupLayout navRoutineLayout = new javax.swing.GroupLayout(navRoutine);
        navRoutine.setLayout(navRoutineLayout);
        navRoutineLayout.setHorizontalGroup(
            navRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navRoutineLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconRoutine)
                .addGap(18, 18, 18)
                .addComponent(menuRoutine)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navRoutineLayout.setVerticalGroup(
            navRoutineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconRoutine, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuRoutine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navNotices.setBackground(new java.awt.Color(255, 255, 255));
        navNotices.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        navNotices.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navNotices.setPreferredSize(new java.awt.Dimension(100, 52));

        menuNotices.setText("Notices");

        iconNotices.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Notices.png"))); // NOI18N

        javax.swing.GroupLayout navNoticesLayout = new javax.swing.GroupLayout(navNotices);
        navNotices.setLayout(navNoticesLayout);
        navNoticesLayout.setHorizontalGroup(
            navNoticesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navNoticesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconNotices)
                .addGap(18, 18, 18)
                .addComponent(menuNotices)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navNoticesLayout.setVerticalGroup(
            navNoticesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconNotices, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuNotices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navAttendance.setBackground(new java.awt.Color(77, 215, 131));
        navAttendance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        navAttendance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navAttendance.setPreferredSize(new java.awt.Dimension(100, 52));

        menuAttendance.setText("Attendance");

        iconAttendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Attendance.png"))); // NOI18N

        javax.swing.GroupLayout navAttendanceLayout = new javax.swing.GroupLayout(navAttendance);
        navAttendance.setLayout(navAttendanceLayout);
        navAttendanceLayout.setHorizontalGroup(
            navAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navAttendanceLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconAttendance)
                .addGap(18, 18, 18)
                .addComponent(menuAttendance)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navAttendanceLayout.setVerticalGroup(
            navAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navSettings.setBackground(new java.awt.Color(255, 255, 255));
        navSettings.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        navSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navSettings.setPreferredSize(new java.awt.Dimension(100, 52));

        menuSettings.setText("Settings");

        iconSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Settings.png"))); // NOI18N

        javax.swing.GroupLayout navSettingsLayout = new javax.swing.GroupLayout(navSettings);
        navSettings.setLayout(navSettingsLayout);
        navSettingsLayout.setHorizontalGroup(
            navSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navSettingsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconSettings)
                .addGap(18, 18, 18)
                .addComponent(menuSettings)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navSettingsLayout.setVerticalGroup(
            navSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        logOut.setBackground(new java.awt.Color(193, 56, 51));
        logOut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        logOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOut.setPreferredSize(new java.awt.Dimension(100, 52));

        menuLogout.setForeground(new java.awt.Color(255, 255, 255));
        menuLogout.setText("Logout");

        iconLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vidyalaya/Assets/icons/Logout.png"))); // NOI18N

        javax.swing.GroupLayout logOutLayout = new javax.swing.GroupLayout(logOut);
        logOut.setLayout(logOutLayout);
        logOutLayout.setHorizontalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logOutLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(iconLogout)
                .addGap(18, 18, 18)
                .addComponent(menuLogout)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        logOutLayout.setVerticalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlSideNavLayout = new javax.swing.GroupLayout(pnlSideNav);
        pnlSideNav.setLayout(pnlSideNavLayout);
        pnlSideNavLayout.setHorizontalGroup(
            pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideNavLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(navCourses, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addGroup(pnlSideNavLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addGroup(pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblName)
                            .addComponent(lblId)
                            .addComponent(lblRole)))
                    .addComponent(navRoutine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(navNotices, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(navAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(navSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlSideNavLayout.setVerticalGroup(
            pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideNavLayout.createSequentialGroup()
                .addGroup(pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSideNavLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addGroup(pnlSideNavLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRole)))
                .addGap(55, 55, 55)
                .addComponent(navCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(navRoutine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(navNotices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(navAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(navSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pnlRight.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Attendance");

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(970, 970, 970))
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPresent.setBackground(new java.awt.Color(193, 241, 212));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Total Present Days");

        presentDays.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        presentDays.setText("30");

        javax.swing.GroupLayout pnlPresentLayout = new javax.swing.GroupLayout(pnlPresent);
        pnlPresent.setLayout(pnlPresentLayout);
        pnlPresentLayout.setHorizontalGroup(
            pnlPresentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresentLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(pnlPresentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(pnlPresentLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(presentDays)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        pnlPresentLayout.setVerticalGroup(
            pnlPresentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPresentLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(presentDays)
                .addGap(28, 28, 28))
        );

        pnlPresent1.setBackground(new java.awt.Color(193, 241, 212));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Total Absent Days");

        absentDays.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        absentDays.setText("30");

        javax.swing.GroupLayout pnlPresent1Layout = new javax.swing.GroupLayout(pnlPresent1);
        pnlPresent1.setLayout(pnlPresent1Layout);
        pnlPresent1Layout.setHorizontalGroup(
            pnlPresent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresent1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(pnlPresent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(pnlPresent1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(absentDays)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        pnlPresent1Layout.setVerticalGroup(
            pnlPresent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPresent1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(absentDays)
                .addGap(28, 28, 28))
        );

        pnlPresent2.setBackground(new java.awt.Color(193, 241, 212));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Total Attendance Percentage");

        attendancePercentage.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        attendancePercentage.setText("30%");

        javax.swing.GroupLayout pnlPresent2Layout = new javax.swing.GroupLayout(pnlPresent2);
        pnlPresent2.setLayout(pnlPresent2Layout);
        pnlPresent2Layout.setHorizontalGroup(
            pnlPresent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresent2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlPresent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(pnlPresent2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(attendancePercentage)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnlPresent2Layout.setVerticalGroup(
            pnlPresent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPresent2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(attendancePercentage)
                .addGap(29, 29, 29))
        );

        pnlAttendance.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAttendanceLayout = new javax.swing.GroupLayout(pnlAttendance);
        pnlAttendance.setLayout(pnlAttendanceLayout);
        pnlAttendanceLayout.setHorizontalGroup(
            pnlAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlAttendanceLayout.setVerticalGroup(
            pnlAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlSideNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlAttendance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addComponent(pnlPresent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(pnlPresent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(pnlPresent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSideNav, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPresent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPresent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPresent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AttendanceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel absentDays;
    private javax.swing.JLabel attendancePercentage;
    private javax.swing.JLabel iconAttendance;
    private javax.swing.JLabel iconCourses;
    private javax.swing.JLabel iconLogout;
    private javax.swing.JLabel iconNotices;
    private javax.swing.JLabel iconRoutine;
    private javax.swing.JLabel iconSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelHead;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JPanel logOut;
    private javax.swing.JLabel menuAttendance;
    private javax.swing.JLabel menuCourses;
    private javax.swing.JLabel menuLogout;
    private javax.swing.JLabel menuNotices;
    private javax.swing.JLabel menuRoutine;
    private javax.swing.JLabel menuSettings;
    private javax.swing.JPanel navAttendance;
    private javax.swing.JPanel navCourses;
    private javax.swing.JPanel navNotices;
    private javax.swing.JPanel navRoutine;
    private javax.swing.JPanel navSettings;
    private javax.swing.JPanel pnlAttendance;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlPresent;
    private javax.swing.JPanel pnlPresent1;
    private javax.swing.JPanel pnlPresent2;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlSideNav;
    private javax.swing.JLabel presentDays;
    // End of variables declaration//GEN-END:variables
}

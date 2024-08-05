/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Notices.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.NoticeData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.NoticeDAO.NoticeDAO;
import vidyalaya.DAO.NoticeDAO.NoticeDAOImplementation;

import vidyalaya.Components.Modals.CreateNoticeForm;

/**
 *
 * @author trishan9
 */
public class CreateNoticeController {

    private final NoticeDAO noticeDAO = new NoticeDAOImplementation();
    private final CreateNoticeForm userView;

    public CreateNoticeController(CreateNoticeForm userView) {
        this.userView = userView;
        userView.addCreateNoticeListener(new CreateNoticeListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CreateNoticeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String noticeType = userView.getNoticeType().getSelectedItem().toString();
                String noticeTitle = userView.getNoticeTitle().getText();
                String effectiveDate = userView.getDateField().getText();
                String noticeContent = userView.getContentField().getText();
                int adminId = AdminSession.getCurrentUser().getId();

                NoticeData notice = new NoticeData(adminId, noticeTitle, noticeContent, noticeType, effectiveDate);
                noticeDAO.createNotice(notice);

                vidyalaya.View.Dashboard.Admin.NoticesScreen noticesView = new vidyalaya.View.Dashboard.Admin.NoticesScreen();
                vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
                Utils.closeAllFrames();
                noticesController.open();
                Utils.success("Notice created successfully");
            } catch (Exception ex) {
                Logger.getLogger(CreateNoticeController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }
}

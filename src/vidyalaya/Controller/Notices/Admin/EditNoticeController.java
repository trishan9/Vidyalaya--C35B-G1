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

import vidyalaya.Components.Modals.EditNoticeForm;

/**
 *
 * @author trishan9
 */
public class EditNoticeController {

    private final NoticeDAO noticeDAO = new NoticeDAOImplementation();
    private final EditNoticeForm userView;
    private final int noticeId;
    public NoticeData currentNotice;

    public EditNoticeController(int noticeId, EditNoticeForm userView) {
        this.userView = userView;
        this.noticeId = noticeId;
        userView.addEditNoticeListener(new EditNoticeListener());
        getCurrentNotice();
        userView.getNoticeType().setSelectedItem(Utils.capitalize(currentNotice.getNoticeType()));
        userView.getNoticeTitle().setText(currentNotice.getTitle());

        String dateString = currentNotice.getEffectiveDate();
        userView.getDateField().setValue(dateString);
        userView.getDatePicker().setSelectedDate(Utils.parseDateString(dateString));

        userView.getContentField().setText(currentNotice.getContent());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void getCurrentNotice() {
        try {
            NoticeData data = noticeDAO.getNoticeById(noticeId);
            currentNotice = data;
        } catch (Exception ex) {
            Logger.getLogger(EditNoticeController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(ex.getMessage());
            currentNotice = null;
        }
    }

    class EditNoticeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String noticeType = userView.getNoticeType().getSelectedItem().toString();
                String noticeTitle = userView.getNoticeTitle().getText();
                String effectiveDate = userView.getDateField().getText();
                String noticeContent = userView.getContentField().getText();
                int adminId = AdminSession.getCurrentUser().getId();

                if (noticeType.isEmpty() || noticeTitle.isEmpty() || effectiveDate.isEmpty() || noticeContent.isEmpty()) {
                    Utils.warning("All the fields are required!");
                    return;
                }

                NoticeData notice = new NoticeData(adminId, noticeTitle, noticeContent, noticeType, effectiveDate);
                noticeDAO.updateNotice(noticeId, notice);

                vidyalaya.View.Dashboard.Admin.NoticesScreen noticesView = new vidyalaya.View.Dashboard.Admin.NoticesScreen();
                vidyalaya.Controller.Notices.Admin.NoticesController noticesController = new vidyalaya.Controller.Notices.Admin.NoticesController(noticesView);
                Utils.closeAllFrames();
                noticesController.open();
                Utils.success("Notice updated successfully");
            } catch (Exception ex) {
                Logger.getLogger(EditNoticeController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }
}

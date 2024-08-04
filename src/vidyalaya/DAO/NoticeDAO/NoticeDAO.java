/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vidyalaya.DAO.NoticeDAO;

import java.util.List;

import vidyalaya.Model.NoticeData;

/**
 *
 * @author trish
 */
public interface NoticeDAO {

    void createNotice(NoticeData notice) throws Exception;

    void updateNotice(int noticeId, NoticeData notice) throws Exception;

    void deleteNotice(int noticeId) throws Exception;

    List<NoticeData> getAllNotices(int adminId) throws Exception;
    
    List<NoticeData> getNoticesByType(int adminId, String noticeType) throws Exception;

    NoticeData getNoticeById(int noticeId) throws Exception;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.DAO.NoticeDAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import vidyalaya.Database.MySqlConnection;

import java.util.ArrayList;
import java.util.List;

import vidyalaya.Model.NoticeData;

/**
 *
 * @author trish
 */
public class NoticeDAOImplementation implements NoticeDAO {

    MySqlConnection mysql = new MySqlConnection();

    @Override
    public void createNotice(NoticeData notice) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "INSERT INTO notice (admin_id, title, content, notice_type, effective_date) VALUES (?, ?, ?, ?, ?)"
        );
        statement.setInt(1, notice.getAdminId());
        statement.setString(2, notice.getTitle());
        statement.setString(3, notice.getContent());
        statement.setString(4, notice.getNoticeType());
        statement.setString(5, notice.getEffectiveDate());

        try {
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void updateNotice(int noticeId, NoticeData notice) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement(
                "UPDATE notice SET title = ?, content = ?, notice_type = ?, effective_date = ? WHERE id = ?"
        );
        statement.setString(1, notice.getTitle());
        statement.setString(2, notice.getContent());
        statement.setString(3, notice.getNoticeType());
        statement.setString(4, notice.getEffectiveDate());
        statement.setInt(5, noticeId);

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public void deleteNotice(int noticeId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM notice WHERE id = ?");
        statement.setInt(1, noticeId);

        try {
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<NoticeData> getAllNotices(int adminId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM notice WHERE admin_id = ?");
        statement.setInt(1, adminId);

        try {
            List<NoticeData> noticeList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    noticeList.add(new NoticeData(data));
                }
            }
            statement.close();
            return noticeList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public List<NoticeData> getNoticesByType(int adminId, String noticeType) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM notice WHERE admin_id = ? AND notice_type = ?");
        statement.setInt(1, adminId);
        statement.setString(2, noticeType);

        try {
            List<NoticeData> noticeList = new ArrayList<>();
            try (ResultSet data = statement.executeQuery()) {
                while (data.next()) {
                    noticeList.add(new NoticeData(data));
                }
            }
            statement.close();
            return noticeList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            mysql.closeConnection(dbConnection);
        }
    }

    @Override
    public NoticeData getNoticeById(int noticeId) throws Exception {
        Connection dbConnection = mysql.openConnection();

        final PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM notice WHERE id = ?");
        statement.setInt(1, noticeId);

        try {
            ResultSet data = statement.executeQuery();
            if (data.next()) {
                return new NoticeData(data);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            statement.close();
            mysql.closeConnection(dbConnection);
        }
    }
}

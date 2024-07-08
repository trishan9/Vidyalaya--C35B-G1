/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author trishan9
 */
public class NoticeData {

    private int id;
    private int admin_id;
    private String title;
    private String content;
    private String notice_type;
    private Timestamp created_at;

    public NoticeData(int admin_id, String title, String content, String notice_type) {
        this.admin_id = admin_id;
        this.title = title;
        this.content = content;
        this.notice_type = notice_type;
    }

    public NoticeData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.admin_id = result.getInt("admin_id");
        this.title = result.getString("title");
        this.content = result.getString("content");
        this.notice_type = result.getString("notice_type");
        this.created_at = result.getTimestamp("created_at");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return admin_id;
    }

    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeType() {
        return notice_type;
    }

    public void setNoticeType(String notice_type) {
        this.notice_type = notice_type;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }
}

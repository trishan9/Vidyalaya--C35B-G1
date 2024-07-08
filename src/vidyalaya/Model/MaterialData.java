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
public class MaterialData {

    private int id;
    private int module_code;
    private int uploader_id;
    private String material_title;
    private String material_text;
    private Timestamp uploaded_at;

    public MaterialData(int module_code, int uploader_id, String material_title, String material_text) {
        this.module_code = module_code;
        this.uploader_id = uploader_id;
        this.material_title = material_title;
        this.material_text = material_text;
    }

    public MaterialData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.module_code = result.getInt("module_code");
        this.uploader_id = result.getInt("uploader_id");
        this.material_title = result.getString("material_title");
        this.material_text = result.getString("material_text");
        this.uploaded_at = result.getTimestamp("uploaded_at");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleCode() {
        return module_code;
    }

    public void setModuleCode(int module_code) {
        this.module_code = module_code;
    }

    public int getUploaderId() {
        return uploader_id;
    }

    public void setUploaderId(int uploader_id) {
        this.uploader_id = uploader_id;
    }

    public String getMaterialTitle() {
        return material_title;
    }

    public void setMaterialTitle(String material_title) {
        this.material_title = material_title;
    }

    public String getMaterialText() {
        return material_text;
    }

    public void setMaterialText(String material_text) {
        this.material_text = material_text;
    }

    public Timestamp getUploadedAt() {
        return uploaded_at;
    }

    public void setUploadedAt(Timestamp uploaded_at) {
        this.uploaded_at = uploaded_at;
    }
}

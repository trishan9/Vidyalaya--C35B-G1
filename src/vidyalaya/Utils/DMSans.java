/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Utils;

import java.awt.Font;
import java.io.File;

/**
 *
 * @author trishan9
 */
public class DMSans {

    private Font font;

    public DMSans(float fontSize) {
        try {
            File fontStyle = new File("src/vidyalaya/Assets/DMSans-Regular.ttf");
            this.font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(Font.BOLD, fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return this.font;
    }
}

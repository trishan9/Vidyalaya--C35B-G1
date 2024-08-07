/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Services;

import java.awt.image.BufferedImage;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 *
 * @author trish
 */
public class QRCodeService {

    public BufferedImage generateQRCode(String jsonData) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonData, BarcodeFormat.QR_CODE, 322, 286);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            Utils.success("QR Code Generated Succesfully!");
            return image;
        } catch (WriterException ex) {
            Logger.getLogger(QRCodeService.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(ex.getMessage());
            return null;
        }
    }
}

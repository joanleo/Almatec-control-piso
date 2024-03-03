package com.almatec.controlpiso.utils;

import java.awt.Font;
import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeGenerator {

	private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
	
	public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
	    Barcode barcode = BarcodeFactory.createCode128(barcodeText);
	    barcode.setFont(BARCODE_TEXT_FONT);

	    return BarcodeImageHandler.getImage(barcode);
	}
	
	public static BufferedImage generateCode128Barcode(String barcodeText) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
	
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}

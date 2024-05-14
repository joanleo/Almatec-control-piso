package com.almatec.controlpiso.utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PdfPageEventHandler extends PdfPageEventHelper  {
	
	public abstract void createHeader(PdfWriter writer, Document document) throws DocumentException;

    public abstract void createFooter(PdfWriter writer, Document document) throws DocumentException;

	 @Override
    public void onStartPage(PdfWriter writer, Document document) {
        createHeader(writer, document);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        createFooter(writer, document);
    }
}

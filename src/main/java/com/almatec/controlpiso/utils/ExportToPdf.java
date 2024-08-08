package com.almatec.controlpiso.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public abstract class ExportToPdf extends PdfPageEventHelper {
	
	protected Document document;
    protected PdfWriter writer;
    protected Rectangle pageSize;

    public ExportToPdf(Rectangle pageSize) {
        this.pageSize = pageSize == null ? PageSize.LETTER : pageSize;
    }
    
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        document = new Document(pageSize);

        try {
            writer = PdfWriter.getInstance(document, response.getOutputStream());

            writer.setPageEvent(this);

            document.setMargins(30, 20, 20, 20);
            document.open();

            addContent();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }

    protected abstract void addContent() throws DocumentException;
    
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
    }
    
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
    }

}

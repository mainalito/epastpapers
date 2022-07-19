package com.epastpapers.epastpapers.service;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.classfile.Module.Export;

import com.epastpapers.epastpapers.PastPapers.Exams;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;


public class ExportFile {

    private final List<Exams>weeklyReportList;

    public ExportFile(List<Exams>weeklyReportList){
        this.weeklyReportList= weeklyReportList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
       // cell.setBackgroundColor(Color);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("File Name", font));
        table.addCell(cell);

        // cell.setPhrase(new Phrase("File Type", font));
        // table.addCell(cell);

        cell.setPhrase(new Phrase("Faculty Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Exams exam : weeklyReportList) {
            table.addCell(String.valueOf(exam.getId()));
            table.addCell(exam.getFileName());
            //table.addCell(exam.getFileType());
            table.addCell(exam.getFaculty().getName());
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            table.addCell(dateFormatter.format(exam.getDate()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.GRAY);

        Paragraph p = new Paragraph("List of Exams Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 4.0f, 4.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}

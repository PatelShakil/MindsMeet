/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.techsavvy.mindsmeet.entity.NotesText;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author M.SHAKIL PATEL
 */
public class Utils {
    public static String APP_URL = "http://localhost:8080/MindsMeet/";
    public static String STORAGE_PATH = "D:\\ICT\\JAVA\\MindsMeet\\MindsMeet\\src\\main\\webapp\\resources\\uploads\\";
    public static String STORAGE_URL = APP_URL+"resources/uploads/";
    public static String PDF_PATH = STORAGE_PATH + "pdf/";
    public static String IMAGES_PATH = STORAGE_PATH + "images/";
    public static String IMAGES_URL = STORAGE_URL + "images/";
    public static final String PDF_URL = STORAGE_URL + "pdf/";
    
    
    public static String PERSISTENCE_CONTEXT = "my_mindsmeet_pu";
    
    public static String getFormattedDate(String pattern){
        Date date = new Date();
        return new SimpleDateFormat(pattern).format(date);
    }
    
    public static Response getCatch(Exception e){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
    
    public static Collection<NotesText> getTextFromPdf(String file) {
        Collection<NotesText> doc = new ArrayList<>();
        try {
            PDDocument document = PDDocument.load(new File(file));
//            URL url = new URL(file);
//            PDDocument document = PDDocument.load(url.openStream());
            PDFTextStripper stripper = new PDFTextStripper();

            for (int page = 1; page <= document.getNumberOfPages(); ++page) {
                stripper.setStartPage(page);
                stripper.setEndPage(page);

                String text = stripper.getText(document);
//                System.out.println("Page " + page + ":\n" + text);
                NotesText note = new NotesText();
                note.setContent(text);
                note.setPageNo(page);
                doc.add(note);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    
}

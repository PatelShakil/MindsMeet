/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author M.SHAKIL PATEL
 */
public class Utils {
    public static String STORAGE_PATH = "C:\\xampp\\htdocs\\mindsmeet\\uploads\\";
    public static String STORAGE_URL = "http://localhost/mindsmeet/uploads/";
    public static String PDF_PATH = STORAGE_PATH + "pdf/";
    public static String IMAGES_PATH = STORAGE_PATH + "images/";
    public static String IMAGES_URL = STORAGE_URL + "images/";
    public static String PDF_URL = STORAGE_URL + "pdf/";
    
    public static String getFormattedDate(String pattern){
        Date date = new Date();
        return new SimpleDateFormat(pattern).format(date);
    }
    
    
}

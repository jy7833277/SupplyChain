package cn.edu.nju.jungle.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Jungle
 *
 */
public class LanguageManager {
    private static LanguageManager languageManager;
    private static InputStream in;
    static{
        initInputStream();
    };
    private LanguageManager(){
        
    }
    public static LanguageManager getInstance(){
        if(null==languageManager){
            languageManager = new LanguageManager();
        }
        return languageManager;
    }
    public String getString(String sign){
        String value = "";
        if(null==in){
            initInputStream();
        }
        Properties p = new Properties();
        try {
            p.load(in);
            value = p.getProperty(sign);
            if(null==value){
                value = p.getProperty(Constant.NOT_FOUND);
                value = null==value?"":value;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return value;
    }
    private static void initInputStream(){
        try{
            in = new BufferedInputStream(new FileInputStream(Constant.LANGUAGE_FILE_NAME));
        }catch(FileNotFoundException fnfe){
            
        }
    }
    public static void releaseLanguageFile(){
        if(null!=in){
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
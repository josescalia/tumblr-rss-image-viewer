/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josescalia.tumblr.util.text;

import com.josescalia.tumblr.model.Rss;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josescalia
 */
public class TextIOUtility {

    private static final String sFile = "assets/link_fav.txt";

    public static List<Rss> getUrlList() {
        List<Rss> list = null;
        BufferedReader br = null;
        try {
            list = new ArrayList<Rss>();
            String sCurrentLine;

            br = new BufferedReader(new FileReader(sFile));

            while ((sCurrentLine = br.readLine()) != null) {
                Rss rss = new Rss();
                rss.setLink(sCurrentLine + "/rss");
                rss.setTitle(sCurrentLine.replaceAll("http://", "").replaceAll(".tumblr.com", "").toUpperCase());
                list.add(rss);
                //
                //System.out.println(sCurrentLine);
            }
        } catch (Exception e) {
            System.out.println("error ; " + e.getMessage());
        }
        return list;
    }

    public static boolean addItem(String url){
        boolean bResult = false;
        List<Rss> list = getUrlList();
        System.out.println("before Add : " + list.size());
        //list.add(new Rss(url.replaceAll("/rss", "")));
        System.out.println("after Add : " + list.size());
        //the rewrite 
        File file = new File(sFile);

        // if file doesnt exists, then create it
        if (file.exists()) {
            file.delete();
            try {
                String content = "";
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                int limit = list.size();
                int i = 0;
                for (Rss rss : list) {
                    i++;
                    content += rss.getLink().replace("/rss", "");
                    if(limit !=i){
                        content+= "\n";
                    }
                }
                bw.write(content);
                bw.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(TextIOUtility.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        return bResult;
    }
    public static void main(String[] args) {
        //write one
        System.out.println("addItem : " + addItem("http://jalurresmi.com"));
        
        
        //readAll
        List<Rss> list = getUrlList();
        if (!list.isEmpty()) {
            for (Rss rss : list) {
                System.out.println(rss);
            }
        }else{
            System.out.println("List is Empty");
        }
        
        
    }
}

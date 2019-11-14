package com.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtil {

    public static List<String> readFiles(File fileName) {
        BufferedReader br = null;
        List<String> urlList = new ArrayList<>();

        try {

            String apiName;


            br = new BufferedReader(new FileReader(fileName));

            while ((apiName = br.readLine()) != null) {
                System.out.println(apiName);
                urlList.add(apiName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return urlList;
    }

}

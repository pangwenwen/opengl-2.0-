package com.example.opengl2.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hua.pang on 2017/10/23.
 */

public class ResourceReader {

    public static String readFromSource(Context context,int sourceId){
        StringBuilder text = new StringBuilder();

        try {
            InputStream inputStream = context.getResources().openRawResource(sourceId);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineString;
            while ((lineString = bufferedReader.readLine())!= null){
                text.append(lineString);
                text.append("\n");
            }
        }catch (IOException e){
           throw new RuntimeException("could not find "+sourceId,e);
        }catch (Resources.NotFoundException e){
            throw new RuntimeException("file can not open "+sourceId,e);
        }
        return text.toString();
    }
}

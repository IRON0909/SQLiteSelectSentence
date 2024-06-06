package com.example.relativenet;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetPerImg {

    private int Index;

    public GetPerImg(int index) {
        Index = index;
    }

    public String getTxtStringFromRaw(Context context, int rawName) throws IOException {
        BufferedReader bufferedReader=null;
        try{
            InputStreamReader inputReader = new InputStreamReader(context.getResources().openRawResource(rawName));
            bufferedReader=new BufferedReader(inputReader);
            String line = "";
            String result=null;
            while ((line = bufferedReader.readLine()) != null) {
                //result += line;

                int PerIndex = Integer.parseInt(line.substring(0, 1));

                if(PerIndex==Index+1) {
                    if(line.length()>=2)
                        result = line.substring(2);
                        System.out.println("Img_link "+result);
                    return result;
                }
            }
            return result;
        }catch (Exception e){
            throw e;
        }
        finally {
            if (bufferedReader !=null){
                try {
                    bufferedReader.close();
                }catch (Exception e){e.printStackTrace();}
            }
        }

    }
}

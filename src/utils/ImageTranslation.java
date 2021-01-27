package utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

public class ImageTranslation {

    public static byte[] getByteArray(InputStream is) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(b);
        int c;
        try{
            while((c=is.read())!=-1){
                os.write(c);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally{
            if(os!=null){
                try{
                    os.flush();
                    os.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return b.toByteArray();
    }

 // ファイル名の取得メソッド
    public static String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }

        return name;
    }

 // INPUTデータの取得メソッド
    public static String getParamVal(Part part) {
        if (part.getContentType() == null) { // INPUTの文字列データはContentTypeがnull
            try {
                InputStream inputStream = part.getInputStream();
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
                return bufReader.lines().collect(Collectors.joining());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}

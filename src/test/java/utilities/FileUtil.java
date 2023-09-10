package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtil {

    public static String storeresponse = System.getProperty("user.dir")+"/src/test/resources/testData/output/";
   public static String storeExpected = System.getProperty("user.dir")+"/src/test/resources/testData/expected/";

    public static  boolean createFileWithContent(String strFileName, String strContent){
        boolean boolresult = false;
        try{

            File file = new File(storeresponse+strFileName);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(strContent);
            output.close();
            boolresult = true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return boolresult;
    }
}

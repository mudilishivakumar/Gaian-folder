package baseLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    private static final String filePath = "./src/main/resources/testData.properties";
    private String BASE_URI = null;
    private Properties pro = null;

    ReadConfig(){
        File src = new File(filePath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (pro.getProperty("ENV").equalsIgnoreCase("dev"))
            BASE_URI = pro.getProperty("BASE_URI_DEV");
        else if (pro.getProperty("ENV").equalsIgnoreCase("prod"))
            BASE_URI = pro.getProperty("BASE_URI_PROD");
    }
    /* Request API EndPoints */
    public String getBaseURI() { return BASE_URI; }
    public String getLoginEndpoint(){ return  pro.getProperty("IMA_URI"); }
    public String getBADeliveryEndpoint() { return pro.getProperty("BA_DELIVERY_URL"); }
    public String getPitcherURL(){ return  pro.getProperty("FILE_UPLOADED_PITCHER_URL"); }

    /* Request Data */
    public String getBAPackageName() { return pro.getProperty("PACKAGE_NAME"); }
    public String getBAID() { return pro.getProperty("BA_ID"); }



}

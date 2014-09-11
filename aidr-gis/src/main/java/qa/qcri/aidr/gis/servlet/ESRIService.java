package qa.qcri.aidr.gis.servlet;

import org.apache.log4j.Logger;
import qa.qcri.aidr.gis.store.LookUp;
import qa.qcri.aidr.gis.util.Communicator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 5/18/14
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ESRIService extends HttpServlet {
    protected static Logger logger = Logger.getLogger("ESRIService");

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @SuppressWarnings("unchecked")
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            System.out.println("esri: processRequest:  ");
            String name = "qdate";
            String value = request.getParameter(name);
            String url = LookUp.MAP_GEOJSON_URL;

            String returnValue;
            File f = lastFileModified();
            if(f != null){
                returnValue = getFileContent(f);
                System.out.println("esri: from file:  " + returnValue);
            }
            else{
                Communicator com = new Communicator();
                com.sendGet(url);
                //String returnValue =  com.requestGet(url,"application/json");
                returnValue =  com.sendGet(url);
                System.out.println("esri: from url:  " + returnValue);
            }

            final byte[] content = returnValue.getBytes("UTF-8");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setContentLength(content.length);

            final OutputStream out = response.getOutputStream();
            out.write(content);

           // System.out.println("esri: output:  " + returnValue);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("esri: output error:  " + e.getMessage());
            logger.error("processRequest : error found " + e.getMessage());
        }

    }

    public String getFileContent(File f){
        StringBuffer st = new StringBuffer();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(f));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                st.append(sCurrentLine) ;
            }
        }
        catch(Exception e){
            System.out.println("exception to read a file : " + e.getMessage());
        }
        finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return st.toString();
    }

    public File lastFileModified() {
        File choise = null;

        try{
            File fl = new File(LookUp.DEFAULT_ESRI_GEO_FILE_PATH);
            File[] files = fl.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isFile();
                }
            });
            long lastMod = Long.MIN_VALUE;


            for (File file : files) {
                if (file.lastModified() > lastMod) {
                    choise = file;
                    lastMod = file.lastModified();
                }
            }

            System.out.println("choise : " + choise);
        }
        catch(Exception e){
            System.out.println("choise Exception: " + e.getMessage());
            return null;
        }


        return choise;
    }

    private boolean isValidDateFormat(String lastupdated){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String dateInString = "2014-01-26 13:44:48";
            Date queryDate = sdf.parse(lastupdated);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}

package qa.qcri.aidr.gis.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 2/12/14
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataFormatValidator {
    public static boolean isValidateJson(String inputDataString)
    {
        boolean isVaildJsonObject = true;
        try{
            new JSONParser().parse(inputDataString);
        }
        catch(Exception e){
            isVaildJsonObject = false;
        }

        return isVaildJsonObject;
    }

    public static boolean isEmptyGeoJson(String jsonString){
        boolean isEmpty = false;
        //geometry
        JSONParser parser = new JSONParser();
        try{
            JSONObject geometry  = (JSONObject)parser.parse(jsonString) ;

            if(geometry.size() == 0){
                isEmpty = true;
            }

            JSONObject a = (JSONObject)geometry.get("geometry");

            if(a.size() == 0){
                isEmpty = true;
            }
        }
        catch(Exception e){
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isValidEMSCJson(String json) {
        boolean valid = false;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;

            if(jsonObject.get("token") == null){
                return false;
            }

            if(jsonObject.get("crisis_code") == null){
                return false;
            }

            if(jsonObject.get("event_code") == null){
                return false;
            }

            if(jsonObject.get("geo")== null){
                return false;
            }

            if(jsonObject.get("durationInHours") == null ){
                return false;
            }
            //max_collections

            if(jsonObject.get("max_collections") == null ){
                return false;
            }

            valid = true;

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        return valid;
    }
}

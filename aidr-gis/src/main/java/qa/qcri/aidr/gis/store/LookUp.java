package qa.qcri.aidr.gis.store;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 6/3/14
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class LookUp {


    // public static String MAP_GEOJSON_URL = "http://pybossa-dev.qcri.org/AIDRTrainerAPI/rest/geo/JSON/geoMap";
    //public static String MAP_GEOJSON_URL = "http://pybossa-dev.qcri.org/AIDRTrainerAPI/esri/getGeoJson";
    public static String MAP_GEOJSON_URL = "http://94.125.228.197:1055/AIDRTrainerAPI/rest/geo/JSON/geoMap";

    public static String DEFAULT_ESRI_GEO_FILE_PATH = "/var/www/data/esri/";
    //public static String DEFAULT_ESRI_GEO_FILE_PATH = "";
    public static String DEFAULT_ESRI_STORY_MAP_FILE_NAME = "esri";
    public static String DEFAULT_ESRI_STORY_MAP_FILE_EXTENSION = ".geojson";

    public static String DEFAULT_ESRI_DATA = "http://clickers.micromappers.com/esri_data/";

    public static String MANAGER_URL = "http://localhost:8080/AIDRFetchManager";
    public static String MANAGER_PUBLIC_COLLECTION_SERVICE = "/public/collection/";

    public static int BOUNDING_BOX_ELEMENT_SIZE = 4;

    public static final String COMMA_SEPERATEOR = ",";
    public static final String EMPTY_STRING = "";
}

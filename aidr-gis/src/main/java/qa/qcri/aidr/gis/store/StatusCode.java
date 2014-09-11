package qa.qcri.aidr.gis.store;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusCode {

    public static final Integer NEVER_STARTED = 0;
    public static final Integer RUNNING = 1;
    public static final Integer EXPIRED = 2;
    public static final Integer UNKNOWN_ERROR = 3;


    public static final Integer NOT_VALID_JSON = 600;
    public static final Integer EMPTY_JSON = 601;
    public static final Integer REQUIRED_FIELD_MISSING = 602;
    public static final Integer AUTHENTICATION_FAILED = 603;


    public static final Integer PARSING_ERROR = 500;
    public static final Integer SERVER_ERROR = 501;

    public static final Integer SUCCESS_OK = 200;
    public static final Integer SUCCESS_NO_CONTENT = 204;
    public static final Integer SERVICE_DEFAULT_RETURN_CODE = -1;
    public static final Integer SERVICE_DEFAULT_FAIL = -1;

}

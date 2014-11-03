package mock;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/8/14
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class EMSCMock {

    public static String getEarthQuakeRequestMockData(){
        //old : 51.29027104637403,25.799896283494462
        //new : 105.85195399999999,29.787701999999978
        StringBuffer sb = new StringBuffer();
        sb.append("{");

        sb.append("\"crisis_code\": \"128390298CEA-146927464\"");
        sb.append(",");

        sb.append("\"name\": \"M5.4 NEW BRITAIN REGION, P.N.G. (EMSC-391087)\"");
        sb.append(",");

        sb.append("\"event_code\": \"EMSC-391087\"");
        sb.append(",");

        sb.append("\"token\": \"8f61035a-d9b1-11e3-9364-03a5361ff574\"");
        sb.append(",");

        sb.append("\"magnitude\": 4.4");
        sb.append(",");

        sb.append("\"durationInHours\": 6");
        sb.append(",");

        sb.append("\"geo\": \"126.24,38.21,127.05,39.48\"");
        sb.append(",");

        sb.append("\"epicenter\": \"9.96,124.13\"");
        sb.append(",");

        sb.append("\"max_collections\": 2");

        sb.append("}");

        return sb.toString();
    }

}

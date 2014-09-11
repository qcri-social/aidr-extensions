package qa.qcri.aidr.gis.service;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */

public interface EMSCService {

    Integer processNewEarthQuakeRequest(String jsonString);
    void processExpirationCheck() throws Exception ;

}

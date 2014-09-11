package qa.qcri.aidr.gis.service;


/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 5/12/14
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticateTokenService {

    public Boolean isAuthorized(String token);
}

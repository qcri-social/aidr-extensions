package qa.qcri.aidr.gis.dao;

import qa.qcri.aidr.gis.entity.AuthenticateToken;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticateTokenDao extends AbstractDao<AuthenticateToken, String> {

    AuthenticateToken findActiveByToken(String token);
}

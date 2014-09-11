package qa.qcri.aidr.gis.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import qa.qcri.aidr.gis.dao.AuthenticateTokenDao;
import qa.qcri.aidr.gis.entity.AuthenticateToken;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AuthenticateTokenDaoImpl extends AbstractDaoImpl<AuthenticateToken, String> implements AuthenticateTokenDao {

    protected AuthenticateTokenDaoImpl(){
        super(AuthenticateToken.class);
    }

    @Override
    public AuthenticateToken findActiveByToken(String token) {
        return findByCriterionID(Restrictions.eq("token", token));  //To change body of implemented methods use File | Settings | File Templates.
    }
}

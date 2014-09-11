package qa.qcri.aidr.gis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.qcri.aidr.gis.dao.AuthenticateTokenDao;
import qa.qcri.aidr.gis.service.AuthenticateTokenService;


/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 5/12/14
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("authenticateTokenService")
@Transactional(readOnly = true)
public class AuthenticateTokenServiceImpl implements AuthenticateTokenService {

    @Autowired
    AuthenticateTokenDao tokenDao;

    @Override
    public Boolean isAuthorized(String token) {
        return tokenDao.findActiveByToken(token) != null;
    }
}

package qa.qcri.aidr.gis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qa.qcri.aidr.gis.entity.AuthenticateToken;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/9/14
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/hibernateContext.xml"})

public class AuthenticateTokenServiceTest {

    @Autowired
    AuthenticateTokenService authenticateTokenService;

    @Test
    public void testIsAuthorized() throws Exception {
        boolean a = authenticateTokenService.isAuthorized("8f61035a-d9b1-11e3-9364-03a5361ff574");
        System.out.print("a:" + a);

    }
}

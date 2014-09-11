package qa.qcri.aidr.gis.service;

import mock.EMSCMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/9/14
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/hibernateContext.xml"})
public class EMSCServiceTest {

    @Autowired
    EMSCService emscService;

    @Test
    public void testProcessNewEarthQuakeRequest() throws Exception {
        int rvc = emscService.processNewEarthQuakeRequest("");
        System.out.println(rvc);

       // int rvc = emscService.processNewEarthQuakeRequest(EMSCMock.getEarthQuakeRequestMockData());
        //System.out.println(rvc);
    }
}

package qa.qcri.aidr.gis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qa.qcri.aidr.gis.dao.AuthenticateTokenDao;
import qa.qcri.aidr.gis.entity.AuthenticateToken;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/hibernateContext.xml"})
public class ESRIWorkerTest {

    @Autowired
    ESRIWorker esriWorker;


    @Test
    public void testGenerateGeoJson() throws Exception {
        //esriWorker.generateGeoJson();

    }
}

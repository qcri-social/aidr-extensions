package qa.qcri.aidr.gis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qa.qcri.aidr.gis.service.EMSCService;
import qa.qcri.aidr.gis.service.ESRIWorker;
import qa.qcri.aidr.gis.service.Worker;


/**
 * A synchronous worker
 */
@Component("syncWorker")
public class SyncWorker implements Worker {

	protected static Logger logger = Logger.getLogger("SyncWorker");

    //@Autowired
    //ESRIWorker esriWorker;

    @Autowired
    EMSCService emscService;



	public void work() {
		String threadName = Thread.currentThread().getName();
		//logger.debug("   " + threadName + " has began working.(SyncWorker - run ClientApps)");
        logger.info("scheduled worker is starting");
        try {
             //Disable esri due to upgrade
            //esriWorker.generateGeoJson();
            System.out.println("processExpirationCheck is tring");
            emscService.processExpirationCheck();
            //TimeUnit.MINUTES.toMillis(yourMinutes)

            System.out.println("processExpirationCheck is after");

            Thread.sleep(1800000); // simulates work
            //Thread.sleep(180000);

        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //logger.debug("   " + threadName + " has completed work.(SyncWorker - run ClientApps)");
        logger.info("esriWorker is going sleep");
    }
	
}

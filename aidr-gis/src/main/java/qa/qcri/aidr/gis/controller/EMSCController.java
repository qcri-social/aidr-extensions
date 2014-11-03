package qa.qcri.aidr.gis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qa.qcri.aidr.gis.service.EMSCService;
import qa.qcri.aidr.gis.store.LookUp;


/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/emsc")
public class EMSCController {

    private static Logger logger = Logger.getLogger(EMSCController.class);

    @Autowired
    EMSCService emscService;

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public @ResponseBody String testing() {
        //System.out.println("fileURL : " + LookUp.DEFAULT_ESRI_DATA + fileURL);
        return "testing";
    }
    @RequestMapping(value="/consumes", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<String> uriComponentsBuilder(@RequestBody String jsonString) {

        int returnCode = emscService.processNewEarthQuakeRequest(jsonString);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>("{\"status\":" + returnCode+"}",
                headers, HttpStatus.OK);

    }



}

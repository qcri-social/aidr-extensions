package qa.qcri.aidr.gis.service.impl;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.qcri.aidr.gis.bean.BoundingBox;
import qa.qcri.aidr.gis.bean.CollectionBean;
import qa.qcri.aidr.gis.dao.EMSCEarthQuakeDao;
import qa.qcri.aidr.gis.entity.EMSCEarthQuake;
import qa.qcri.aidr.gis.service.AuthenticateTokenService;
import qa.qcri.aidr.gis.service.EMSCService;
import qa.qcri.aidr.gis.store.LookUp;
import qa.qcri.aidr.gis.store.StatusCode;
import qa.qcri.aidr.gis.util.Communicator;
import qa.qcri.aidr.gis.util.DataFormatValidator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("emscService")
@Transactional(readOnly = true)
public class EMSCServiceImpl implements EMSCService {

    protected static Logger logger = Logger.getLogger(EMSCService.class);

    @Autowired
    EMSCEarthQuakeDao emscEarthQuakeDao;

    @Autowired
    AuthenticateTokenService authenticateTokenService;


    @Override
    @Transactional(readOnly = false)
    public Integer processNewEarthQuakeRequest(String jsonString) {
        logger.info("processNewEarthQuakeRequest *************************");

        int returnCode = StatusCode.SERVICE_DEFAULT_RETURN_CODE;
        if(!DataFormatValidator.isValidateJson(jsonString)  ){
            return StatusCode.NOT_VALID_JSON;
        }
        if(!DataFormatValidator.isValidEMSCJson(jsonString)){
            return StatusCode.REQUIRED_FIELD_MISSING;
        }
        JSONParser parser;
        Object obj;

            try{
                parser = new JSONParser();
                obj = parser.parse(jsonString);

                JSONObject jsonObject = (JSONObject) obj;
                String token = (String)jsonObject.get("token");

                if(!authenticateTokenService.isAuthorized(token)){
                    return StatusCode.AUTHENTICATION_FAILED;
                }

                String code = (String) jsonObject.get("crisis_code");
                String name = (String) jsonObject.get("name");
                String geo = (String) jsonObject.get("geo");
                double magnitude = (Double) jsonObject.get("magnitude");
                String epicenter = (String) jsonObject.get("epicenter");
                long max_collections = (Long) jsonObject.get("max_collections");
                long defaultHours = (Long)jsonObject.get("durationInHours") ;

                Date start = new Date();
                Date stop = null ;
                Calendar c = Calendar.getInstance();
                c.setTime(start);
                c.add(Calendar.HOUR, (int)defaultHours);
                stop = c.getTime();

                returnCode = this.processNewGeoForActiveCollection(code, (int)max_collections,geo,token,(int)defaultHours);

                if(returnCode!=StatusCode.SERVICE_DEFAULT_RETURN_CODE && returnCode != StatusCode.SERVICE_DEFAULT_FAIL){
                    EMSCEarthQuake emsc = new EMSCEarthQuake(name, code, token,magnitude,
                            (int)defaultHours, geo,epicenter,
                            StatusCode.RUNNING,start, stop,code );

                    emscEarthQuakeDao.addNewEarthQuake(emsc);
                }
            }
            catch(ParseException ps){
                logger.error(ps);
                returnCode= StatusCode.PARSING_ERROR;
            }
            catch(Exception e){
                logger.error(e);
                returnCode= StatusCode.SERVER_ERROR;
            }
            finally{
                parser = null;
                obj = null;
            }

        return returnCode;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private int processNewGeoForActiveCollection(String code, int max_collections, String requestedGeo, String token, Integer defaultHours){
        int returncCode = StatusCode.SERVICE_DEFAULT_RETURN_CODE;

        try {
            Communicator com = new Communicator();

            JSONObject data = this.getAIDRCollectionDetails(com, code);

            String geo = (String)data.get("geo");
            Long id = (Long)data.get("id");

            List<String> list = Arrays.asList(geo.split(",")) ;

            int geoCollectionSize = list.size() / LookUp.BOUNDING_BOX_ELEMENT_SIZE;

            if(geoCollectionSize >= max_collections)  {
                geo = "";
                for(int i = 0; i < list.size(); i++){
                    if(i >= LookUp.BOUNDING_BOX_ELEMENT_SIZE){
                        if(!geo.isEmpty()){
                            geo = geo + LookUp.COMMA_SEPERATEOR + list.get(i);
                        }
                        else{
                            geo = list.get(i);
                        }

                    }
                }

            }

            String updatableGeo = geo ;

            if(!geo.isEmpty()){
                updatableGeo =  updatableGeo + LookUp.COMMA_SEPERATEOR;
            }

            updatableGeo = updatableGeo + requestedGeo;

            returncCode = this.pushCollectionDetailUpdate(id, updatableGeo, token, com, defaultHours, true);

            if(returncCode == -1){
                logger.error("***********  failed : check manager!!!!  ****************************");
                logger.error(String.format("EMSC processNewGeoForActiveCollection id: %s geoInfo: %s failed",id, updatableGeo));
            }

        } catch (ParseException e) {
            logger.error(e);
            logger.error(String.format("EMSC processNewGeoForActiveCollection ParseException : %s",e));
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (Exception e1) {
            logger.error(e1);
            logger.error(String.format("EMSC processNewGeoForActiveCollection Exception : %s",e1));
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return returncCode;
    }

    @Override
    @Transactional(readOnly = false)
    public void processExpirationCheck() throws Exception{
        List<EMSCEarthQuake> crisisList = emscEarthQuakeDao.getCrisisCode();
        logger.info("crisisList :" + crisisList.size());
        for (Object temp : crisisList){
            String crisisCode = (String) temp;
            logger.info("crisisCode :" + crisisCode);
            List<EMSCEarthQuake> quakeList = emscEarthQuakeDao.getEeathQuakeByStatusAndCrisisCode(StatusCode.RUNNING, crisisCode);
            this.processActiveCollectionBatch(quakeList, crisisCode);
        }

    }

    private void processActiveCollectionBatch(List<EMSCEarthQuake> quakeList, String crisisCode) throws Exception{

        logger.info("processActiveCollectionBatch");
        Communicator com = new Communicator();
        CollectionBean collectionBean = this.createCollectionBean(com, crisisCode);

        if(collectionBean.getBoundingBoxList().size() > 1){
            logger.info("collectionBean.getBoundingBoxList().size() should be greater than 1 to process batch: " +  collectionBean.getBoundingBoxList().size());
            for (EMSCEarthQuake temp : quakeList) {
                Date currenDateTime = new Date();
                logger.info("currenDateTime:" + currenDateTime);
                logger.info("temp.getScheduled_stop():" + temp.getScheduled_stop());

                if(temp.getScheduled_stop().compareTo(currenDateTime) < 1){
                    String emscGeo =  temp.getGeo();
                    for (BoundingBox bBox : collectionBean.getBoundingBoxList()){
                        if(bBox.getCoordinates().equalsIgnoreCase(emscGeo))
                        {
                            bBox.setDeactivationStatus(true);
                            emscEarthQuakeDao.updateEMSCEarthQuakeStatus(StatusCode.EXPIRED, temp.getId());

                        }
                    }
                }
            }

            if(collectionBean.getBoundingBoxList().size() > 0){
                StringBuffer sb = new StringBuffer() ;
                for(int i = 0; i < collectionBean.getBoundingBoxList().size(); i++){
                    if(!collectionBean.getBoundingBoxList().get(i).isDeactivationStatus()){
                        if(!sb.toString().isEmpty()){
                            sb.append(LookUp.COMMA_SEPERATEOR);
                        }
                        sb.append(collectionBean.getBoundingBoxList().get(i).getCoordinates());
                    }
                }

                logger.info("sb.toString(): " + sb.toString());

                int returncCode = this.pushCollectionDetailUpdate(collectionBean.getId(), sb.toString(), LookUp.EMPTY_STRING, com, 0, false);
                logger.info("returncCode: " + returncCode);
            }
        }

    }

    private int pushCollectionDetailUpdate(long id, String updatableGeo, String token, Communicator com, int  durationInHours, boolean updateDuration){

        JSONObject pushObj = new JSONObject();
        pushObj.put("geo", updatableGeo);
        pushObj.put("token", token);
        pushObj.put("id", id);
        pushObj.put("durationInHours", durationInHours);
        pushObj.put("updateDuration", updateDuration);

        String url2 = LookUp.MANAGER_URL + LookUp.MANAGER_PUBLIC_COLLECTION_SERVICE + "updateGeo.action";

        int returncCode = com.sendPost(pushObj.toJSONString(), url2) ;

        if(returncCode == -1){
            logger.error("failed");
        }

        return returncCode;
    }

    private CollectionBean createCollectionBean(Communicator com, String crisisCode) throws Exception{

        JSONObject data = this.getAIDRCollectionDetails(com, crisisCode);
        List<BoundingBox> boundingBoxList = new ArrayList<BoundingBox>();

        String geo = (String)data.get("geo");
        Long id = (Long)data.get("id");

        List<String> list = Arrays.asList(geo.split(",")) ;


        int geoCollectionSize = list.size() / LookUp.BOUNDING_BOX_ELEMENT_SIZE;

        for(int i = 0; i < geoCollectionSize; i++){
            int index = LookUp.BOUNDING_BOX_ELEMENT_SIZE * i;
            String  geoTemp = list.get(index)+ LookUp.COMMA_SEPERATEOR +list.get(index+1)+ LookUp.COMMA_SEPERATEOR +list.get(index+2)+ LookUp.COMMA_SEPERATEOR +list.get(index+3);
            BoundingBox javaBean = new BoundingBox(index, geoTemp);
            boundingBoxList.add(javaBean);
        }

        return new CollectionBean(id, boundingBoxList);

    }

    private JSONObject getAIDRCollectionDetails(Communicator com, String crisisCode) throws Exception{

        String url = LookUp.MANAGER_URL + LookUp.MANAGER_PUBLIC_COLLECTION_SERVICE + "findByRequestCode.action?code=" + crisisCode;
        com = new Communicator();
        String returnValue =  com.sendGet(url);


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(returnValue);

        JSONObject jsonObject = (JSONObject) obj;
        JSONObject data = (JSONObject)jsonObject.get("data");

        return data;
    }
}

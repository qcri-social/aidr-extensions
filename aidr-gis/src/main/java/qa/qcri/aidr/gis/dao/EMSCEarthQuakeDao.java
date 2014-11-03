package qa.qcri.aidr.gis.dao;

import qa.qcri.aidr.gis.entity.EMSCEarthQuake;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EMSCEarthQuakeDao extends AbstractDao<EMSCEarthQuake, String> {

    void addNewEarthQuake(EMSCEarthQuake earthQuake);
    List<EMSCEarthQuake> getEeathQuakeByStatus(Integer status);
    List<EMSCEarthQuake> getEeathQuakeByStatusAndCrisisCode(Integer status, String crisisCode);
    List<EMSCEarthQuake> getEeathQuakeByCrisisCode(String crisisCode);
    List<EMSCEarthQuake> getCrisisCode();
    void updateEMSCEarthQuakeStatus(Integer status, Long id);
}

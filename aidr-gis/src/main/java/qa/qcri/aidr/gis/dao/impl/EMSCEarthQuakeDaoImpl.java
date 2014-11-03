package qa.qcri.aidr.gis.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import qa.qcri.aidr.gis.dao.EMSCEarthQuakeDao;
import qa.qcri.aidr.gis.entity.EMSCEarthQuake;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EMSCEarthQuakeDaoImpl extends AbstractDaoImpl<EMSCEarthQuake, String> implements EMSCEarthQuakeDao {

    protected EMSCEarthQuakeDaoImpl(){
        super(EMSCEarthQuake.class);
    }

    @Override
    public void addNewEarthQuake(EMSCEarthQuake earthQuake) {
        save(earthQuake);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<EMSCEarthQuake> getEeathQuakeByStatus(Integer status) {
        return findByCriteria(Restrictions.eq("status", status)); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<EMSCEarthQuake> getEeathQuakeByStatusAndCrisisCode(Integer status, String crisisCode) {
        return findByCriteria(Restrictions.conjunction()
                .add(Restrictions.eq("crisis_code",crisisCode))
                .add(Restrictions.eq("status",status)));

    }

    @Override
    public List<EMSCEarthQuake> getEeathQuakeByCrisisCode(String crisisCode) {
        return findByCriteria(Restrictions.eq("crisis_code", crisisCode));

    }

    @Override
    public List<EMSCEarthQuake> getCrisisCode() {
        return findAllByFieldName("crisis_code");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateEMSCEarthQuakeStatus(Integer status, Long id) {
        List<EMSCEarthQuake> rs = findByCriteria(Restrictions.eq("id", id));
        if(rs.size() > 0){
            EMSCEarthQuake emscEarthQuake = rs.get(0);
            emscEarthQuake.setStatus(status);
            saveOrUpdate(emscEarthQuake);
        }
    }
}

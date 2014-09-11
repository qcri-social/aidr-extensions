package qa.qcri.aidr.gis.bean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/10/14
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionBean {

    private Long id;
    private List<BoundingBox> boundingBoxList;

    public CollectionBean(long id,List<BoundingBox> boundingBoxList){
        this.id = id;
        this.boundingBoxList = boundingBoxList;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BoundingBox> getBoundingBoxList() {
        return boundingBoxList;
    }

    public void setBoundingBoxList(List<BoundingBox> boundingBoxList) {
        this.boundingBoxList = boundingBoxList;
    }
}

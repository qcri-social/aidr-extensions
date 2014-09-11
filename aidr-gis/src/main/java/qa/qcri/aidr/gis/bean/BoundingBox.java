package qa.qcri.aidr.gis.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/10/14
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoundingBox {
    private String coordinates;
    private int index;
    private boolean deactivationStatus = false;

    public BoundingBox(int index, String coordinates){
        this.index = index;
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isDeactivationStatus() {
        return deactivationStatus;
    }

    public void setDeactivationStatus(boolean deactivationStatus) {
        this.deactivationStatus = deactivationStatus;
    }
}

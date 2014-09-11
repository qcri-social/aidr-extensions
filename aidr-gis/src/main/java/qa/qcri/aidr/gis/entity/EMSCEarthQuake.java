package qa.qcri.aidr.gis.entity;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 9/7/14
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(catalog = "aidr_gis", name = "emsc_earthquake")
public class EMSCEarthQuake implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    private static Logger logger = Logger.getLogger(EMSCEarthQuake.class);

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column (name = "status", nullable = false)
    private Integer status;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "event_code", nullable = false)
    private String eventCode;

    @Column (name = "crisis_code", nullable = false)
    private String crisis_code;

    @Column (name = "token", nullable = false)
    private String token;

    @Column (name = "magnitude", nullable = false)
    private double magnitude;

    @Column (name = "durationInHours", nullable = false)
    private Integer durationInHours;

    @Column (name = "geo", nullable = false)
    private String geo;

    @Column (name = "epicenter", nullable = false)
    private String epicenter;

    @Column (name = "started_at", nullable = false)
    private Date started_at;

    @Column (name = "scheduled_stop", nullable = false)
    private Date scheduled_stop;

    @Column (name = "updated", nullable = false)
    private Date updated;


    public EMSCEarthQuake(){

    }

    public EMSCEarthQuake(String name, String eventCode, String token,
                          double magnitude, int durationInHours, String geo,
                          String epicenter, int status, Date startedAt, Date scheduledStop, String crisisCode){
        this.name = name;
        this.eventCode = eventCode;
        this.token = token;
        this.magnitude = magnitude;
        this.durationInHours = durationInHours;
        this.geo = geo;
        this.epicenter = epicenter;
        this.status = status;
        this.started_at = startedAt;
        this.scheduled_stop = scheduledStop;
        this.crisis_code = crisisCode;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getEpicenter() {
        return epicenter;
    }

    public void setEpicenter(String epicenter) {
        this.epicenter = epicenter;
    }

    public Date getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Date started_at) {
        this.started_at = started_at;
    }

    public Date getScheduled_stop() {
        return scheduled_stop;
    }

    public void setScheduled_stop(Date scheduled_stop) {
        this.scheduled_stop = scheduled_stop;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getCrisis_code() {
        return crisis_code;
    }

    public void setCrisis_code(String crisis_code) {
        this.crisis_code = crisis_code;
    }
}

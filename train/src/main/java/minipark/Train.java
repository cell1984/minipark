package minipark;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Train_table")
public class Train {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long trainId;
    private String trainType;
    private String start;
    private String destination;
    private Integer reservableSeat;

    @PostPersist
    public void onPostPersist(){
        TrainRegistered trainRegistered = new TrainRegistered();
        BeanUtils.copyProperties(this, trainRegistered);
        trainRegistered.publishAfterCommit();

    }

    @PostUpdate
    public void onPostUpdate(){
        SeatModified seatModified = new SeatModified();
        BeanUtils.copyProperties(this, seatModified);
        seatModified.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }
    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Integer getReservableSeat() {
        return reservableSeat;
    }

    public void setReservableSeat(Integer reservableSeat) {
        this.reservableSeat = reservableSeat;
    }




}

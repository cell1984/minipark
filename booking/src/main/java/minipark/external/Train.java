package minipark.external;

public class Train {

    private Long id;
    private Long trainId;
    private String trainType;
    private String start;
    private String destination;
    private Integer reservableSeat;

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

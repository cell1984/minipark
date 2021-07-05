package minipark;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Booking_table")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String trainId;
    private Long seats;
    private String status;

    // *************************************************************************
    // 동기호출 : FeignClient를 통한 구현
    // *************************************************************************
    // Booking 수행시, Train에 사전 등록된 좌석수(혹은 타 Booking을 통해 차감된 최종 남은 좌석수)를 초과하여 예약시도 할 경우,
    // 신규 Booking의 persist(POST)가 되지 않도록 처리하여야 한다.
    //
    // 따라서 PostPersist를 하고 예외처리 발생해서 문제 해결함
    // Train TrainService에서 좌석수 체크 (http://localhost:8083/trains의 /chkAndModifySeat) 결과가 false 로 나올 경우
    // 강제 Exception을 발생시켜서, Booking 이 POST되지 않도록 처리한다.
    // *************************************************************************

    //@PostPersist //onPostPersist() 
    @PostPersist
    public void onPostPersist() throws Exception {

        boolean rslt = BookingApplication.applicationContext.getBean(minipark.external.TrainService.class).modifySeat(this.getTrainId(), this.getSeats().intValue());

        if(rslt){
            System.out.println("##### Booking - Result : true #####");
            this.setStatus("SeatsReserved");
            Booked booked = new Booked();
            BeanUtils.copyProperties(this, booked);
            booked.publishAfterCommit();
        }else{
            System.out.println("##### Booking - Result : false - too big seats count #####");
            throw new Exception("Too big seats Count");
        }

    }

    @PreRemove
    public void onPreRemove(){
        Canceled canceled = new Canceled();
        canceled.setId(this.getId());
        canceled.setStatus("SeatCanceled");
        BeanUtils.copyProperties(this, canceled);
        canceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
    public Long getSeats() {
        return seats;
    }

    public void setSeats(Long seats) {
        this.seats = seats;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}

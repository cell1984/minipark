package minipark;

import minipark.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired 
    TrainRepository trainRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_IncreaseSeat(@Payload Canceled canceled){

        if(!canceled.validate()) return;

        Long trainId = Long.valueOf(canceled.getTrainId());
        Train train = trainRepository.findByTrainId(trainId);
        train.setReservableSeat(train.getReservableSeat()+canceled.getSeats().intValue());
        trainRepository.save(train);
             
    }

}

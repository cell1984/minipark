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
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBooked_ApprovePayment(@Payload Booked booked){

        //if(!booked.validate()) return;
        if(booked.validate()){
            System.out.println("\n\n##### listener ApprovePayment : " + booked.toJson() + "\n\n");    

            Payment payment = new Payment();
            payment.setBookingId(booked.getId());
            payment.setStatus("PaymentApproved");
            paymentRepository.save(payment);

        }
        

            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_CancelPayment(@Payload Canceled canceled){

        //if(!canceled.validate()) return;

        if(canceled.validate()){
            System.out.println("\n\n##### listener CancelPayment : " + canceled.toJson() + "\n\n");    

            Payment payment = paymentRepository.findByBookingId(canceled.getId());
            payment.setStatus("PaymentCanceled");
            paymentRepository.save(payment);
        }
        
            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}

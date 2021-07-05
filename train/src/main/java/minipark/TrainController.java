package minipark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class TrainController {
        @Autowired
        TrainRepository trainRepository;

        @RequestMapping(value = "/chkAndModifySeat",
                        method = RequestMethod.GET,
                        produces = "application/json;charset=UTF-8")

        public boolean modifySeat(HttpServletRequest request, HttpServletResponse response) throws Exception {
                System.out.println("##### /train/modifySeat  called #####");

                boolean status = false;
                Long trainId = Long.valueOf(request.getParameter("trainId"));
                int seats = Integer.parseInt(request.getParameter("seats"));

                Train train = trainRepository.findByTrainId(trainId);
                

                if(train.getReservableSeat() >= seats) {
                        status = true;
                        train.setReservableSeat(train.getReservableSeat()-seats);
                        trainRepository.save(train);
                }
                return status;
        }


 }

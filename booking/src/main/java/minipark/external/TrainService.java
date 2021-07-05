
package minipark.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

//@FeignClient(name="train", url="http://localhost:8083")
@FeignClient(name="train", url="${api.url.train}")
public interface TrainService {

    @RequestMapping(method= RequestMethod.GET, path="/chkAndModifySeat")
    public boolean modifySeat(@RequestParam("trainId") String trainId,
    @RequestParam("seats") int seatCount);

}
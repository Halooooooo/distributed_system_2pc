package com.example.coordinate;

import com.example.coordinate.feigninterface.TwoPhaseCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: Halo
 * @date: 2019/5/7 17:47
 * @Description:
 */
@RestController
public class CoordinateController {


    @Autowired
    CoordinateService coordinateService;

    @GetMapping("/begin")
    public String begin(){
        UUID uuid = UUID.randomUUID();
        if(coordinateService.ask(uuid.toString())){
            if(!coordinateService.commit(uuid.toString())){
                coordinateService.mysqlRollbac(uuid.toString());
            }
            // commit
        }else{
            // rock
            coordinateService.rollbac(uuid.toString());
        }
        return "success";
    }
}

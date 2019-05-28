package com.example.coordinate;

import com.example.coordinate.feigninterface.TwoPhaseCommit;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Halo
 * @date: 2019/5/7 17:49
 * @Description:
 */

@Import(FeignClientsConfiguration.class)
@Configuration
@Service
public class CoordinateService {

    @Autowired
    @Qualifier("twoPhaseCommit1")
    private TwoPhaseCommit partA;
    @Autowired
    @Qualifier("twoPhaseCommit2")
    private TwoPhaseCommit partB;
    private String sql = "INSERT INTO `testforqq`.`test` (`id`, `name`) VALUES ('1', '2');";


    public CoordinateService() {

    }


    public Boolean ask(String id){
        String askA = partA.ask(id);
//        String askB = partB.ask(id);
//        boolean B = "success".equals(askB);
        boolean A = "success".equals(askA);
        return A&&true;
    }


    public boolean commit(String id) {
        String commitA = partA.commit(id);
//        String commitB = partB.commit(id);
//        boolean B = "ACK".equals(commitB);
        boolean A = "ACK".equals(commitA);
        return A&&true;
    }
    // undo redo 日志回滚
    public void mysqlRollbac(String toString) {

    }

    public void rollbac(String id) {
        String rollbacA = partA.rollbac(id);
        String rollbacB = partB.rollbac(id);
    }
}

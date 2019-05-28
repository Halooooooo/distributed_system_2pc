package com.example.participantB;

import com.example.participantB.dao.PersonDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Halo
 * @date: 2019/5/8 9:49
 * @Description:
 */
@RestController
public class ParticipantBController {

    public static final ConcurrentHashMap<String, SqlSession> storeSQLSession = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, TransactionStatus> storeTM = new ConcurrentHashMap<>();

    @Autowired
    PersonDao personDao;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    DataSourceTransactionManager transactionManager;
    @Autowired
    PersonService personService;
    @GetMapping("/test")
    public String test(){
        personService.insert();
        return "success";
    }
    @GetMapping("/test1")
    public String test1(){
        personService.testtx("1");
        return "success";
    }
//    @PostMapping("/api/first_phase/ask")
//    public String ask(@RequestBody String id){
//        System.out.println("ask"+id);
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        TransactionStatus status = transactionManager.getTransaction(def);
//
//        SqlSession sqlSession = sqlSessionFactory.openSession(false);
//        try {
//            sqlSession.insert("com.example.participantB.dao.PersonDao.insertP");
//            storeSQLSession.put(id, sqlSession);
//            personDao.insertP();
//            storeTM.put(id,status);
//        }catch (Exception e){
//            transactionManager.rollback(status);
//            e.printStackTrace();
//            sqlSession.rollback();
//            sqlSession.close();
//            return "error";
//        }
//        return "success";
//    }
//
//    @PostMapping("/api/two_phase/commit")
//    public String commit(@RequestBody String id){
//        System.out.println("commit"+id);
//        SqlSession remove = storeSQLSession.remove(id);
//        TransactionStatus transactionStatus = storeTM.get(id);
//        if(Objects.nonNull(remove)) {
//            try {
//                remove.commit();
//                remove.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        if(Objects.nonNull(transactionStatus)){
//            transactionManager.commit(transactionStatus);
//            storeTM.remove(id);
//        }
//        return "ACK";
//    }
//    @PostMapping("/api/two_phase/rollbac")
//    public String rollbac(@RequestBody String id){
//        System.out.println("rollbac"+id);
//        TransactionStatus transactionStatus = storeTM.get(id);
//        SqlSession remove = storeSQLSession.remove(id);
//        if(Objects.nonNull(remove)) {
//            remove.rollback();
//            remove.close();
//        }
//        if(Objects.nonNull(transactionStatus)){
//            transactionManager.rollback(transactionStatus);
//            storeTM.remove(id);
//        }
//        return "ACK";
//    }
//
    @PostMapping("/api/first_phase/ask")
    public String ask(@RequestBody String id){
        System.out.println("ask"+id);

        try {
            personService.testtx(id);
        }catch (Exception e){
            personService.testRollback(id);
            return "error";
        }
        return "success";
    }

    @PostMapping("/api/two_phase/commit")
    public String commit(@RequestBody String id){
        personService.testCommit(id);
        return "ACK";
    }
    @PostMapping("/api/two_phase/rollbac")
    public String rollbac(@RequestBody String id){
        personService.testRollback(id);
        return "ACK";
    }

}

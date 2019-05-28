package com.example.participantC.tx;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author: Halo
 * @date: 2019/5/16 9:23
 * @Description:
 */
//@Component

public class TranscactionLister {

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void afterCommit(){
//        System.out.println("afterCommit");
//    }
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
//    public void afterCompletion(){
//        System.out.println("afterCompletion");
//
//    }
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
//    public void afterRollback(){
//        System.out.println("afterRollback");
//
//    }
//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    public void beforeCommit(){
//        System.out.println("beforeCommit");
//
//    }
}

package com.example.coordinate.feigninterface;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Halo
 * @date: 2019/5/7 17:16
 * @Description:
 */
public interface TwoPhaseCommit {
    @RequestMapping(value = "/api/first_phase/ask",method = RequestMethod.POST)
    String ask(String id);
    @RequestMapping(value = "/api/two_phase/commit",method = RequestMethod.POST)
    String commit(String id);
    @RequestMapping(value = "/api/two_phase/rollbac",method = RequestMethod.POST)
    String rollbac(String id);
}

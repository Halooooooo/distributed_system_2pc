package com.example.participantB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    TestAspect testAspect;
    @Autowired
    PersonService personService;
    @Test
    public void contextLoads() {
        personService.insert();
    }

}

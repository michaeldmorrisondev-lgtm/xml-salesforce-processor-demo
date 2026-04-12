package com.mmp.xmlproject.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmp.xmlproject.rest.model.*;

@RestController
public class RestEndpoint {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

}

package com.mmp.xmlproject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmp.xmlproject.controller.SubmissionController;

@RestController
public class RestEndpoint {
    
    @Autowired
    SubmissionController subCntrlr;

    @CrossOrigin
    @RequestMapping("/processRequest")
    public String processRequest(@RequestParam(value="id") String id) {
        
        subCntrlr.processXmlValidation(id);
        
        return "done";
    }

}

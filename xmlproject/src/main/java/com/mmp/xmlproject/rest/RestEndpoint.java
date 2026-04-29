package com.mmp.xmlproject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmp.xmlproject.services.SubmissionService;

@RestController
public class RestEndpoint {

    @Autowired
    SubmissionService subCntrlr;

    @CrossOrigin
    @RequestMapping("/processRequest")
    public ResponseEntity<String> processRequest(@RequestParam(value = "id") String id) {

        subCntrlr.processXmlValidation(id);

        return ResponseEntity
                .accepted()
                .body("Request received. Processing started for id: " + id);
    }
}
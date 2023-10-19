package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Tag(name="ZipCode from https://api.zippopotam.us/")
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipCodeQueryService;

    @Operation(summary = "Get a information about a zipcode")
    @GetMapping("/get")
    public ResponseEntity<String> getSubreddits(        
        @Parameter(name="zipcode", description="US zipcode", example="93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        
        String result = zipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}
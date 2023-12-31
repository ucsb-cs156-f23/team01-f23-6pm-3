package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
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

@Tag(name="Joke info from https://v2.jokeapi.dev/")
@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get a random joke")
    @GetMapping("/get")
    public ResponseEntity<String> getJoke(
        @Parameter(name="category", description="category of joke, e.g. 'Programming' or 'Spooky'", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", description="amount of jokes to get, e.g. '1' or '2'", example="1") @RequestParam int numJokes) 
        throws JsonProcessingException {
        String result = jokeQueryService.getJSON(category, numJokes);  
        return ResponseEntity.ok().body(result);
    }

}

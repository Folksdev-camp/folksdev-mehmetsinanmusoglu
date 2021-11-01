package com.folksdev.blog.controller;

import com.folksdev.blog.dto.CreateMovieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/movie")
public class MovieController {

    @GetMapping
    public ResponseEntity<String> getMovie() {
        return ResponseEntity.ok("Hello Folksdev");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getMovieWithId(@PathVariable String id) {
        return ResponseEntity.ok("Hello Folksdev");
    }

    @PostMapping
    public ResponseEntity<CreateMovieRequest> createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        return new ResponseEntity<>(createMovieRequest, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateMovie(@RequestBody String id) {
        return ResponseEntity.ok("id: " + id + " is updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id) {
        return ResponseEntity.ok("id: " + id + " is deleted");
    }

}

package com.example.reactive_streams_demo.controller;

import com.example.reactive_streams_demo.entity.Movie;
import com.example.reactive_streams_demo.service.MovieService;
import java.awt.PageAttributes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *   http://localhost:8080/movies/blocking
 *   http://localhost:8080/movies/non-blocking
 */

@RestController
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @GetMapping("/movies/blocking")
    public List<Movie> getAllMovies(){
        return movieService.loadAllMovies();
    }

    @GetMapping(value = "/movies/non-blocking",produces = MediaType.TEXT_EVENT_STREAM_VALUE) //
    public Flux<Movie> getAllMoviesStream(){
        return movieService.loadAllMoviesStream();
    }
}

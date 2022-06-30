package com.example.reactive_streams_demo.service;

import com.example.reactive_streams_demo.dao.MovieDao;
import com.example.reactive_streams_demo.entity.Movie;
 import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movie> loadAllMovies(){
        Long start= System.currentTimeMillis()/1000L;
         List<Movie> movies = movieDao.getMovies();
        Long end= System.currentTimeMillis()/1000L;

        System.out.println("Total Execution Time taken is: "+ (end - start) + " (sec)");
        System.out.println("Hello World 👋👋👋 !!!");

        return movies;
    }

    public Flux<Movie> loadAllMoviesStream(){
        Long start= System.currentTimeMillis()/1000;
          Flux<Movie> movies = movieDao.getMoviesStream();
        Long end= System.currentTimeMillis()/1000;

        System.out.println("Total Execution Time taken is: "+ (end - start) + " (sec)");
        System.out.println("Hello World 👋👋👋 !!!");

        return movies;
    }
}

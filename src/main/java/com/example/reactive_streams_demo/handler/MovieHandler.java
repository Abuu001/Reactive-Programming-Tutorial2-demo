package com.example.reactive_streams_demo.handler;

import com.example.reactive_streams_demo.dao.MovieDao;
import com.example.reactive_streams_demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieHandler {

    @Autowired
    private MovieDao movieDao;

    public Mono<ServerResponse> loadMovies(ServerRequest request){
       Flux<Movie> movieList =  movieDao.getMoviesList();
       return ServerResponse.status(200).body(movieList,Movie.class);
    }

    public Mono<ServerResponse> findMovie(ServerRequest request){
        Integer movieId = Integer.valueOf(request.pathVariable("movieId"));

        Mono<Movie> movie =  movieDao.getMoviesList()
                              .filter(i -> i.getUuid() == movieId).next();
/*
     or can also be written as this
        Mono<Movie> movie =  movieDao.getMoviesList()
                .filter(i -> i.getUuid() == movieId).take(1).single();
*/


        return ServerResponse.status(200).body(movie , Movie.class);
    }

    public Mono<ServerResponse> saveMovies(ServerRequest request){
        Mono<Movie> movie = request.bodyToMono(Movie.class);
        Mono<String> res = movie.map(dto -> dto.getUuid() + ":" + dto.getName());

        return ServerResponse.status(200).body(res,String.class);
    }

}

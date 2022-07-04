package com.example.reactive_streams_demo.handler;

import com.example.reactive_streams_demo.dao.MovieDao;
import com.example.reactive_streams_demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouterStreamHandler {

    @Autowired
    private MovieDao movieDao;

    public Mono<ServerResponse> getMoviesStream(ServerRequest request){
        Flux<Movie> movieStream =  movieDao.getMoviesStream();
        return ServerResponse.status(200)
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(movieStream,Movie.class);
    }
}

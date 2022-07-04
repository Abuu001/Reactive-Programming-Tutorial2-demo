package com.example.reactive_streams_demo.router;


import com.example.reactive_streams_demo.handler.MovieHandler;
import com.example.reactive_streams_demo.handler.RouterStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private MovieHandler movieHandler;

    @Autowired
    private RouterStreamHandler routerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/movies",movieHandler::loadMovies)
                .GET("/router/movies/stream",routerStreamHandler::getMoviesStream)
                .GET("/router/movies/{movieId}",movieHandler::findMovie)
                .POST("/router/movies/save" , movieHandler::saveMovies)
                .build();
    }
}

package com.example.reactive_streams_demo.dao;

import com.example.reactive_streams_demo.entity.Movie;
import com.example.reactive_streams_demo.util.MovieUtils;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class MovieDao {

    private static final Long SLEEP_TIME_IN_MILLI_SEC = 1000L;
    private static final Integer NO_OF_MOVIES_TO_RETRIEVE = 10;

    @Autowired
    private final MovieUtils movieUtils;

    private static void setSleepTimer() {
        try {
            Thread.sleep(SLEEP_TIME_IN_MILLI_SEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMovies(){
       return IntStream.rangeClosed(1,NO_OF_MOVIES_TO_RETRIEVE)
               .peek(i -> {
                    setSleepTimer();
                    System.out.println("processing counter : " + i);
               })
               .mapToObj(i -> new Movie(i,"Movie Name : "+i, movieUtils.calculatePrice(i),movieUtils.calculateRatings(i)))
               .collect(Collectors.toList());
    }


    //non blocking async
    public Flux<Movie> getMoviesStream(){
        //Long startTime= System.currentTimeMillis();

        return  Flux.range(1,NO_OF_MOVIES_TO_RETRIEVE)
                .delayElements(Duration.ofMillis(SLEEP_TIME_IN_MILLI_SEC))
                .doOnNext(i -> System.out.println("processing counter in non-blocking : " + i))
                .map(i -> new Movie(i,"Movie Name : "+i, movieUtils.calculatePrice(i),movieUtils.calculateRatings(i)));
           /*     .doOnComplete(() ->{
                  Long endTime = System.currentTimeMillis();
                  System.out.println("Total Execution Time taken is: "+ (endTime - startTime) + " (sec)");
                });*/
    }
}

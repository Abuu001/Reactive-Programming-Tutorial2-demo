package com.example.reactive_streams_demo.util;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MovieUtils {

    private static final Integer MOVIE_PRICE = 2342;
    private static final Double MOVIE_RATING = 6.1;

    public Double calculateRatings(Integer counter){
        Double finalRatings = 0.0;
        finalRatings = counter % MOVIE_RATING;

        return finalRatings;
    }


    public BigDecimal calculatePrice(Integer counter){
        Integer finalPrice = 1;

        finalPrice = (MOVIE_PRICE * counter) - MOVIE_PRICE / 2;

        return  new BigDecimal(finalPrice);
    }

}

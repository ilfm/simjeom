package com.simjeom.simjeom.domain.reviews;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MyReviewResponse {

    private String res;
    private String[] keywords;
    private String visitDt;
}

package com.thomas.wishlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CourseRequest {
    private String name;
    private double completionPercentage;
    private Integer technologyId;
}

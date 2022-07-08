package com.thomas.wishlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseRequest {
    public Integer technologyId;
    public String name;
    public double completionPercentage;
}

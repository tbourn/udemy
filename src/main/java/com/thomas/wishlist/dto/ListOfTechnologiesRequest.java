package com.thomas.wishlist.dto;

import com.thomas.wishlist.entity.Technology;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ListOfTechnologiesRequest {
    private List<Technology> technologies;
}

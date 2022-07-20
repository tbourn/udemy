package com.thomas.wishlist.dto;

import com.thomas.wishlist.entity.Technology;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ListOfTechnologiesResponse {
    private List<Technology> technologies;
}

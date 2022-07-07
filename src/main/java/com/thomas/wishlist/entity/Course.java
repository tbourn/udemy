package com.thomas.wishlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "completion_percentage")
    double completionPercentage;

    //    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "technology_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "technology_id")
    @NotNull(message = "Technology has to be present")
    @ToString.Exclude
    @JsonIgnore
    private Technology technologyId;

}

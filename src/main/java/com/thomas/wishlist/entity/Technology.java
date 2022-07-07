package com.thomas.wishlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technology")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Integer technologyId;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "technologyId",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Course> courses;

    @Transient
    @JsonIgnore
    private double technologyAvg = 0.0;

    @Transient
    public double getTechnologyAvg() {
        for (Course course : this.courses) {
            this.technologyAvg += course.getCompletionPercentage();
        }

        return this.technologyAvg / this.courses.size();
    }

}

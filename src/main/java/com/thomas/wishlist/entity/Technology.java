package com.thomas.wishlist.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technology")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Integer technologyId;

    @NonNull
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "technologyId",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
//    @NonNull
    private List<Course> courses;

    @Transient
    private double technologyAvg = 0.0;

    public double getTechnologyAvg() {
        try {
            // I used this temp variable, otherwise the method is invoked twice!
            double temp = technologyAvg;
            for (Course course : courses) {
                temp += course.getCompletionPercentage();
            }

            return temp / courses.size();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return 0.0;
    }

}

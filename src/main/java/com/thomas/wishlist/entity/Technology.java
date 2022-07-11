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
    private List<Course> courses;

    @Transient
    private double technologyAvg = 0.0;

    /**
     * Copy constructor.
     *
     * @param technology of Technology type
     */
    public Technology(Technology technology) {
        this.technologyId = technology.getTechnologyId();
        this.name = technology.getName();
        this.courses = technology.getCourses();
        this.technologyAvg = technology.getTechnologyAvg();
    }

    /**
     * Returns the average of the courses for a technology. Default value is zero.
     *
     * @return double
     */
    public double getTechnologyAvg() {
        try {
            // I used this temp variable, otherwise the method is invoked twice!
            var temp = technologyAvg;
            for (Course course : courses) {
                temp += course.getCompletionPercentage();
            }

            return temp / courses.size();
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("There are not currently any courses assigned. AVG is set to zero.");
        }
        return 0.0;
    }
}

package com.thomas.wishlist.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Technology(Technology technology) {
        this.technologyId = technology.getTechnologyId();
        this.name = technology.getName();
        this.courses = technology.getCourses();
        this.technologyAvg = technology.getTechnologyAvg();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (Technology) o;
        return Double.compare(that.technologyAvg, technologyAvg) == 0 && Objects.equals(technologyId, that.technologyId)
                && name.equals(that.name) && Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technologyId, name, courses, technologyAvg);
    }
}

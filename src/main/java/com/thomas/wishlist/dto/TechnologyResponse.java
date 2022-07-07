//package com.thomas.wishlist.dto;
//
//import com.thomas.wishlist.entity.Course;
//
//import java.util.List;
//
//public class TechnologyResponse {
//
//    private Integer technologyId;
//
//    private String name;
//
//    private List<Course> courses;
//
//    private double avg = 0.0;
//
//    public TechnologyResponse() {
//    }
//
//    public TechnologyResponse(Integer technologyId, String name, List<Course> courses, double avg) {
//        this.technologyId = technologyId;
//        this.name = name;
//        this.courses = courses;
//        this.avg = avg;
//    }
//
//    public Integer getTechnologyId() {
//        return technologyId;
//    }
//
//    public void setTechnologyId(Integer technologyId) {
//        this.technologyId = technologyId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }
//
//    public double getAvg() {
//        return avg;
//    }
//
//    public void setAvg(double avg) {
//        this.avg = avg;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("TechnologyResponse{");
//        sb.append("technologyId=").append(technologyId);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", courses=").append(courses);
//        sb.append(", avg=").append(avg);
//        sb.append('}');
//        return sb.toString();
//    }
//}

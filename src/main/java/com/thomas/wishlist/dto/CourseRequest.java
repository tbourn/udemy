package com.thomas.wishlist.dto;

public class CourseRequest {
    public Integer technologyId;
    public String name;
    public double completionPercentage;

    public CourseRequest() {
    }

    public CourseRequest(String name, double completionPercentage) {
        this.name = name;
        this.completionPercentage = completionPercentage;
    }

    public Integer getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Integer technologyId) {
        this.technologyId = technologyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseRequestDTO{");
        sb.append("technologyId=").append(technologyId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", completionPercentage=").append(completionPercentage);
        sb.append('}');
        return sb.toString();
    }
}

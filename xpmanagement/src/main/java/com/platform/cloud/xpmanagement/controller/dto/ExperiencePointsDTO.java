package com.platform.cloud.xpmanagement.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body for adding experience points
 * 
 * @author richmondchng
 *
 */
public class ExperiencePointsDTO {

    @JsonProperty(value = "points")
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }
}

package com.platform.cloud.xpmanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Composite key.
 *
 * @author richmondchng
 *
 */
public class ExperienceId implements Serializable {
    private static final long serialVersionUID = -455266744650980908L;

    @Column(name = "experience_id")
    private int experienceId;

    @Column(name = "player_id")
    private int playerId;

    public ExperienceId() {
        super();
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(final int experienceId) {
        this.experienceId = experienceId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(final int playerId) {
        this.playerId = playerId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + experienceId;
        result = prime * result + playerId;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExperienceId)) {
            return false;
        }
        ExperienceId other = (ExperienceId) obj;
        if (experienceId != other.experienceId) {
            return false;
        }
        if (playerId != other.playerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExperienceId [experienceId=" + experienceId + ", playerId=" + playerId + "]";
    }

}

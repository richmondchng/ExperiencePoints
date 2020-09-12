package com.platform.cloud.xpmanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Experience log entity.
 *
 * @author richmondchng
 *
 */
@Entity(name = "experience_log")
public class ExperienceLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long experienceLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "experienceId", referencedColumnName = "experience_id"),
        @JoinColumn(name = "playerId", referencedColumnName = "player_id") })
    private ExperienceEntity experience;

    @Column(name = "amount")
    private int amount;

    @Column(name = "type")
    private ExperienceType type;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at_timestamp")
    private LocalDateTime createdAtTimestamp;

    public long getExperienceLogId() {
        return experienceLogId;
    }

    public void setExperienceLogId(final long experienceLogId) {
        this.experienceLogId = experienceLogId;
    }

    public ExperienceEntity getExperience() {
        return experience;
    }

    public void setExperience(final ExperienceEntity experience) {
        this.experience = experience;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public ExperienceType getType() {
        return type;
    }

    public void setType(final ExperienceType type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public void setCreatedAtTimestamp(final LocalDateTime createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
    }

}

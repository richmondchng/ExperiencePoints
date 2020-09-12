package com.platform.cloud.xpmanagement.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;

/**
 * Experience entity.
 *
 * @author richmondchng
 *
 */
@Entity(name = "experience")
@IdClass(ExperienceId.class)
public class ExperienceEntity {
    @Id
    @Column(name = "experience_id")
    @GeneratedValue
    private int experienceId;

    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "balance")
    private int balance;

    @Column(name = "created_at_timestamp")
    private LocalDateTime createdAtTimestamp;

    @Column(name = "updated_at_timestamp")
    private LocalDateTime updatedAtTimestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "experience", cascade = CascadeType.ALL)
    private Set<ExperienceLogEntity> experienceLogs = new HashSet<>();

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

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public void setCreatedAtTimestamp(final LocalDateTime createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
    }

    public LocalDateTime getUpdatedAtTimestamp() {
        return updatedAtTimestamp;
    }

    public void setUpdatedAtTimestamp(final LocalDateTime updatedAtTimestamp) {
        this.updatedAtTimestamp = updatedAtTimestamp;
    }

    public Set<ExperienceLogEntity> getExperienceLogs() {
        return experienceLogs;
    }

    public void setExperienceLogs(final Set<ExperienceLogEntity> experienceLogs) {
        this.experienceLogs = experienceLogs;
    }

}

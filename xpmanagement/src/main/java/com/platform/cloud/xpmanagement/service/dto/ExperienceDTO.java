package com.platform.cloud.xpmanagement.service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object describing player's experience balance.
 * 
 * @author richmondchng
 *
 */
public class ExperienceDTO {

    @JsonProperty(value = "balance")
    private int balance;

    @JsonProperty(value = "created_at_timestamp")
    private LocalDateTime createdAtTimestamp;

    @JsonProperty(value = "updated_at_timestamp")
    private LocalDateTime updatedAtTimestamp;

    public ExperienceDTO(final int balance, final LocalDateTime createdAtTimestamp,
            final LocalDateTime updatedAtTimestamp) {
        this.balance = balance;
        this.createdAtTimestamp = createdAtTimestamp;
        this.updatedAtTimestamp = updatedAtTimestamp;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public LocalDateTime getUpdatedAtTimestamp() {
        return updatedAtTimestamp;
    }

}

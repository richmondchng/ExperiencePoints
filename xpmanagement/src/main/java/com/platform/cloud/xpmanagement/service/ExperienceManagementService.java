package com.platform.cloud.xpmanagement.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;
import com.platform.cloud.xpmanagement.entity.ExperienceLogEntity;
import com.platform.cloud.xpmanagement.entity.ExperienceType;
import com.platform.cloud.xpmanagement.repository.ExperienceRepository;
import com.platform.cloud.xpmanagement.service.dto.ExperienceDTO;

/**
 * Service to manage experience points.
 *
 * @author richmondchng
 *
 */
@Service
public class ExperienceManagementService {

    private final ExperienceRepository experienceRepository;

    public ExperienceManagementService(final ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    /**
     * Get player balance.
     *
     * @param playerId player id
     * @return ExperienceDTO
     */
    public ExperienceDTO getPlayerBalance(final int playerId) {
        final ExperienceEntity record = getPlayerRecord(playerId);
        if (record.getExperienceId() <= 0) {
            // save if new record
            experienceRepository.save(record);
        }
        return new ExperienceDTO(record.getBalance(), record.getCreatedAtTimestamp(), record.getUpdatedAtTimestamp());
    }

    /**
     * Add points to player.
     *
     * @param playerId player Id
     * @param points   points to add
     */
    public void addExperiencePoints(final int playerId, final int points) {
        // get record
        final ExperienceEntity record = getPlayerRecord(playerId);
        // get time stamp
        final LocalDateTime timestamp = LocalDateTime.now();

        final ExperienceLogEntity log = new ExperienceLogEntity();
        log.setExperience(record);
        log.setAmount(points);
        log.setCreatedAtTimestamp(timestamp);
        if (points >= 0) {
            // earn
            log.setType(ExperienceType.EARN);
        } else {
            log.setType(ExperienceType.PENALTY);
        }
        record.setBalance(record.getBalance() + points);
        // lazy-loading, getter will load records
        record.getExperienceLogs().add(log);
        record.setUpdatedAtTimestamp(timestamp);
        // save record
        experienceRepository.save(record);
    }

    /**
     * Get player experience record.
     *
     * @param playerId player Id
     * @return ExperienceEntity
     */
    private ExperienceEntity getPlayerRecord(final int playerId) {
        ExperienceEntity record = experienceRepository.findByPlayerId(playerId);
        if (record == null) {
            // does not exists
            record = new ExperienceEntity();
            record.setPlayerId(playerId);
            // assumption = new player starts from 0
            record.setBalance(0);
            final LocalDateTime timestamp = LocalDateTime.now();
            record.setCreatedAtTimestamp(timestamp);
            record.setUpdatedAtTimestamp(timestamp);
        }
        return record;
    }
}

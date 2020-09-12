package com.platform.cloud.xpmanagement.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;
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

        ExperienceEntity experience = experienceRepository.findByPlayerId(playerId);
        if (experience == null) {
            // does not exists
            experience = new ExperienceEntity();
            experience.setPlayerId(playerId);
            // assumption = new player starts from 0
            experience.setBalance(0);
            final LocalDateTime timestamp = LocalDateTime.now();
            experience.setCreatedAtTimestamp(timestamp);
            experience.setUpdatedAtTimestamp(timestamp);
            // persist
            experienceRepository.save(experience);
        }
        return new ExperienceDTO(experience.getBalance(), experience.getCreatedAtTimestamp(),
                experience.getUpdatedAtTimestamp());
    }
}

package com.platform.cloud.xpmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;
import com.platform.cloud.xpmanagement.entity.ExperienceId;

/**
 * Respository for managing ExperienceEntity beans.
 * 
 * @author richmondchng
 *
 */
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, ExperienceId> {

    /**
     * Find experience by player Id.
     * 
     * @param playerId int player Id
     * @return ExperienceEntity or null if not matched
     */
    ExperienceEntity findByPlayerId(final int playerId);
}

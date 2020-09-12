package com.platform.cloud.xpmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;
import com.platform.cloud.xpmanagement.entity.ExperienceLogEntity;
import com.platform.cloud.xpmanagement.entity.ExperienceType;

/**
 * Test ExperienceRepository.
 *
 * @author richmondchng
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExperienceRepositoryTest {

    @Autowired
    private ExperienceRepository experienceRepository;

    /**
     * Test findByPlayerId. Has record matching playerId.
     *
     * @throws Exception
     */
    @Test
    public void testFindByPlayerId_hasRecord() throws Exception {
        final ExperienceEntity e1 = new ExperienceEntity();
        e1.setPlayerId(8);
        e1.setBalance(23);
        e1.setCreatedAtTimestamp(LocalDateTime.now());
        e1.setUpdatedAtTimestamp(LocalDateTime.now());
        experienceRepository.save(e1);

        final ExperienceEntity result = experienceRepository.findByPlayerId(8);
        assertNotNull(result);
        assertTrue(result.getExperienceId() > 0);
        assertEquals(8, result.getPlayerId());
        assertEquals(23, result.getBalance());
        assertNotNull(result.getCreatedAtTimestamp());
        assertNotNull(result.getUpdatedAtTimestamp());
    }

    /**
     * Test findByPlayerId. Has no record matching playerId.
     *
     * @throws Exception
     */
    @Test
    public void testFindByPlayerId_noRecord() throws Exception {
        final ExperienceEntity result = experienceRepository.findByPlayerId(8);
        assertNull(result);
    }

    /**
     * Test save will cascade save for child entities.
     * 
     * @throws Exception
     */
    @Test
    public void testSave_cascadeSaveExperienceLogs() throws Exception {
        final ExperienceEntity e1 = new ExperienceEntity();
        e1.setPlayerId(9);
        e1.setBalance(23);
        e1.setCreatedAtTimestamp(LocalDateTime.now());
        e1.setUpdatedAtTimestamp(LocalDateTime.now());

        final ExperienceLogEntity l1 = new ExperienceLogEntity();
        l1.setExperience(e1);
        l1.setAmount(3);
        l1.setCreatedAtTimestamp(LocalDateTime.now());
        l1.setType(ExperienceType.EARN);
        // lazy-loading, getter will load records
        e1.getExperienceLogs().add(l1);

        experienceRepository.save(e1);

        final ExperienceEntity result = experienceRepository.findByPlayerId(9);
        assertNotNull(result);

        final Set<ExperienceLogEntity> logs = result.getExperienceLogs();
        assertNotNull(logs);
        assertEquals(1, logs.size());

        final ExperienceLogEntity log = logs.iterator().next();
        assertEquals(ExperienceType.EARN, log.getType());
        assertEquals(3, log.getAmount());
        assertNotNull(log.getExperience());
        assertNotNull(log.getCreatedAtTimestamp());
    }
}

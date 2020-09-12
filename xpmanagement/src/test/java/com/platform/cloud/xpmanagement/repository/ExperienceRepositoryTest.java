package com.platform.cloud.xpmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;

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
        e1.setExperienceId(21);
        e1.setPlayerId(8);
        e1.setBalance(23);
        e1.setCreatedAtTimestamp(LocalDateTime.now());
        e1.setUpdatedAtTimestamp(LocalDateTime.now());
        experienceRepository.save(e1);

        final ExperienceEntity result = experienceRepository.findByPlayerId(8);
        assertNotNull(result);
        assertEquals(21, result.getExperienceId());
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
}

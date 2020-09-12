package com.platform.cloud.xpmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.platform.cloud.xpmanagement.entity.ExperienceEntity;
import com.platform.cloud.xpmanagement.repository.ExperienceRepository;
import com.platform.cloud.xpmanagement.service.dto.ExperienceDTO;

/**
 * Test ExperienceManagementService.
 *
 * @author richmondchng
 *
 */
@ExtendWith(MockitoExtension.class)
public class ExperienceManagementServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    // test instance
    private ExperienceManagementService experienceManagementService;

    @BeforeEach
    public void setUp() throws Exception {
        experienceManagementService = new ExperienceManagementService(experienceRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        experienceManagementService = null;
    }

    /**
     * Test getPlayerBalance. Test experience exists.
     *
     * @throws Exception
     */
    @Test
    public void testGetPlayerBalance_hasExistingRecord() throws Exception {
        final ExperienceEntity record = new ExperienceEntity();
        record.setExperienceId(1);
        record.setPlayerId(23);
        record.setBalance(101);
        record.setCreatedAtTimestamp(LocalDateTime.of(2020, 9, 12, 15, 30, 30));
        record.setUpdatedAtTimestamp(LocalDateTime.of(2020, 9, 12, 16, 30, 30));
        when(experienceRepository.findByPlayerId(anyInt())).thenReturn(record);

        final ExperienceDTO result = experienceManagementService.getPlayerBalance(23);

        // check repository is used to get record
        verify(experienceRepository, times(1)).findByPlayerId(23);
        // check repository is not used to save record
        verify(experienceRepository, times(0)).save(any(ExperienceEntity.class));

        // check result
        assertNotNull(result);
        assertEquals(101, result.getBalance());
        assertEquals(LocalDateTime.of(2020, 9, 12, 15, 30, 30), result.getCreatedAtTimestamp());
        assertEquals(LocalDateTime.of(2020, 9, 12, 16, 30, 30), result.getUpdatedAtTimestamp());
    }

    /**
     * Test getPlayerBalance. Test experience does not exists.
     *
     * @throws Exception
     */
    @Test
    public void testGetPlayerBalance_hasNoExistingRecord() throws Exception {
        when(experienceRepository.findByPlayerId(anyInt())).thenReturn(null);

        final ExperienceDTO result = experienceManagementService.getPlayerBalance(23);

        // check repository is used to get record
        verify(experienceRepository, times(1)).findByPlayerId(23);
        // check repository is used to save record
        verify(experienceRepository, times(1)).save(any(ExperienceEntity.class));

        // check result
        assertNotNull(result);
        assertEquals(0, result.getBalance());
        assertNotNull(result.getCreatedAtTimestamp());
        assertNotNull(result.getUpdatedAtTimestamp());
    }
}

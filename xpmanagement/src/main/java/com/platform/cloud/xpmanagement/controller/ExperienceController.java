package com.platform.cloud.xpmanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cloud.xpmanagement.service.ExperienceManagementService;
import com.platform.cloud.xpmanagement.service.dto.ExperienceDTO;

/**
 * Rest controller.
 *
 * @author richmondchng
 *
 */
@RestController
@RequestMapping(value = "/experience")
public class ExperienceController {

    private final ExperienceManagementService experienceManagementService;

    public ExperienceController(final ExperienceManagementService experienceManagementService) {
        this.experienceManagementService = experienceManagementService;
    }

    /**
     * Get player balance
     *
     * @param playerId player Id
     * @return {@link ExperienceDTO}
     */
    @GetMapping(value = "/{playerId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ExperienceDTO getPlayerBalance(@PathVariable(required = true, name = "playerId") final int playerId) {
        return experienceManagementService.getPlayerBalance(playerId);
    }
}

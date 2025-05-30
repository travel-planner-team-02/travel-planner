package com.laioffer.travelplannerbe.service;

import com.laioffer.travelplannerbe.model.SiteDTO;
import com.laioffer.travelplannerbe.model.SiteEntity;
import com.laioffer.travelplannerbe.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<SiteDTO> getSitesByCityId(Long cityId) {
        List<SiteEntity> sites = siteRepository.findByCityId(cityId);
        return sites.stream()
                .map(SiteDTO::fromSiteEntity)
                .collect(Collectors.toList());
    }
}
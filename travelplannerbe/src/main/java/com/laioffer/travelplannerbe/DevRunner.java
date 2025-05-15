package com.laioffer.travelplannerbe;

import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.repository.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class DevRunner implements ApplicationRunner {

    static private final Logger logger = LoggerFactory.getLogger(DevRunner.class);

    private final CityRepository cityRepository;
    private final SiteRepository siteRepository;
    private final TripRepository tripRepository;
    private final TripSiteRepository tripSiteRepository;
    private final UserRepository userRepository;

    public DevRunner(
            CityRepository cityRepository,
            SiteRepository siteRepository,
            TripRepository tripRepository,
            TripSiteRepository tripSiteRepository,
            UserRepository userRepository
    ) {
        this.cityRepository = cityRepository;
        this.siteRepository = siteRepository;
        this.tripRepository = tripRepository;
        this.tripSiteRepository = tripSiteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        generateSampleData();

    }

    private void generateSampleData() {
        // for test use
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("1234");
        UserEntity user = userRepository.save(new UserEntity(null, "marcus", encodedPassword));

        GeometryFactory geometryFactory = new GeometryFactory();

        cityRepository.saveAll(List.of(
                new CityEntity(
                        null,
                        "San_Francisco",
                        geometryFactory.createPoint(new Coordinate(-122.4194, 37.7749))
                ),
                new CityEntity(
                        null,
                        "Los_Angeles",
                        geometryFactory.createPoint(new Coordinate(-118.2437, 34.0522))
                ),
                new CityEntity(
                        null,
                        "San_Jose",
                        geometryFactory.createPoint(new Coordinate(-121.8863, 37.338))
                ),
                new CityEntity(
                        null,
                        "New_York",
                        geometryFactory.createPoint(new Coordinate(-74.0060, 40.7128))
                )
        ));

        LocalDate today = LocalDate.now();
        tripRepository.saveAll(List.of(
                new TripEntity(null, 1L, today.minusDays(10), today.minusDays(5), 1L),
                new TripEntity(null, 1L, today.minusDays(30), today.minusDays(25), 2L),
                new TripEntity(null, 1L, today.plusDays(5), today.plusDays(10), 3L),
                new TripEntity(null, 1L, today.plusDays(25), today.plusDays(35), 4L)
        ));
    }
}

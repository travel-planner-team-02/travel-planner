package com.laioffer.travelplannerbe;

import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.repository.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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

        UserEntity user = userRepository.findByUsername("marcus");
        logger.info("marcus UserId" + user.toString());

        boolean exists = userRepository.existsByUsername("eddy");
        logger.info("User eddy exists? " + exists);

        List<TripEntity> marcusTrips = tripRepository.findByUserId(1L);
        logger.info("marcus trips" + marcusTrips.toString());

        CityEntity city = cityRepository.findById(1L).get();
        logger.info("city " + city.toString());

        List<Long> siteIds = tripSiteRepository.findSiteIdsByTripId(1L);
        logger.info("siteIds " + siteIds.toString());

        List<SiteEntity> sites = siteRepository.findByCityId(1L);
        logger.info("sites " + sites.toString());



    }

    private void generateSampleData() {
        userRepository.saveAll(List.of(
                new UserEntity(null, "marcus", "YT61cW"),//1L
                new UserEntity(null, "joe", "sa4NiK"),//2L
                new UserEntity(null, "tom", "akKKN6"),//3L
                new UserEntity(null, "jerry", "HDiaen"),//4L
                new UserEntity(null, "alice", "GYEnwk"),//5L
                new UserEntity(null, "bob", "UjaUW"),//6L
                new UserEntity(null, "charlie", "OApSI"),//7L
                new UserEntity(null, "dave", "QA&@Ja")//8L
        ));

        GeometryFactory geometryFactory = new GeometryFactory();

        cityRepository.saveAll(List.of(
                new CityEntity(
                        null,
                        "San_Francisco",
                        geometryFactory.createPoint(new Coordinate(37.7749, -122.4194))
                ),
                new CityEntity(
                        null,
                        "Los_Angeles",
                        geometryFactory.createPoint(new Coordinate(77.7749, 12.4134))
                ),
                new CityEntity(
                        null,
                        "San_Jose",
                        geometryFactory.createPoint(new Coordinate(82.6387, 91.6172))
                ),
                new CityEntity(
                        null,
                        "New_York",
                        geometryFactory.createPoint(new Coordinate(17.2718, 78.1625))
                )
        ));

        siteRepository.saveAll(List.of(
                new SiteEntity(null, "Golden Gate Bridge", "Iconic red suspension bridge.",
                        "Golden Gate Bridge, SF", new CityEntity(1L, null, null),
                        4.8, List.of("https://img1", "https://img2"), 30),
                new SiteEntity(null, "Alcatraz Island", "Historic former prison island.",
                        "San Francisco Bay", new CityEntity(1L, null, null),
                        4.6, List.of("https://example.com/alcatraz1.jpg"), 120),

                new SiteEntity(null, "Griffith Observatory", "Great views of LA and the stars.",
                        "Los Angeles", new CityEntity(2L, null, null),
                        4.7, List.of("https://example.com/griffith1.jpg"), 60),

                new SiteEntity(null, "Hollywood Sign", "Famous landmark in LA hills.",
                        "Mount Lee, Los Angeles", new CityEntity(2L, null, null),
                        4.5, List.of("https://example.com/hollywood1.jpg"), 30),

                new SiteEntity(null, "Winchester Mystery House", "Sprawling, maze-like Victorian mansion.",
                        "San Jose", new CityEntity(3L, null, null),
                        4.2, List.of("https://example.com/winchester1.jpg"), 90),

                new SiteEntity(null, "The Tech Interactive", "Tech museum for all ages.",
                        "Downtown San Jose", new CityEntity(3L, null, null),
                        4.4, List.of("https://example.com/tech1.jpg"), 60),

                new SiteEntity(null, "Central Park", "Massive urban park in NYC.",
                        "Manhattan, New York", new CityEntity(4L, null, null),
                        4.9, List.of("https://example.com/centralpark1.jpg", "https://example.com/centralpark2.jpg"), 90),

                new SiteEntity(null, "Statue of Liberty", "Iconic symbol of freedom.",
                        "Liberty Island, NYC", new CityEntity(4L, null, null),
                        4.8, List.of("https://example.com/liberty1.jpg"), 60),

                new SiteEntity(null, "Times Square", "Bright lights and entertainment hub.",
                        "Manhattan, NYC", new CityEntity(4L, null, null),
                        4.3, List.of("https://example.com/times1.jpg"), 30),

                new SiteEntity(null, "Exploratorium", "Interactive science museum.",
                        "Pier 15, San Francisco", new CityEntity(1L, null, null),
                        4.6, List.of("https://example.com/exploratorium1.jpg"), 90)
        ));

        LocalDate today = LocalDate.now();
        tripRepository.saveAll(List.of(
                new TripEntity(null, 1L, today.minusDays(10), today.minusDays(5), 1L),
                new TripEntity(null, 1L, today.minusDays(30), today.minusDays(25), 2L),
                new TripEntity(null, 2L, today.minusDays(20), today.minusDays(15), 1L),
                new TripEntity(null, 3L, today.minusDays(50), today.minusDays(45), 3L),
                new TripEntity(null, 4L, today.minusDays(60), today.minusDays(55), 4L),
                new TripEntity(null, 5L, today.minusDays(70), today.minusDays(65), 2L),
                new TripEntity(null, 6L, today.minusDays(90), today.minusDays(85), 3L),
                new TripEntity(null, 7L, today.minusDays(15), today.minusDays(10), 1L),
                new TripEntity(null, 8L, today.minusDays(120), today.minusDays(115), 4L),
                new TripEntity(null, 1L, today.minusDays(5), today.minusDays(2), 2L)

        ));

        tripSiteRepository.saveAll(List.of(
                new TripSiteEntity(
                        new TripSiteKey(1L, 1L),
                        new TripEntity(1L, null, null, null, null),
                        new SiteEntity(1L, null, null, null, null, null, null, null),
                        today.minusDays(10)),
                new TripSiteEntity(
                        new TripSiteKey(1L, 2L),
                        new TripEntity(1L, null, null, null, null),
                        new SiteEntity(2L, null, null, null, null, null, null, null),
                        today.minusDays(9)),
                new TripSiteEntity(
                        new TripSiteKey(2L, 3L),
                        new TripEntity(2L, null, null, null, null),
                        new SiteEntity(3L, null, null, null, null, null, null, null),
                        today.minusDays(29)),
                new TripSiteEntity(
                        new TripSiteKey(3L, 1L),
                        new TripEntity(3L, null, null, null, null),
                        new SiteEntity(1L, null, null, null, null, null, null, null),
                        today.minusDays(19)),
                new TripSiteEntity(
                        new TripSiteKey(3L, 4L),
                        new TripEntity(3L, null, null, null, null),
                        new SiteEntity(4L, null, null, null, null, null, null, null),
                        today.minusDays(18)),
                new TripSiteEntity(
                        new TripSiteKey(4L, 5L),
                        new TripEntity(4L, null, null, null, null),
                        new SiteEntity(5L, null, null, null, null, null, null, null),
                        today.minusDays(49)),
                new TripSiteEntity(
                        new TripSiteKey(5L, 6L),
                        new TripEntity(5L, null, null, null, null),
                        new SiteEntity(6L, null, null, null, null, null, null, null),
                        today.minusDays(59)),
                new TripSiteEntity(
                        new TripSiteKey(6L, 7L),
                        new TripEntity(6L, null, null, null, null),
                        new SiteEntity(7L, null, null, null, null, null, null, null),
                        today.minusDays(89)),
                new TripSiteEntity(
                        new TripSiteKey(7L, 8L),
                        new TripEntity(7L, null, null, null, null),
                        new SiteEntity(8L, null, null, null, null, null, null, null),
                        today.minusDays(14)),
                new TripSiteEntity(
                        new TripSiteKey(8L, 9L),
                        new TripEntity(8L, null, null, null, null),
                        new SiteEntity(9L, null, null, null, null, null, null, null),
                        today.minusDays(119))
        ));
    }
}

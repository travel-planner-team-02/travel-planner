package com.laioffer.travelplannerbe;

import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.repository.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class DevRunner implements ApplicationRunner {

    //static private final Logger logger = LoggerFactory.getLogger(DevRunner.class);

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
                        "San Francisco",
                        geometryFactory.createPoint(new Coordinate(-122.4194, 37.7749))
                ),
                new CityEntity(
                        null,
                        "Los Angeles",
                        geometryFactory.createPoint(new Coordinate(-118.2437, 34.0522))
                ),
                new CityEntity(
                        null,
                        "San Jose",
                        geometryFactory.createPoint(new Coordinate(-121.8863, 37.338))
                ),
                new CityEntity(
                        null,
                        "New York",
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

        siteRepository.saveAll(List.of(
                new SiteEntity(null, "Golden Gate Bridge", "Iconic red suspension bridge.",
                        "Golden Gate Bridge, San Francisco, CA 94129", new CityEntity(1L, null, null),
                        4.8, List.of("https://images.squarespace-cdn.com/content/v1/56e8fcc03c44d89db7df9b3e/1554359150041-FS043JZG6F79BEDSZIY2/11+Picture-Perfect+Views+of+the+Golden+Gate+Bridge+in+San+Francisco?format=2500w"),
                        30,
                        geometryFactory.createPoint(new Coordinate(-122.4783, 37.8199))
                ),

                new SiteEntity(null, "Alcatraz Island", "Historic former prison island.",
                        "Alcatraz Island, San Francisco, CA 94133", new CityEntity(1L, null, null),
                        4.6, List.of("https://www.usatoday.com/gcdn/-mm-/1abbac059a7e6f21ff3aa7e38760a41a48819119/c=0-217-2118-1414/local/-/media/2018/08/17/USATODAY/USATODAY/636701422647615718-GettyImages-510871741.jpg?width=660&height=374&fit=crop&format=pjpg&auto=webp"),
                        120,
                        geometryFactory.createPoint(new Coordinate(-122.4231, 37.8267))
                ),

                new SiteEntity(null, "Exploratorium", "Interactive science museum.",
                        "Pier 15, The Embarcadero, San Francisco, CA 94111", new CityEntity(1L, null, null),
                        4.6, List.of("https://drupal-prod.visitcalifornia.com/sites/default/files/Museum%20Galleries%20%7C%20Exploratorium.jpg"),
                        90,
                        geometryFactory.createPoint(new Coordinate(-122.3986, 37.8014))
                ),

                new SiteEntity(null, "Griffith Observatory", "Great views of LA and the stars.",
                        "2800 E Observatory Rd, Los Angeles, CA 90027", new CityEntity(2L, null, null),
                        4.7, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Griffith_observatory_2006.jpg/250px-Griffith_observatory_2006.jpg"),
                        60,
                        geometryFactory.createPoint(new Coordinate(-118.3004, 34.1184))
                ),

                new SiteEntity(null, "Hollywood Sign", "Famous landmark in LA hills.",
                        "Los Angeles, CA 90068 (Mount Lee)", new CityEntity(2L, null, null),
                        4.5, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Hollywood_Sign_%28Zuschnitt%29.jpg/250px-Hollywood_Sign_%28Zuschnitt%29.jpg"),
                        30,
                        geometryFactory.createPoint(new Coordinate(-118.3215, 34.1341))
                ),

                new SiteEntity(null, "Winchester Mystery House", "Sprawling, maze-like Victorian mansion.",
                        "525 S Winchester Blvd, San Jose, CA 95128", new CityEntity(3L, null, null),
                        4.2, List.of("https://upload.wikimedia.org/wikipedia/commons/e/ec/Winchester_Mystery_House_2023-07-17_02.jpg"),
                        90,
                        geometryFactory.createPoint(new Coordinate(-121.9505, 37.3185))
                ),

                new SiteEntity(null, "The Tech Interactive", "Tech museum for all ages.",
                        "201 S Market St, San Jose, CA 95113", new CityEntity(3L, null, null),
                        4.4, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/The_Tech_Museum_of_Innovation_in_Downtown_San_Jose_%28cropped%29.jpg/330px-The_Tech_Museum_of_Innovation_in_Downtown_San_Jose_%28cropped%29.jpg"),
                        60,
                        geometryFactory.createPoint(new Coordinate(-121.8870, 37.3318))
                ),

                new SiteEntity(null, "Central Park", "Massive urban park in NYC.",
                        "New York, NY 10024", new CityEntity(4L, null, null),
                        4.9, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Global_Citizen_Festival_Central_Park_New_York_City_from_NYonAir_%2815351915006%29.jpg/330px-Global_Citizen_Festival_Central_Park_New_York_City_from_NYonAir_%2815351915006%29.jpg"),
                        90,
                        geometryFactory.createPoint(new Coordinate(-73.9654, 40.7829))
                ),

                new SiteEntity(null, "Statue of Liberty", "Iconic symbol of freedom.",
                        "Liberty Island, New York, NY 10004", new CityEntity(4L, null, null),
                        4.8, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Front_view_of_Statue_of_Liberty_with_pedestal_and_base_2024.jpg/330px-Front_view_of_Statue_of_Liberty_with_pedestal_and_base_2024.jpg"),
                        60,
                        geometryFactory.createPoint(new Coordinate(-74.0445, 40.6892))
                ),

                new SiteEntity(null, "Times Square", "Bright lights and entertainment hub.",
                        "Manhattan, NY 10036", new CityEntity(4L, null, null),
                        4.3, List.of("https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/New_york_times_square-terabass.jpg/330px-New_york_times_square-terabass.jpg"),
                        30,
                        geometryFactory.createPoint(new Coordinate(-73.9851, 40.7580))
                )
        ));

        tripSiteRepository.saveAll(List.of(
                new TripSiteEntity(
                        new TripSiteKey(1L, 1L),
                        new TripEntity(1L, null, null, null, null),
                        new SiteEntity(1L, null, null, null, null, null, null, null,null),
                        today.minusDays(10)),
                new TripSiteEntity(
                        new TripSiteKey(1L, 2L),
                        new TripEntity(1L, null, null, null, null),
                        new SiteEntity(2L, null, null, null, null, null, null, null, null),
                        today.minusDays(9)),
                new TripSiteEntity(
                        new TripSiteKey(1L, 3L),
                        new TripEntity(1L, null, null, null, null),
                        new SiteEntity(3L, null, null, null, null, null, null, null, null),
                        today.minusDays(9)),
                new TripSiteEntity(
                        new TripSiteKey(2L, 4L),
                        new TripEntity(2L, null, null, null, null),
                        new SiteEntity(4L, null, null, null, null, null, null, null, null),
                        today.minusDays(29)),
                new TripSiteEntity(
                        new TripSiteKey(2L, 5L),
                        new TripEntity(2L, null, null, null, null),
                        new SiteEntity(5L, null, null, null, null, null, null, null, null),
                        today.minusDays(29)),
                new TripSiteEntity(
                        new TripSiteKey(3L, 6L),
                        new TripEntity(3L, null, null, null, null),
                        new SiteEntity(6L, null, null, null, null, null, null, null, null),
                        today.minusDays(19)),
                new TripSiteEntity(
                        new TripSiteKey(3L, 7L),
                        new TripEntity(3L, null, null, null, null),
                        new SiteEntity(7L, null, null, null, null, null, null, null, null),
                        today.minusDays(18)),
                new TripSiteEntity(
                        new TripSiteKey(4L, 8L),
                        new TripEntity(4L, null, null, null, null),
                        new SiteEntity(8L, null, null, null, null, null, null, null, null),
                        today.minusDays(49)),
                new TripSiteEntity(
                        new TripSiteKey(4L, 9L),
                        new TripEntity(4L, null, null, null, null),
                        new SiteEntity(9L, null, null, null, null, null, null, null, null),
                        today.minusDays(59)),
                new TripSiteEntity(
                        new TripSiteKey(4L, 10L),
                        new TripEntity(4L, null, null, null, null),
                        new SiteEntity(10L, null, null, null, null, null, null, null, null),
                        today.minusDays(59))

        ));
    }
}

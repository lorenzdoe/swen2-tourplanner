
package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.utils.Difficulty;
import at.fhtw.swen2.tutorial.presentation.utils.TransportType;
import at.fhtw.swen2.tutorial.service.MapService;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import at.fhtw.swen2.tutorial.service.dto.TourLog;
import at.fhtw.swen2.tutorial.service.utils.MapData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourLogService tourLogService;

    @Autowired
    private MapService mapService;

    @Value("${fill.db}")
    private Boolean fillDb;

    @Override
    public void afterPropertiesSet() {

        if(!fillDb){
            return;
        }

        // add some tours
        MapData mapData = mapService.getMap("Wien", "Rotterdam", "fastest");
        Tour one = tourService.addNew(Tour.builder()
                .name("Roadtrip")
                .from("Wien")
                .to("Rotterdam")
                .description("Roadtrip from Vienna to Rotterdam")
                .transportType(TransportType.FASTEST.toString())
                .distance(mapData != null ? mapData.getDistance() : 0)
                .estimatedTime(mapData != null ? mapData.getDuration() : 0)
                .imagePath(mapData != null ? mapData.getImagePath() : null)
                .build());

        MapData mapData2 = mapService.getMap("Wien", "Graz", "fastest");
        Tour two = tourService.addNew(Tour.builder()
                .name("Achterbahn")
                .from("Wien")
                .to("Graz")
                .description("Achterbahn von Wien nach Graz")
                .transportType(TransportType.FASTEST.toString())
                .distance(mapData2 != null ? mapData2.getDistance() : 0)
                .estimatedTime(mapData2 != null ? mapData2.getDuration() : 0)
                .imagePath(mapData2 != null ? mapData2.getImagePath() : null)
                .build());

        MapData mapData3 = mapService.getMap("Wien", "Innsbruck", "pedestrian");
        Tour three = tourService.addNew(Tour.builder()
                .name("Hatscher")
                .from("Wien")
                .to("Innsbruck")
                .description("Hatscher von Wien nach Innsbruck")
                .transportType(TransportType.PEDESTRIAN.toString())
                .distance(mapData3 != null ? mapData3.getDistance() : 0)
                .estimatedTime(mapData3 != null ? mapData3.getDuration() : 0)
                .imagePath(mapData3 != null ? mapData3.getImagePath() : null)
                .build());


        // add some tour logs
        tourLogService.addNew(TourLog.builder().dateTime(new Date()).comment("super").rating(4).difficulty(Difficulty.EASY).timeInMinutes(10).TourId(one.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.APRIL,19)).comment("faad").rating(5).difficulty(Difficulty.MEDIUM).timeInMinutes(120).TourId(one.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.APRIL,10)).comment("lustig").rating(2).difficulty(Difficulty.EASY).timeInMinutes(10).TourId(one.getId()).build());

        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.MARCH,1)).comment("schöner Ausblick").rating(4).difficulty(Difficulty.EASY).timeInMinutes(23).TourId(two.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.FEBRUARY,16)).comment("gruselig").rating(10).difficulty(Difficulty.HARD).timeInMinutes(10).TourId(two.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.JANUARY,3)).comment("lange").rating(8).difficulty(Difficulty.HARD).timeInMinutes(23).TourId(two.getId()).build());

        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.APRIL,1)).comment("mega").rating(11).difficulty(Difficulty.HARD).timeInMinutes(35).TourId(three.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.MARCH,20)).comment("entspannt").rating(9).difficulty(Difficulty.MEDIUM).timeInMinutes(45).TourId(three.getId()).build());
        tourLogService.addNew(TourLog.builder().dateTime(new Date(2023-1900, Calendar.MARCH,11)).comment("grausig").rating(12).difficulty(Difficulty.MEDIUM).timeInMinutes(60).TourId(three.getId()).build());

    }
}
package michalwds.mappers;

import michalwds.commons.Mapper;
import michalwds.models.Planet;
import michalwds.models.dtos.PlanetDTO;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper implements Mapper<Planet, PlanetDTO> {

    @Override
    public PlanetDTO map(Planet from) {
        return PlanetDTO
                .builder()
                .planetName(from.getPlanetName())
                .distanceFromSun(from.getDistanceFromSun())
                .oneWayLightTimeToTheSun(from.getOneWayLightTimeToTheSun())
                .lengthOfYears(from.getLengthOfYears())
                .planetType(from.getPlanetType())
                .planetInfo(from.getPlanetInfo())
                .planetImage(from.getPlanetImage())
                .build();
    }

    @Override
    public Planet reverseMap(PlanetDTO to) {
        return Planet
                .builder()
                .planetName(to.getPlanetName())
                .distanceFromSun(to.getDistanceFromSun())
                .oneWayLightTimeToTheSun(to.getOneWayLightTimeToTheSun())
                .lengthOfYears(to.getLengthOfYears())
                .planetType(to.getPlanetType())
                .planetInfo(to.getPlanetInfo())
                .planetImage(to.getPlanetImage())
                .build();
    }
}

package michalwds.services;

import michalwds.mappers.PlanetMapper;
import michalwds.models.Planet;
import michalwds.models.dtos.PlanetDTO;
import michalwds.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    private PlanetMapper planetMapper;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    /*
     * DAO //dane z bazy danych
     */
    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    /*
     *   DTO data transfer Object
     */

    public List<PlanetDTO> getPlanetsDTO(){
        return planetRepository
                .findAll()    //finding all planets but not DTO
                .stream()       //making a stream to find planetsDTO
                .map(planetMapper::map)  // method map from PlanetMapper (mapping) to get List od planetDTO
                .collect(Collectors.toList());
    }

    public Planet addPlanet (PlanetDTO planetDTO){
        return planetRepository.save(planetMapper.reverseMap(planetDTO));
    }

    public void updatePlanet(PlanetDTO planetDTO){
        planetRepository.findPlanetByPlanetName(planetDTO.getPlanetName())//if planet is null, then move forward
               .ifPresent(p -> {  //if planet exist, then my planet sets some options, like name, type... after that, I save it)
                   p.setDistanceFromSun(planetDTO.getDistanceFromSun());
                   p.setOneWayLightTimeToTheSun(planetDTO.getOneWayLightTimeToTheSun());
                   p.setLengthOfYears(planetDTO.getLengthOfYears());
                   p.setPlanetType(planetDTO.getPlanetType());
                   p.setPlanetInfo(planetDTO.getPlanetInfo());
                   p.setPlanetImage(planetDTO.getPlanetImage());

                   planetRepository.save(p);
               });



    }






}

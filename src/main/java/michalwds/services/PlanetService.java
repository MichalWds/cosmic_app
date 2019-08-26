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


}

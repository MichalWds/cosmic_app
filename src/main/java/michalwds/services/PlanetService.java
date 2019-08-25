package michalwds.services;

import michalwds.models.Planet;
import michalwds.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {
    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
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

}

package michalwds.controllers;

import michalwds.models.Planet;
import michalwds.models.dtos.PlanetDTO;
import michalwds.services.PlanetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets(){
        return planetService.getPlanets();
    }

    /*
    *
    *   DTO
    *
     */

    @GetMapping("/dto/planets")
    public List<PlanetDTO> getPlanetsDTO(){
        return planetService.getPlanetsDTO();
    }
}

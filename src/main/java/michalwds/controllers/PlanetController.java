package michalwds.controllers;

import michalwds.models.Planet;
import michalwds.models.dtos.PlanetDTO;
import michalwds.services.PlanetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets() {
        return planetService.getPlanets();
    }

    /*
     *
     *   DTO
     *
     */

//    @GetMapping("/dto/planets")
//    public List<PlanetDTO> getPlanetsDTO() {
//        return planetService.getPlanetsDTO();
//    }

    @PostMapping("/dto/planets")  //delete,create,update should be in same endpoint full CRUD
    public Planet addPlanet(@RequestBody PlanetDTO planetDTO) { //request body, mapuje po kluczach za pomca metody hhtp (wbudowany object mapper
        return planetService.addPlanet(planetDTO);
    }

    @PutMapping("/dto/planets")
    public void updatePlanet(@RequestBody PlanetDTO planetDTO) {
        planetService.updatePlanet(planetDTO);
    }

    @DeleteMapping("/dto/planets/{planetName}")
    public void deletePlanet(@PathVariable String planetName) {
        planetService.deletingPlanet(planetName);
    }

    @GetMapping("/dto/planets")
    public List<PlanetDTO> getPlanetsDTO(@RequestParam(value = "distance", required = false) Long distance ) {
        if (distance != null && distance > 0) {
            return planetService.getPlanetsByDistanceFromTheSun(distance);
        }
        return planetService.getPlanetsDTO();
    }
}

package michalwds.controllers;

import michalwds.models.dtos.PlanetDTO;
import michalwds.services.PlanetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private PlanetService planetService;

    //Audwired as a contructor
    public HomeController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("planets", planetService.getPlanetsDTO());

        return "index";
    }


    @GetMapping("/planets")
    public String planetPage(ModelMap modelMap) {
        modelMap.addAttribute("planets", planetService.getPlanetsDTO());
        return "planets";
    }

    @GetMapping("/delete")
    public String deletePlanet(@RequestParam(value = "planet") String planetName){
        planetService.deletingPlanet(planetName);
        return "redirect:/planets";
    }

    @PostMapping("/add")  //model attribute binds all inputs using keys
    public String addPlanet(@ModelAttribute PlanetDTO planet){
        planetService.addPlanet(planet);
        return "redirect:/planets";
    }


}

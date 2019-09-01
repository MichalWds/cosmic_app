package michalwds.controllers;

import michalwds.services.PlanetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private PlanetService planetService;

    //Audwired as a contructor
    public HomeController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/")
    public String homePage() {
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


}

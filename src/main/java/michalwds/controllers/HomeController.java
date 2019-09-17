package michalwds.controllers;

import michalwds.extras.CreatorXLS;
import michalwds.models.dtos.PlanetDTO;
import michalwds.services.PlanetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
        SecurityContext context = SecurityContextHolder.getContext();
        modelMap.addAttribute("message", "You Are logged in as "+ context.getAuthentication().getName());
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')") //authorization just for admin
    @GetMapping("/planets")
    public String planetPage(ModelMap modelMap) {
        modelMap.addAttribute("planets", planetService.getPlanetsDTO());
        return "planets";
    }

    @GetMapping("/delete")
    public String deletePlanet(@RequestParam(value = "planet") String planetName) {
        planetService.deletingPlanet(planetName);
        return "redirect:/planets";
    }

    @PostMapping("/add")  //model attribute binds all inputs using keys
    public String addPlanet(@ModelAttribute PlanetDTO planet) {
        planetService.addPlanet(planet);
        return "redirect:/planets";
    }

    @GetMapping("/excel")
    public String createFile() throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        CreatorXLS<PlanetDTO> creatorXLS = new CreatorXLS<>(PlanetDTO.class);
        creatorXLS.createFile(planetService.getPlanetsDTO(), "src/main/resources", "planets");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}

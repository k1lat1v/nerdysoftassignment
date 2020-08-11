package org.nerdysoft.vitaliim.controller;

import org.nerdysoft.vitaliim.data.Coordinate;
import org.nerdysoft.vitaliim.service.Service;
import org.nerdysoft.vitaliim.util.Mappings;
import org.nerdysoft.vitaliim.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    // == fields ==
    private final Service service;

    // == constructors ==
    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @ModelAttribute("service")
    public Service service(){
        return service;
    }

    // == handle methods ==
    @GetMapping(Mappings.HOME)
    public String homePage(){
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.CREATION)
    public String creationPage(Model model){
        Coordinate<Integer, Integer> coordinate = new Coordinate<Integer, Integer>();
        model.addAttribute("coordinatePair", coordinate);
        model.addAttribute("result", "Please enter the first pair of coordinates!");
        return ViewNames.CREATION;
    }

    @RequestMapping(value="/validateRoom", method= {RequestMethod.POST}, params={ "submitCoordinates=Submit"})
    public String submitCoordinates(@ModelAttribute("coordinatePair") Coordinate<Integer, Integer> coordinate, Model model){

        if(service.buildRoom()) {
            return "redirect:/" + ViewNames.ROOM;
        }

        String result = service.getInfo();
        model.addAttribute("result", result);

        return ViewNames.CREATION;
    }

    @RequestMapping(value="/validateRoom", method={RequestMethod.POST}, params={ "addCoordinates=Add"})
    public String addCoordinates(@ModelAttribute("coordinatePair") Coordinate<Integer, Integer> coordinate, Model model){
        service.addCoordinate(coordinate);
        String result = "Room can not be built. Please check for: </br>" +
                                                "1. At least 4 walls. </br>" +
                                                "2. Walls do not intersect. </br>" +
                                                "3. Providing a clockwise direction, room has a finite area.";
        if(service.buildRoom()) {
            result = "Room can be built! Press Submit to proceed.";
            model.addAttribute("result", result);
        }
        model.addAttribute("result", result);
        return ViewNames.CREATION;
    }

    @GetMapping(Mappings.ROOM)
    public String room(){
        return ViewNames.ROOM;
    }

}

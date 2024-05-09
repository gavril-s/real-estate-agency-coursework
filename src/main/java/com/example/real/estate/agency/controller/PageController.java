package com.example.real.estate.agency.controller;

import com.example.real.estate.agency.entity.RealEstateObject;
import com.example.real.estate.agency.repository.RealEstateObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private RealEstateObjectRepository realEstateObjectRepository;

    @GetMapping("/objects")
    public String objects(Model model) {
        model.addAttribute("realEstateObjects", realEstateObjectRepository.findAll());
        return "objects";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String address,
                         @RequestParam(required = false) String description,
                         @RequestParam(required = false) Integer areaMin,
                         @RequestParam(required = false) Integer areaMax,
                         @RequestParam(required = false) Integer priceMin,
                         @RequestParam(required = false) Integer priceMax,
                         @RequestParam(required = false) Integer buildYearMin,
                         @RequestParam(required = false) Integer buildYearMax,
                         @RequestParam(required = false) Integer minLivingRooms,
                         @RequestParam(required = false) Integer minBathRooms,
                         @RequestParam(defaultValue = "address") String sortBy,
                         @RequestParam(defaultValue = "asc") String sortDirection,
                         Model model) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        List<RealEstateObject> searchResults = realEstateObjectRepository.findByCriteria(
                address, description, areaMin, areaMax, priceMin, priceMax,
                buildYearMin, buildYearMax, minLivingRooms, minBathRooms, sort);

        model.addAttribute("realEstateObjects", searchResults);
        return "objects";
    }

    @GetMapping("/new-object")
    public String newObject() {
        return "new-object";
    }
}

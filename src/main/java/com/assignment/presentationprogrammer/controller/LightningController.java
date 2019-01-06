package com.assignment.presentationprogrammer.controller;

import com.assignment.presentationprogrammer.model.Lightning;
import com.assignment.presentationprogrammer.service.LightningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/lightning")
public class LightningController {

    @Autowired
    LightningService lightningService;

    @GetMapping
    public List<Lightning> list() {
        return lightningService.list();
    }

    @PostMapping
    public void create(@RequestBody List<Lightning> lightningList) {
        lightningService.create(lightningList);
    }

}

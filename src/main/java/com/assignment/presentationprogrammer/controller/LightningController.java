package com.assignment.presentationprogrammer.controller;

import com.assignment.presentationprogrammer.model.Lightning;
import com.assignment.presentationprogrammer.repository.LightningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/lightning")
public class LightningController {


    @Autowired
    LightningRepository lightningRepository;

    @GetMapping
    public List<Lightning> list() {
        return lightningRepository.findAll();

    }

    @PostMapping
    public List<Lightning> create(@RequestBody List<Lightning> lightningList) {
        return lightningRepository.saveAll(lightningList);
    }

}

package com.assignment.presentationprogrammer.controller;

import com.assignment.presentationprogrammer.model.Lightning;
import com.assignment.presentationprogrammer.repository.LightningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public int compare(int d, int d1) {
        return d - d1;
    }

    public Lightning findLigtning(List<Lightning> lightnings, int findByDuration) {
        Lightning fixedLighting = new Lightning();
        for (Lightning ligtning : lightnings) {
            if (ligtning.getDuration() == findByDuration) {
                fixedLighting = ligtning;
            }
        }
        return fixedLighting;
    }

    public Date timeSetter(int hour, int min) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        return cal.getTime();
    }

    @PostMapping
    public void create(@RequestBody List<Lightning> lightningList) {
        int hour = 9;
        int minuteStart = 0;
//        boolean flag = false; //TODO:
        List<Lightning> lightnings = lightningRepository.findAll();
        if (lightnings.size() > 0) {
            for (Lightning lightning : lightningList) {
                for (Lightning existLightning : lightnings) {
                    if (existLightning.getSubject().equals(lightning.getSubject())) {
                        lightningRepository.deleteById(existLightning.getId());
                    }
                }
            }
        }
        lightningList.sort((o1, o2) -> compare(o1.getDuration(), o2.getDuration()));
        for (Lightning lightning : lightningList) {
            lightning.setStarting(timeSetter(hour, minuteStart));
            minuteStart += lightning.getDuration();
            if (minuteStart > 60 || minuteStart == 60) {
                minuteStart = minuteStart - 60;
                hour++;
            }

            if (hour == 11) {
                findLigtning(lightningList, 60 - minuteStart);
                Lightning lunch=new Lightning();
                lunch.setSubject("Lunch");
                lunch.setStarting(timeSetter(12,0));
                lunch.setFinishing(timeSetter(13,0));
            }
            lightning.setFinishing(timeSetter(hour, minuteStart));
        }


        lightningRepository.saveAll(lightningList);
    }

}

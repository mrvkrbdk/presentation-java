package com.assignment.presentationprogrammer.controller;

import com.assignment.presentationprogrammer.model.Lightning;
import com.assignment.presentationprogrammer.repository.LightningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
    public void create(@RequestBody List<Lightning> lightningList) {
        int hour = 9;
        int minuteStart = 0;
        boolean flag = false;
        Date starting = new Date();
        Calendar cal = Calendar.getInstance();
        lightningList.sort((o1, o2) -> compare(o1.getDuration(), o2.getDuration()));
        for (Lightning lightning : lightningList) {
            minuteStart += lightning.getDuration();
            if (minuteStart > 60 || minuteStart==60) {
                minuteStart = minuteStart - 60;
                hour++;
            }
            /*TODO
            *gecici diziye ligningleri atarsam ardindan saat 11 oldugunda ama minute fazlaligi vasa minute
            *fazlaligi kadar olan durationli objeyi diziden cikarsam yerine 1 saatlik durationluyu eklesem
            * saat 11 de flagi trueya cekip gecici dizi icersinde dedigim durationluyu arayip indexini bulup suanki diziden diziden
             * cikartip  */
            if (hour == 11) {
                findLigtning(lightningList, 60 - minuteStart);
            }

            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minuteStart);
            starting = cal.getTime();
            lightning.setStarting(starting);
        }
        lightningRepository.saveAll(lightningList);
    }

}

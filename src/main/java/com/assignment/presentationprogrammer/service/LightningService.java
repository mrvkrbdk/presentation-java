package com.assignment.presentationprogrammer.service;

import com.assignment.presentationprogrammer.model.Lightning;
import com.assignment.presentationprogrammer.repository.LightningRepository;
import com.assignment.presentationprogrammer.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mervek
 */
@Service
public class LightningService extends Util {

    @Autowired
    LightningRepository lightningRepository;


    public List<Lightning> list() {
        return lightningRepository.findAll();
    }

    public void create(List<Lightning> lightningList) {
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

        List<Lightning> tmpArr = new ArrayList<>();

        for (Lightning lightning : lightningList) {
            lightning.setStarting(timeSetter(hour, minuteStart));
            minuteStart += lightning.getDuration();
            if (minuteStart > 60 || minuteStart == 60) {
                minuteStart = minuteStart - 60;
                hour++;
            }
            lightning.setFinishing(timeSetter(hour, minuteStart));
            tmpArr.add(lightning);
            if (hour == 11) {
                tmpArr.add(findLightning(lightningList, 60 - minuteStart));
                Lightning lunch = new Lightning();
                lunch.setSubject("Lunch");
                lunch.setStarting(timeSetter(12, 0));
                lunch.setFinishing(timeSetter(13, 0));
                lunch.setDuration(60);
                tmpArr.add(lunch);

                hour += 2;
                minuteStart = 0;
            }
        }
        lightningRepository.saveAll(tmpArr);
    }
}

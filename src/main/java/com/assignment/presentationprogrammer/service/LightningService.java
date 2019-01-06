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
            for (Lightning lightning:lightnings) {
                lightningRepository.deleteById(lightning.getId());
            }
//            for (Lightning lightning : lightningList) {
//                for (Lightning existLightning : lightnings) {
//                    if (existLightning.getSubject().equals(lightning.getSubject()) || existLightning.getSubject().equals("Lunch")) {
//                        lightningRepository.deleteById(existLightning.getId());
//                    }
//                    if (!lightning.getSubject().equals(existLightning.getSubject())){
//                        Lightning lightningRepositoryBySubject=lightningRepository.getBySubject(lightning.getSubject());
//                        lightningRepository.deleteById(lightningRepositoryBySubject.getId());
//                    }
//                }
//            }
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
                Lightning findedLightnig=findLightning(lightningList, 60 - minuteStart);
                findedLightnig.setStarting(timeSetter(hour,minuteStart));
                minuteStart += findedLightnig.getDuration();
                if (minuteStart > 60 || minuteStart == 60) {
                    minuteStart = minuteStart - 60;
                    hour++;
                }
                findedLightnig.setFinishing(timeSetter(hour,minuteStart));
                tmpArr.add(findedLightnig);

                Lightning lunch = new Lightning();
                lunch.setSubject("Lunch");
                lunch.setStarting(timeSetter(hour, minuteStart));
                hour ++;
                minuteStart = 0;
                lunch.setFinishing(timeSetter(hour, minuteStart));
                lunch.setDuration(60);
                tmpArr.add(lunch);

            }
            if (hour==17){
                break;
            }
        }
        lightningRepository.saveAll(tmpArr);
    }
}

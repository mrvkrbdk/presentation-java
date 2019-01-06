package com.assignment.presentationprogrammer.utils;

import com.assignment.presentationprogrammer.model.Lightning;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author mervek
 */
public class Util {

    public int compare(int d, int d1) {
        return d - d1;
    }

    public Lightning findLightning(List<Lightning> lightnings, int findByDuration) {
        Lightning fixedLighting = new Lightning();
        for (Lightning lightning : lightnings) {
            if (lightning.getDuration() == findByDuration) {
                fixedLighting = lightning;
            }
        }
        return fixedLighting;
    }

    public Date timeSetter(int hour, int min) {
        Calendar cal = Calendar.getInstance();
        // cal.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        return cal.getTime();
    }
}

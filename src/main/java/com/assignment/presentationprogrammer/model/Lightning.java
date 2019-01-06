package com.assignment.presentationprogrammer.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mervek
 */
@Entity
public class Lightning implements Serializable{
    private static final long serialVersionUID = 34563456345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;
    private int duration;

    private Date starting;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }
}

package Classes;


import java.sql.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schul
 */
public class WaitlistEntry {
    private String faculty;
    private Date date;
    private Integer seats;
    
    public WaitlistEntry(String faculty, Date date, Integer seats) {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    public String getFaculty() {
        return this.faculty;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    
    public Integer getSeats() {
        return this.seats;
    }
    
}

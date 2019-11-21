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
public class ReservationEntry {
    private String faculty;
    private RoomEntry room;
    private Date date;
    
    public ReservationEntry(String faculty, RoomEntry room, Date date) {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    public String getFaculty() {
        return this.faculty;
    }
    
    public void setRoom(RoomEntry room) {
        this.room = room;
    }
    
    public RoomEntry getRoom() {
        return this.room;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
}

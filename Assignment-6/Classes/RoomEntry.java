package Classes;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schul
 */
public class RoomEntry {
    private String name;
    private Integer seats;
    
    public RoomEntry(String name, Integer seats) {
        this.name = name;
        this.seats = seats;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    
    public Integer getSeats() {
        return this.seats;
    }
}

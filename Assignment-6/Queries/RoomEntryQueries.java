package Queries;


import Classes.RoomEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schul
 */
public class RoomEntryQueries {
    private static Connection connection;
    private static PreparedStatement addRoom;
    private static PreparedStatement deleteRoom;
    private static PreparedStatement getRooms;
    private static PreparedStatement getRoom;
    private static ResultSet resultSet;
    
    public static boolean addRoom(RoomEntry room) {
        
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into rooms values (?, ?)");
            addRoom.setString(1, room.getName());
            addRoom.setInt(2, room.getSeats());
            addRoom.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean deleteRoom(RoomEntry room) {
        
        connection = DBConnection.getConnection();
        try {
            deleteRoom = connection.prepareStatement("delete from rooms where name = ?");
            deleteRoom.setString(1, room.getName());
            deleteRoom.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static ArrayList<RoomEntry> getRooms() {
        
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        try {
            getRooms = connection.prepareStatement("select name, seats from rooms order by name");
            resultSet = getRooms.executeQuery();
            
            while(resultSet.next()) {
                rooms.add(new RoomEntry(resultSet.getString("name"), resultSet.getInt("seats")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static RoomEntry getRoom(String name) {
        
        connection = DBConnection.getConnection();
        RoomEntry room = new RoomEntry(name, 0);
        try {
            getRoom = connection.prepareStatement("select seats from rooms where name = ?");
            getRoom.setString(1, name);
            resultSet = getRoom.executeQuery();
            
            while(resultSet.next()) {
                room.setSeats(resultSet.getInt("seats"));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return room;
    }
}

package Queries;


import Classes.RoomEntry;
import Classes.ReservationEntry;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author schul
 */
public class ReservationEntryQueries {
    private static Connection connection;
    private static PreparedStatement addReservation;
    private static PreparedStatement deleteReservation;
    private static PreparedStatement getReservations;
    private static PreparedStatement getRooms;
    private static ResultSet resultSet;
    
    public static boolean addReservation(ReservationEntry reservation) {
        
        connection = DBConnection.getConnection();
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        try {
            addReservation = connection.prepareStatement("insert into reservations values (?, ?, ?, ?, ?)");
            addReservation.setString(1, reservation.getFaculty());
            addReservation.setString(2, reservation.getRoom().getName());
            addReservation.setDate(3, reservation.getDate());
            addReservation.setInt(4, reservation.getRoom().getSeats());
            addReservation.setTimestamp(5, timestamp);
            addReservation.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean deleteReservation(ReservationEntry reservation) {
        
        connection = DBConnection.getConnection();
        try {
            deleteReservation = connection.prepareStatement("delete from reservations where faculty = ? and room = ? and date = ?");
            deleteReservation.setString(1, reservation.getFaculty());
            deleteReservation.setString(2, reservation.getRoom().getName());
            deleteReservation.setDate(3, reservation.getDate());
            deleteReservation.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservations = connection.prepareStatement("select faculty, room, seats from reservations where date = ?");
            getReservations.setDate(1, date);
            resultSet = getReservations.executeQuery();
            
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), new RoomEntry(resultSet.getString("room"), resultSet.getInt("seats")), date));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservations = connection.prepareStatement("select room, seats, date from reservations where faculty = ?");
            getReservations.setString(1, faculty);
            resultSet = getReservations.executeQuery();
            
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(faculty, new RoomEntry(resultSet.getString("room"), resultSet.getInt("seats")), resultSet.getDate("date")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(RoomEntry room) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservations = connection.prepareStatement("select faculty, seats, date from reservations where room = ?");
            getReservations.setString(1, room.getName());
            resultSet = getReservations.executeQuery();
            
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), room, resultSet.getDate("date")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    public static ArrayList<ReservationEntry> getReservations() {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservations = connection.prepareStatement("select * from reservations");
            resultSet = getReservations.executeQuery();
            
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), new RoomEntry(resultSet.getString("room"), resultSet.getInt("seats")), resultSet.getDate("date")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    public static ArrayList<RoomEntry> getRoomsByDate(Date date) {
        
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();
        try {
            getRooms = connection.prepareStatement("select room, seats from reservations where date = ?");
            getRooms.setDate(1, date);
            resultSet = getRooms.executeQuery();
            
            while(resultSet.next()) {
                rooms.add(new RoomEntry(resultSet.getString("room"), resultSet.getInt("seats")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
}

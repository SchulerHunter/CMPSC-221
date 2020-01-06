package Queries;


import Classes.WaitlistEntry;
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
public class WaitlistEntryQueries {
    private static Connection connection;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static PreparedStatement getWaitlist;
    private static ResultSet resultSet;
    
    
    public static boolean addWaitlistEntry(WaitlistEntry waitlist) {
        
        connection = DBConnection.getConnection();
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        try {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist values (?, ?, ?, ?)");
            addWaitlistEntry.setString(1, waitlist.getFaculty());
            addWaitlistEntry.setDate(2, waitlist.getDate());
            addWaitlistEntry.setInt(3, waitlist.getSeats());
            addWaitlistEntry.setTimestamp(4, timestamp);
            addWaitlistEntry.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean deleteWaitlistEntry(WaitlistEntry waitlist) {
        
        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = ? and date = ? and seats = ?");
            deleteWaitlistEntry.setString(1, waitlist.getFaculty());
            deleteWaitlistEntry.setDate(2, waitlist.getDate());
            deleteWaitlistEntry.setInt(3, waitlist.getSeats());
            deleteWaitlistEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date) {
        
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlist = connection.prepareStatement("select faculty, seats from waitlist where date = ? order by timestamp");
            getWaitlist.setDate(1, date);
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next()) {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"), date, resultSet.getInt("seats")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlist = connection.prepareStatement("select date, seats from waitlist where faculty = ? order by timestamp");
            getWaitlist.setString(1, faculty);
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next()) {
                waitlist.add(new WaitlistEntry(faculty, resultSet.getDate("date"), resultSet.getInt("seats")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlist() {
        
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlist = connection.prepareStatement("select * from waitlist order by timestamp");
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next()) {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"), resultSet.getDate("date"), resultSet.getInt("seats")));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
}

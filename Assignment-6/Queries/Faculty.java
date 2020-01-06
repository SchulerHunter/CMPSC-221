package Queries;


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
public class Faculty {
    private static Connection connection;
    private static PreparedStatement checkFaculty;
    private static PreparedStatement addFaculty;
    private static PreparedStatement deleteFaculty;
    private static PreparedStatement getFacultyList;
    private static ResultSet resultSet;
    
    public static boolean checkFaculty(String name) {
        
        connection = DBConnection.getConnection();
        try {
            checkFaculty = connection.prepareStatement("select count(*) from faculty where name = ?");
            checkFaculty.setString(1, name);
            resultSet = checkFaculty.executeQuery();
            if (resultSet.absolute(1)) {
                return true;
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return false;
    }
    
    
    public static boolean addFaculty(String name) {
        
        connection = DBConnection.getConnection();
        try {
            addFaculty = connection.prepareStatement("insert into faculty values (?)");
            addFaculty.setString(1, name);
            addFaculty.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean deleteFaculty(String name) {
        
        connection = DBConnection.getConnection();
        try {
            deleteFaculty = connection.prepareStatement("delete from faculty where name = ?");
            deleteFaculty.setString(1, name);
            deleteFaculty.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static ArrayList<String> getFacultyList() {
        
        connection = DBConnection.getConnection();
        ArrayList<String> faculty = new ArrayList<String>();
        try {
            getFacultyList = connection.prepareStatement("select name from faculty order by name");
            resultSet = getFacultyList.executeQuery();
            
            while(resultSet.next()) {
                faculty.add(resultSet.getString(1));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return faculty;
    }
}

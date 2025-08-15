package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class DBHelper {
	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true";
    private static String username="user";
    private static String password = "123";
	
	private static Connection con =null;
    private static Statement stat =null;
    private static ResultSet rs =null;
    private static PreparedStatement prepstat=null;
    
 // This method links JSE to DB
    public static Connection connectDB() 
    {
        try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return con;
         
    }
    
    public static int saveData(Student student) 
    {
    	int st = 0;
    	// query 
    	String addQuery = "insert into StudentInfo (name , email , password , country)\n"
    			+ "values(? , ? , ? , ?)";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
			if(con != null)
			{
				// execute query insert
		    	prepstat = con.prepareStatement(addQuery);
		    	prepstat.setString(1, student.getName());
		    	prepstat.setString(2, student.getEmail());
		    	prepstat.setString(3, student.getPassword());
		    	prepstat.setString(4, student.getCountry());
		    	
		    	st = prepstat.executeUpdate();
		    	
		    	DBHelper.colseconnect();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	return st;
    }
    
    
    public static int updateData(Student student)
    {
    	int st = 0;
    	// query 
    	String updateQuery = "update StudentInfo "
    			+ "set name = ?, email = ? , password = ? , country = ?"
    			+ "where id = ?";
    			
    	 
    	try {
    		// make connect
			con = DBHelper.connectDB();
			// execute query update
	    	prepstat = con.prepareStatement(updateQuery);
	    	prepstat.setString(1, student.getName());
	    	prepstat.setString(2, student.getEmail());
	    	prepstat.setString(3, student.getPassword());
	    	prepstat.setString(4, student.getCountry());
	    	prepstat.setInt(5, student.getId());
	    	
	    	st = prepstat.executeUpdate();
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return st;
    }
    
    
    public static int deleteData(int id) 
    {
    	int st = 0;
    	// query 
    	String deleteQuery = "delete from StudentInfo "
    			+ "where id = ?";
    			
    	
    	try {
    		// make connect 
			con = DBHelper.connectDB();
			prepstat = con.prepareStatement(deleteQuery);
	    	prepstat.setInt(1, id);
	    	
	    	st = prepstat.executeUpdate();
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	// execute query delete
    	
    	
    	return st;
    }
    
    public static Student getDataById(int id) 
    {
    	Student student = new Student();
    	// query 
    	String showQuery = "select * from StudentInfo "
    			+ "where id = ?";
    			
    	
    	try {
    		// make connect 
        	con = DBHelper.connectDB();
        	// execute query delete
			prepstat = con.prepareStatement(showQuery);
			prepstat.setInt(1, id);
	    	
	    	rs = prepstat.executeQuery();
	    	
	    	if(rs.next())
	        {
	            student.setId(rs.getInt(1));
	            student.setName(rs.getString(2));
	            student.setEmail(rs.getString(3));
	            student.setPassword(rs.getString(4));
	            student.setCountry(rs.getString(5));
	        }
	    	
	    	DBHelper.colseconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return student;
    }
    
    public static List<Student> getAllData() 
    {
    	List<Student> list = new ArrayList<Student>();
    	// query 
    	String showQuery = "select * from StudentInfo";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
			// execute query delete
	    	prepstat = con.prepareStatement(showQuery);
	    	
	    	rs = prepstat.executeQuery();
	    	
	    	while(rs.next())
	        {
	    		Student student = new Student();
	    		student.setId(rs.getInt(1));
	            student.setName(rs.getString(2));
	            student.setEmail(rs.getString(3));
	            student.setPassword(rs.getString(4));
	            student.setCountry(rs.getString(5));
	    		
	    		list.add(student);
	        }
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    	
    	
    	return list;
    	
    
    }
    
 // This method close links JSE to DB after finash working
    public static void colseconnect() throws SQLException
    {
        if(rs != null)
        { rs.close(); } 

        if(stat != null)
        { stat.close(); } 

        if(prepstat != null)
        {  prepstat.close();} 

        if(con != null)
        { con.close(); }
         
    }
	        
	       
	

}

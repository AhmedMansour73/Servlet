package linker;

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
	

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/orclpdb";
    private static final String USER = "hr";
    private static final String PASS = "hr";
    
	private static Connection con =null;
    private static Statement stat =null;
    private static ResultSet rs =null;
    private static PreparedStatement prepstat=null;
    
 // This method links JSE to DB
    public static Connection connectDB() 
    {
        try {
        	
        	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return DriverManager.getConnection(JDBC_URL, USER, PASS);
		
        } catch (SQLException e) {
        	System.err.println("Connection failed. Check connection details.");
			e.printStackTrace();
		}
		return con;
         
    }
    
    public static int chackSSNUnique(String ssn)
    {
    	int count = 0;
    	// query 
        String sql = "SELECT COUNT(*) FROM Students_Information WHERE student_SSN = '" + ssn + "'";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
		    	
			stat = con.createStatement();
	        rs = stat.executeQuery(sql) ;
	        if (rs.next()) {
	            count = rs.getInt(1); 
	        }
		    DBHelper.colseconnect();
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    	
    	return count;
    	
    }
    
    
    public static int chackEmailUnique(String email)
    {
    	int count = 0;
    	// query 
        String sql = "SELECT COUNT(*) FROM Students_Information WHERE student_email = '" + email + "'";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
		    	
			stat = con.createStatement();
	        rs = stat.executeQuery(sql) ;
	        if (rs.next()) {
	            count = rs.getInt(1); 
	        }
		    DBHelper.colseconnect();
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    	
    	return count;
    	
    }

    public static int chackPhoneUnique(String phone)
    {
    	int count = 0;
    	// query 
        String sql = "SELECT COUNT(*) FROM Students_Information WHERE student_phone = '" + phone + "'";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
		    	
			stat = con.createStatement();
	        rs = stat.executeQuery(sql) ;
	        if (rs.next()) {
	            count = rs.getInt(1); 
	        }
		    DBHelper.colseconnect();
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    	
    	return count;
    	
    }
    
    public static int saveData(Student student) 
    {
    	int addStatus = 0;
    	// query 
    	String addQuery = "INSERT INTO Students_Information "
    			+ "(student_name, student_SSN, student_birth_date, student_email, student_password, student_country, student_age, student_phone) "
    			+ "VALUES "
    			+ "(?, ?, ?, ?, ?, ? , ? , ? )";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
			if(con != null)
			{
				// execute query insert
		    	prepstat = con.prepareStatement(addQuery);
		    	prepstat.setString( 1 	, student.getName() );
		    	prepstat.setString( 2	, student.getSsn() );
		    	prepstat.setDate( 3     , student.getBirthDate() );
		    	prepstat.setString( 4	, student.getEmail());
		    	prepstat.setString( 5	, student.getPassword());
		    	prepstat.setString( 6	, student.getCountry());
		    	prepstat.setInt( 7      , student.getAge() );
		    	prepstat.setString( 8	, student.getPhone() );
		    	
		    	addStatus = prepstat.executeUpdate();
		    	
		    	DBHelper.colseconnect();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    	
    	
    	return addStatus;
    }
    
    
    public static int updateDataById(Student student)
    {
    	int updateStatus = 0;
    	// query 
    	String updateQuery = "UPDATE Students_Information\r\n"
    			+ "SET \r\n"
    			+ "    student_name = ? ,\r\n"
    			+ "    student_SSN = ? ,\r\n"
    			+ "    student_birth_date =  ? ,\r\n"
    			+ "    student_email = ? ,\r\n"
    			+ "    student_password = ? ,\r\n"
    			+ "    student_country = ? ,\r\n"
    			+ "    student_age = ? ,\r\n"
    			+ "    student_phone = ? \r\n"
    			+ "WHERE student_id = ? ";
    			
    	/* 
    	UPDATE Students_Information
    	SET student_name = 'Ahmed Ali', student_birth_date = TO_DATE('15-08-2001', 'dd-MM-yyyy'), student_email = 'ahmed.ali@gmail.com',
    	    student_password = 'NewPass@123', tudent_country = 'Egypt', student_age = 22, student_phone = '01098765432'
    	WHERE student_SSN = 12345678901234;
    	*/
    	try {
    		// make connect
			con = DBHelper.connectDB();
			// execute query update
			prepstat = con.prepareStatement(updateQuery);
	    	prepstat.setString( 1 	, student.getName() );
	    	prepstat.setString( 2		, student.getSsn() );
	    	prepstat.setDate( 3     , student.getBirthDate() );
	    	prepstat.setString( 4	, student.getEmail());
	    	prepstat.setString( 5	, student.getPassword());
	    	prepstat.setString( 6	, student.getCountry());
	    	prepstat.setInt( 7      , student.getAge() );
	    	prepstat.setString( 8		, student.getPhone() );
	    	prepstat.setLong( 9		, student.getId() );
	    	
	    	updateStatus = prepstat.executeUpdate();
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	return updateStatus;
    }
//    
//    
    public static int deleteDataById(Long id) 
    {
    	int deleteStatus = 0;
    	// query 
    	String deleteQuery = "DELETE FROM Students_Information\r\n"
    			+ "WHERE student_id =  ?";
    			
    	
    	try {
    		// make connect 
			con = DBHelper.connectDB();
			prepstat = con.prepareStatement(deleteQuery);
	    	prepstat.setLong(1, id);
	    	
	    	deleteStatus = prepstat.executeUpdate();
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	// execute query delete
    	
    	
    	return deleteStatus;
    }
    
//    
    
    public static Student getDataById(Long id) 
    {
    	Student student = new Student();
    	// query 
    	String showQuery = "SELECT *\r\n"
    			+ "FROM Students_Information\r\n"
    			+ "WHERE student_id = ?";
    	
    	try {
    		// make connect 
        	con = DBHelper.connectDB();
        	
        	// execute query delete
//        	prepstat.setString(1, "%" + name + "%");
			prepstat = con.prepareStatement(showQuery);
			prepstat.setLong(1, id);
	    	
	    	rs = prepstat.executeQuery();
	    	
	    	if(rs.next())
	        {
	    		student.setId(rs.getLong("student_id"));
	    		student.setName(rs.getString("student_name"));
	    		student.setSsn(rs.getString("student_SSN"));
	    		student.setBirthDate(rs.getDate("student_birth_date"));
	    		student.setEmail(rs.getString("student_email"));
	    		student.setPassword(rs.getString("student_password"));
	    		student.setCountry(rs.getString("student_country"));
	    		student.setAge(rs.getInt("student_age"));
	    		student.setPhone(rs.getString("student_phone"));
	        }
	    	
	    	DBHelper.colseconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return student;
    }
    
//    
    public static List<Student> getDataByname(String name) 
    {
    	List<Student> studentsList = new ArrayList<Student>();
    	// query 
    	String showQuery = "SELECT *\r\n"
    			+ "FROM Students_Information\r\n"
    			+ "WHERE LOWER(student_name) like ?";
    	
    	try {
    		// make connect 
        	con = DBHelper.connectDB();
        	
        	// execute query delete
//        	prepstat.setString(1, "%" + name + "%");
//        	prepstat.setString(1, "%" + name.toLowerCase() + "%");
//			prepstat = con.prepareStatement(showQuery);
			
			 if (name != null && !name.trim().isEmpty()) {
				 prepstat = con.prepareStatement(showQuery);
				 prepstat.setString(1, "%" + name.toLowerCase() + "%");
			    } else {
//			        ps = conn.prepareStatement("SELECT * FROM students");
			    }
	    	
	    	rs = prepstat.executeQuery();
	    	
	    	while(rs.next())
	        {
	    		Student student = new Student();
	    		
	    		student.setId(rs.getLong("student_id"));
	    		student.setName(rs.getString("student_name"));
	    		student.setSsn(rs.getString("student_SSN"));
	    		student.setBirthDate(rs.getDate("student_birth_date"));
	    		student.setEmail(rs.getString("student_email"));
	    		student.setPassword(rs.getString("student_password"));
	    		student.setCountry(rs.getString("student_country"));
	    		student.setAge(rs.getInt("student_age"));
	    		student.setPhone(rs.getString("student_phone"));
	    		
	            studentsList.add(student);
	        }
	    	
	    	DBHelper.colseconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return studentsList;
    }
//    
    
    public static List<Student> getAllData() 
    {
    	List<Student> studentsList = new ArrayList<Student>();
    	// query 
    	String showQuery = "SELECT * FROM Students_Information ORDER BY student_id  ASC";
    			
    	// make connect 
    	try {
			con = DBHelper.connectDB();
			// execute query delete
	    	prepstat = con.prepareStatement(showQuery);
	    	
	    	rs = prepstat.executeQuery();
	    	
	    	while(rs.next())
	        {
	    		Student student = new Student();
	    		
	    		student.setId(rs.getLong("student_id"));
	    		student.setName(rs.getString("student_name"));
	    		student.setSsn(rs.getString("student_SSN"));
	    		student.setBirthDate(rs.getDate("student_birth_date"));
	    		student.setEmail(rs.getString("student_email"));
	    		student.setPassword(rs.getString("student_password"));
	    		student.setCountry(rs.getString("student_country"));
	    		student.setAge(rs.getInt("student_age"));
	    		student.setPhone(rs.getString("student_phone"));
	    		
	            studentsList.add(student);
	        }
	    	
	    	DBHelper.colseconnect();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    	
    	
    	return studentsList;
    	
    
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

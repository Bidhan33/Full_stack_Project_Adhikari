package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Student;

/**
 * DAO class for accessing movies. NB! There should be NO user input/output in
 * this class! This class can be used in a stand-alone Java application or as a
 * part of the server-side implementation of a web application. => This code
 * works as it is with all major RDBMSes and SQLite, Excel etc.
 * 
 * @author Kari
 */
public class StudentDAO {

    public StudentDAO() {
        try {
            Class.forName(ConnectionParameters.jdbcDriver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }
    /**
	 * Open a new database connection
	 * 
	 * @throws SQLException
	 */
    private static Connection openConnection() throws SQLException {
    		try {
    			Class.forName("org.sqlite.JDBC");
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
        return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
                ConnectionParameters.password);
    }
    /**
	 * Retrieve all movies from the database
	 * 
	 * @return List<Movie>
	 * @throws SQLException
	 */

    public List<Student> getAllStudents() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();

        try {
            connection = openConnection();

            String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY id, firstname, lastname, streetaddress, postcode, postoffice";

            preparedStatement = connection.prepareStatement(sqlText);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String streetaddress = resultSet.getString("streetaddress");
                String postoffice = resultSet.getString("postoffice");
                int postcode = resultSet.getInt("postcode");
                studentList.add(new Student(id, firstname, lastname, streetaddress, postoffice, postcode));
            }

        } catch (SQLException sqle) {
            System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");

        } finally {
            DbUtils.closeQuietly(resultSet, preparedStatement, connection);
        }

        return studentList;
    }
    /**
	 * Retrieve all movies from the given year from the database
	 * 
	 * @param givenYear - the year to be used as the filter in the query
	 * @return List<Movie>
	 * @throws SQLException
	 */
    public String getAllStudentsJSON() {
        List<Student> studentList = getAllStudents();
        Gson gson = new Gson();
        String jsonString = gson.toJson(studentList);
        return jsonString;
    }

    public Student getStudentById(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;

        try {
            connection = openConnection();

            String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ?";

            preparedStatement = connection.prepareStatement(sqlText);
            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String streetaddress = resultSet.getString("streetaddress");
                String postoffice = resultSet.getString("postoffice");
                int postcode = resultSet.getInt("postcode");
                student = new Student(id, firstname, lastname, streetaddress, postoffice, postcode);
            }

        } catch (SQLException sqle) {
            System.out.println("[ERROR] StudentDAO: getStudentById() failed. " + sqle.getMessage() + "\n");
        } finally {
            DbUtils.closeQuietly(resultSet, preparedStatement, connection);
        }

        return student;
    }
    /**
	 * Insert a movie into the database
	 * 
	 * @return 0 = Ok | 1 = Duplicate id | -1 = Other error
	 * @throws SQLException
	 */
    public int insertStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
       

        try {
            connection = openConnection();

            String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sqlText);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getStreetAddress());
            preparedStatement.setInt(5, student.getPostCode());
            preparedStatement.setString(6, student.getPostOffice());

          preparedStatement.executeUpdate();
            return 0;

         

        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
                return 1; 
            } else {
                System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
               return -1;
            }

        } finally {
            DbUtils.closeQuietly(preparedStatement, connection);
        }

       
    }
    public static int deleteStudent(int studentId) {
    	  Connection connection = null;
          PreparedStatement preparedStatement = null;
          
          try {
              connection = openConnection();

              String sqlText = "DELETE FROM Student WHERE id = ?";

              preparedStatement = connection.prepareStatement(sqlText);
              preparedStatement.setInt(1, studentId);

              int change = preparedStatement.executeUpdate();
              if (change == 0) {
                  return 1;
              } else {
                  return 0; // Student ID not found
              }

          } catch (SQLException sqle) {
              System.out.println("[ERROR] StudentDAO: deleteStudent() failed. " + sqle.getMessage() + "\n");
              return -1;
          } finally {
              DbUtils.closeQuietly(preparedStatement, connection);
          }   
    	
    }
    
    public int updateStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        

        try {
            connection = openConnection();

            String sqlText = "UPDATE Student SET firstname=?, lastname=?, streetaddress=?, postcode=?, postoffice=? WHERE id=?";

            preparedStatement = connection.prepareStatement(sqlText);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getStreetAddress());
            preparedStatement.setInt(4, student.getPostCode());
            preparedStatement.setString(5, student.getPostOffice());
            preparedStatement.setInt(6, student.getId());

            int update = preparedStatement.executeUpdate();
            if (update == 0) {
                return 1; 
            } else {
                return 0; 
            }

        } catch (SQLException sqle) {
            System.out.println("\n[ERROR] StudentDAO: updateStudent() failed. " + sqle.getMessage() + "\n");
            return -1;
        } finally {
            DbUtils.closeQuietly(preparedStatement, connection);
        }
    }
}

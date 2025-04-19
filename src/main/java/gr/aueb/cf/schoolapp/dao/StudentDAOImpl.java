package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StudentDAOImpl implements IStudentDAO{


    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO students (firstname, lastname, fathername, phone_num,)" +
                "email, street, street_num, zipcode, city_id, uuid, created_at, updated_at" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Student insertedStudent = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getFatherName());
            ps.setString(4, student.getPhoneNum());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getStreet());
            ps.setString(7, student.getStreetNum());
            ps.setString(8, student.getZipCode());
            ps.setInt(9, student.getCityId());
            ps.setString(10, UUID.randomUUID().toString());
            ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                int generateId = rsGeneratedKeys.getInt(1);
                insertedStudent = getById(generateId);
            }
            return  insertedStudent;
        } catch (SQLException e) {
            throw new StudentDAOException("SQL error in insert student with email: " + student.getEmail() +)
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws StudentDAOException {

    }

    @Override
    public Student getById(Integer id) throws StudentDAOException {
        return null;
    }

    @Override
    public List<Student> getAll() throws StudentDAOException {
        return List.of();
    }
}

package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            throw new StudentDAOException("SQL error in insert student with email: " + student.getEmail() );
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE students SET firstname = ?, lastname = ?, fathername = ?, phone_num = ?," +
                " email = ?, street = ?, street_num = ?, zipcode = ?, city_id = ?," +
                " updated_at = ? WHERE id = ?";
        Student updatedStudent;

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getFatherName());
            ps.setString(4, student.getPhoneNum());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getStreet());
            ps.setString(7, student.getStreetNum());
            ps.setString(8, student.getZipCode());
            ps.setInt(9, student.getCityId());
            ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(12, student.getId());

            ps.executeUpdate();
            updatedStudent = getById(student.getId());

            return updatedStudent;
        } catch (SQLException e) {
            throw new StudentDAOException("SQL error in update student with email: " + student.getEmail());

        }
    }

    @Override
    public void delete(Integer id) throws StudentDAOException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            // logging
        } catch (SQLException e) {
            // e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in delete with student id: " + id);
        }
    }

    @Override
    public Student getById(Integer id) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("fathername"), rs.getString("phone_num"), rs.getString("email"), rs.getString("street"),
                        rs.getString("street_num"), rs.getString("zipcode"), rs.getInt("city_id"), rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(), rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return student;
        } catch (SQLException e) {
            throw new StudentDAOException("SQL error in get by id with id: " + student.getId());
        }
    }

    @Override
    public List<Student> getAll() throws StudentDAOException {
        String sql = "SELECT * FROM students";
        Student student;
        List<Student> students = new ArrayList<>();
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("fathername"), rs.getString("phone_num"), rs.getString("email"), rs.getString("street"),
                        rs.getString("street_num"), rs.getString("zipcode"), rs.getInt("city_id"), rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(), rs.getTimestamp("updated_at").toLocalDateTime());
                students.add(student);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in get all");
        }
        return students;
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        List<Student> students = new ArrayList<>();
        Student student;
        String sql = "SELECT * FROM students WHERE lastname LIKE ?";
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("fathername"), rs.getString("phone_num"), rs.getString("email"), rs.getString("street"),
                        rs.getString("street_num"), rs.getString("zipcode"), rs.getInt("city_id"), rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(), rs.getTimestamp("updated_at").toLocalDateTime());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            // e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in get with lastname: " + lastname);
        }
    }

    @Override
    public List<Student> getFilteredStudents(String firstname, String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE firstname LIKE ? AND lastname LIKE ?";
        List<Student> students = new ArrayList<>(); // isEmpty == true
        ResultSet rs;
        Student student;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, firstname + "%");
            ps.setString(2, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                         rs.getString("fathername"), rs.getString("phone_num"), rs.getString("email"),
                        rs.getString("street"), rs.getString("street_num"), rs.getString("zipcode"), rs.getInt("city_id"),
                        rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
                students.add(student);
            }
            // Logging
            students.forEach(System.out::println);
            return students;
        } catch (SQLException e) {
            // e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in filtered get");
        }
    }
}

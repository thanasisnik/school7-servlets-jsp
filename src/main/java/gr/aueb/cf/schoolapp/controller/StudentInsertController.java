package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp.validator.StudentValidator;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/students/insert")
public class StudentInsertController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<City> cities = null;
        try {
            cities = cityService.getAllCities();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            request.getSession().setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp")
                    .forward(request, response);
        }
        request.setAttribute("cities", cities);
        //req.getSession().setAttribute("insertDTO", teacherInsertDTO);
        if (request.getSession().getAttribute("insertDTO") != null) {
            // Move from session to request scope for JSP
            request.setAttribute("insertDTO", request.getSession().getAttribute("insertDTO"));

            // Clear session data (so it doesn't persist after refresh)
            request.getSession().removeAttribute("insertDTO");

        }
        request.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentInsertDTO studentInsertDTO;
        Map<String, String> errors;

        String firstnameMessage;
        String lastnameMessage;
        String fathernameMessage;
        String phoneNumMessage;
        String emailMessage;
        String streetMessage;
        String streetNumMessage;
        String zipcodeMessage;
        String cityIdMessage;
        String errorMessage;
        Student student;

        String firstname = (request.getParameter("firstname") != null) ? request.getParameter("firstname").trim() : "";
        String lastname = (request.getParameter("lastname") != null) ? request.getParameter("lastname").trim() : "";
        String fathername = (request.getParameter("fathername") != null) ? request.getParameter("fathername").trim() : "";
        String phoneNum = (request.getParameter("phoneNum") != null) ? request.getParameter("phoneNum").trim() : "";
        String email = (request.getParameter("email") != null) ? request.getParameter("email").trim() : "";
        String street = (request.getParameter("street") != null) ? request.getParameter("street").trim() : "";
        String streetNum = (request.getParameter("streetNum") != null) ? request.getParameter("streetNum").trim() : "";
        String zipcode = (request.getParameter("zipcode") != null) ? request.getParameter("zipcode").trim() : "";
        Integer cityId = (request.getParameter("cityId") != null) ? Integer.parseInt(request.getParameter("cityId").trim()) : 0;

        studentInsertDTO = new StudentInsertDTO(firstname, lastname,fathername, phoneNum,
                email, street, streetNum, zipcode, cityId);


        try {
            // Validate dto
            errors = StudentValidator.validate(studentInsertDTO);

            if (!errors.isEmpty()) {

                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");
                fathernameMessage = errors.getOrDefault("fathername", "");
                phoneNumMessage = errors.getOrDefault("phoneNum", "");


                request.getSession().setAttribute("firstnameMessage", firstnameMessage);
                request.getSession().setAttribute("lastnameMessage", lastnameMessage);
                request.getSession().setAttribute("fathernameMessage", fathernameMessage);
                request.getSession().setAttribute("phoneNumMessage", phoneNumMessage);
                request.getSession().setAttribute("studentInsertDTO", studentInsertDTO);

                response.sendRedirect(request.getContextPath() + "/school-app/students/insert");
                return;
            }

            StudentReadOnlyDTO readOnlyDTO = studentService.insertStudent(studentInsertDTO);
            HttpSession session = request.getSession(false);
            session.setAttribute("studentInfo", readOnlyDTO);
            System.out.println("[DEBUG] Student to store in session: " + readOnlyDTO);  // logging
            response.sendRedirect(request.getContextPath() + "/school-app/student-inserted");

    } catch (StudentDAOException | StudentAlreadyExistsException e) {
            errorMessage = e.getMessage();
            request.setAttribute("error message", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp")
                    .forward(request, response);
        }
    }

}

package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.*;
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
import org.jboss.weld.context.http.Http;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/students/update")
public class StudentUpdateController extends HttpServlet {
    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = null;
        Integer id = Integer.parseInt(req.getParameter("id").trim());
        try {
            cities = cityService.getAllCities();
            StudentReadOnlyDTO studentReadOnlyDTO = studentService.getStudentById(id);
            req.setAttribute("cities", cities);
            if (req.getSession().getAttribute("updateDTO") != null) {
                // Move from session to request scope for JSP
                req.setAttribute("updateDTO", req.getSession().getAttribute("updateDTO"));
                //req.setAttribute("errors", req.getSession().getAttribute("errors"));

                // Clear session data (so it doesn't persist after refresh)
                req.getSession().removeAttribute("updateDTO");
                //req.getSession().removeAttribute("errors");
            }
            else req.setAttribute("updateDTO", studentReadOnlyDTO);
            req.getRequestDispatcher("/WEB-INF/jsp/student-update.jsp").forward(req, resp);
        } catch (SQLException | StudentDAOException | StudentNotFoundException e) {
            String errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-update.jsp")
                    .forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentUpdateDTO updateDTO;
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

        String idStr = (req.getParameter("id") != null) ? req.getParameter("id").trim() : "";
        Integer id = Integer.parseInt(idStr);
        String firstname = (req.getParameter("firstname") != null) ? req.getParameter("firstname").trim() : "";
        String lastname = (req.getParameter("lastname") != null) ? req.getParameter("lastname").trim() : "";
        String fathername = (req.getParameter("fathername") != null) ? req.getParameter("fathername").trim() : "";
        String phoneNum = (req.getParameter("phoneNum") != null) ? req.getParameter("phoneNum").trim() : "";
        String email = (req.getParameter("email") != null) ? req.getParameter("email").trim() : "";
        String street = (req.getParameter("street") != null) ? req.getParameter("street").trim() : "";
        String streetNum = (req.getParameter("streetNum") != null) ? req.getParameter("streetNum").trim() : "";
        String zipcode = (req.getParameter("zipcode") != null) ? req.getParameter("zipcode").trim() : "";
        Integer cityId = (req.getParameter("cityId") != null) ? Integer.parseInt(req.getParameter("cityId").trim()) : 0;
        updateDTO = new StudentUpdateDTO(id, firstname, lastname, fathername, phoneNum,
                email, street, streetNum, zipcode, cityId);

        try {
            // Validate dto
            errors = StudentValidator.validate(updateDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");
                fathernameMessage = errors.getOrDefault("fathername", "");
                phoneNumMessage = errors.getOrDefault("phoneNum", "");
                // more ...

                req.getSession().setAttribute("firstnameMessage", firstnameMessage);
                req.getSession().setAttribute("lastnameMessage", lastnameMessage);
                req.getSession().setAttribute("fathernameMessage", fathernameMessage);
                req.getSession().setAttribute("phoneNumMessage", phoneNumMessage);
                req.getSession().setAttribute("updateDTO", updateDTO);
//                req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
//                        .forward(req, resp);
                resp.sendRedirect(req.getContextPath() + "/school-app/students/update?id=" + id);
                return;
            }

            // Call the service

            StudentReadOnlyDTO readOnlyDTO = studentService.updateStudent(id, updateDTO);
            HttpSession session = req.getSession(false);
            session.setAttribute("teacherInfo", readOnlyDTO);
            // PRG Pattern
            resp.sendRedirect(req.getContextPath() + "/school-app/teacher-updated");
//            req.getRequestDispatcher("/WEB-INF/jsp/teacher-inserted.jsp")
//                    .forward(req, resp);
        } catch (StudentDAOException | StudentAlreadyExistsException | StudentNotFoundException e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp")
                    .forward(req, resp);
        }
    }
}

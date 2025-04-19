package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseStudentDTO;

import java.util.HashMap;
import java.util.Map;

public class StudentValidator {

    private StudentValidator() {
    }

    public static <T extends BaseStudentDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname() == null || dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Το όνομα πρέπει να είναι από 2 έως 32 χαρακτήρες");
        }

        if (dto.getFirstname() != null && dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Το όνομα δεν πρέπει να περιέχει κενά");
        }

        if (dto.getLastname() == null || dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "Το επώνυμο πρέπει να είναι από 2 έως 32 χαρακτήρες");
        }

        if (dto.getLastname() != null && dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Το επώνυμο δεν πρέπει να περιέχει κενά");
        }

        if (dto.getFatherName() == null || dto.getFatherName().length() < 2 || dto.getFatherName().length() > 32) {
            errors.put("fatherName", "Το όνομα πατρός πρέπει να είναι από 2 έως 32 χαρακτήρες");
        }

        if (dto.getFatherName() != null && dto.getFatherName().matches("^.*\\s+.*$")) {
            errors.put("fatherName", "Το όνομα πατρός δεν πρέπει να περιέχει κενά");
        }

        if (dto.getPhoneNum() == null || dto.getPhoneNum().length() < 8 || dto.getPhoneNum().length() > 15) {
            errors.put("phoneNum", "Ο αριθμός τηλεφώνου πρέπει να είναι από 8 έως 15 ψηφία");
        }

        if (dto.getPhoneNum() != null && dto.getPhoneNum().matches("^.*\\s+.*$")) {
            errors.put("phoneNum", "Ο αριθμός τηλεφώνου δεν πρέπει να περιέχει κενά");
        }

        if (dto.getEmail() == null || !dto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            errors.put("email", "Το email δεν είναι έγκυρο");
        }

        return errors;
    }

}

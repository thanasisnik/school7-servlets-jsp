package gr.aueb.cf.schoolapp.dto;

public class StudentInsertDTO extends BaseStudentDTO{

    public StudentInsertDTO() {

    }

    public StudentInsertDTO(String firstname, String lastname, String fatherName,
                            String phoneNum, String email, String street, String streetNum,
                            String zipCode, Integer cityId) {
        super(firstname, lastname, fatherName, phoneNum, email, street, streetNum, zipCode, cityId);
    }
}

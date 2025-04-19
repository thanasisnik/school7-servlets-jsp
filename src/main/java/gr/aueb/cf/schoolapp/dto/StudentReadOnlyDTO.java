package gr.aueb.cf.schoolapp.dto;

public class StudentReadOnlyDTO extends BaseStudentDTO {
    private Integer id;
    private String uuid;

    public StudentReadOnlyDTO() {

    }

    public StudentReadOnlyDTO(String firstname, String lastname, String fatherName,
                              String phoneNum, String email, String street, String streetNum,
                              String zipCode, Integer cityId, Integer id, String uuid) {
        super(firstname, lastname, fatherName, phoneNum, email, street, streetNum, zipCode, cityId);
        this.id = id;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "StudentReadOnlyDTO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

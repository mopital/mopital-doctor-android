package models;

import java.util.List;

/**
 * Created by AlperCem on 28.2.2015.
 */
public class Patient {

    private String name;
    private String bloodType;
    private String fileNo;
    private String admissionDate;
    private String age;
    private String weight;
    private String height;
    private List<Treatment> treatments;

    public Patient(String name, String bloodType, String fileNo, String admissionDate, String age, String weight, String height, List<Treatment> treatments) {
        this.name = name;
        this.bloodType = bloodType;
        this.fileNo = fileNo;
        this.admissionDate = admissionDate;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.treatments = treatments;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}

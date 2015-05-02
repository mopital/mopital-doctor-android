package com.mopital.doctor.models;

/**
 * Created by AlperCem on 24.4.2015.
 */
public class Equipment {
    private String id;
    private String recordId;
    private String name;
    private String typeOfEquipment;

    public Equipment(String id, String recordId, String name, String typeOfEquipment) {
        this.id = id;
        this.recordId = recordId;
        this.name = name;
        this.typeOfEquipment = typeOfEquipment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfEquipment() {
        return typeOfEquipment;
    }

    public void setTypeOfEquipment(String typeOfEquipment) {
        this.typeOfEquipment = typeOfEquipment;
    }
}

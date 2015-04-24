package com.mopital.doctor.models.wrappers;

import com.mopital.doctor.models.Equipment;

import java.util.List;

/**
 * Created by AlperCem on 24.4.2015.
 */
public class EquipmentListWrapper {

    private List<Equipment> equipmentList;

    public EquipmentListWrapper(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}

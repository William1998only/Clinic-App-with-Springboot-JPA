package com.company.msclinic.dto;

import lombok.Data;

@Data
public class InsertMedicineTypeRequestDTO {
    private String medicineType;
    private Integer medicineCodeType;
}

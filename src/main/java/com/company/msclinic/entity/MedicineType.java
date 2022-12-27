package com.company.msclinic.entity;

import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "t_m_medicine_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicine_type", nullable = false, length = 20)
    private String medType;

    @Column(name = "medicine_code_type", nullable = false, unique = true)
    private Integer medicineCodeType;

    public MedicineType(InsertMedicineTypeRequestDTO request) {
        this.medType = request.getMedicineType();
        this.medicineCodeType = request.getMedicineCodeType();
    }
}

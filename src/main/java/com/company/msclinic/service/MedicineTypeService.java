package com.company.msclinic.service;

import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.entity.MedicineType;
import com.company.msclinic.repository.MedicineTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicineTypeService {
    private final MedicineTypeRepository medicineTypeRepository;

    public MedicineTypeService(MedicineTypeRepository medicineTypeRepository) {
        this.medicineTypeRepository = medicineTypeRepository;
    }

    public void insertMedicineType(InsertMedicineTypeRequestDTO request) {
        MedicineType medicineType = new MedicineType(request);

        medicineTypeRepository.save(medicineType);
    }
}

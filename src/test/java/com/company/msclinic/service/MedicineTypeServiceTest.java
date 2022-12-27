package com.company.msclinic.service;

import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.entity.MedicineType;
import com.company.msclinic.repository.MedicineTypeRepository;
import com.company.msclinic.service.abstraction.AbstractServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MedicineTypeServiceTest extends AbstractServiceTest {
    @MockBean
    private MedicineTypeRepository medicineTypeRepository;

    @Autowired
    private MedicineTypeService medicineTypeService;

    @Nested
    @DisplayName("insertMedicineType Test Case")
    class InsertMedicineTypeTestCase {

        @Test
        @DisplayName("Expect to call repository when data is saved")
        void success() {
            InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
            request.setMedicineType("sirup");
            request.setMedicineCodeType(1);

            medicineTypeService.insertMedicineType(request);

            verify(medicineTypeRepository, times(1)).save(any(MedicineType.class));
        }
    }
}
package com.company.msclinic.controller;

import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.service.MedicineTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.msclinic.constant.Url.MEDICINE_TYPE;

@RestController
@RequestMapping(value = MEDICINE_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineTypeController {
    private final MedicineTypeService medicineTypeService;

    public MedicineTypeController(MedicineTypeService medicineTypeService) {
        this.medicineTypeService = medicineTypeService;
    }

    @PostMapping
    public ResponseEntity<String> insertMedicineType(@RequestBody InsertMedicineTypeRequestDTO request) {
        try {
            medicineTypeService.insertMedicineType(request);

            return new ResponseEntity<>("Input success", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();

            return new ResponseEntity<>("Input fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

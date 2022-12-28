package com.company.msclinic.controller;

import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.service.MedicineTypeService;
import com.company.msclinic.service.ValidationService;
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
  private final ValidationService validationService;

  public MedicineTypeController(MedicineTypeService medicineTypeService, ValidationService validationService) {
    this.medicineTypeService = medicineTypeService;
    this.validationService = validationService;
  }

  @PostMapping
  public ResponseEntity<String> insertMedicineType(@RequestBody InsertMedicineTypeRequestDTO request) {
    validationService.validate(request);

    medicineTypeService.insertMedicineType(request);

    return new ResponseEntity<>("Input success", HttpStatus.OK);
  }
}

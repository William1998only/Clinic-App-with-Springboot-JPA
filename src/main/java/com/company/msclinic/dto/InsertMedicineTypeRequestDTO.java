package com.company.msclinic.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class InsertMedicineTypeRequestDTO {
  @NotBlank
  @Size(max = 20)
  private String medicineType;

  @NotNull
  private Integer medicineCodeType;
}

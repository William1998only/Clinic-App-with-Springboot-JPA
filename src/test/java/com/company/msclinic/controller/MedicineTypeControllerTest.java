package com.company.msclinic.controller;

import com.company.msclinic.constant.Url;
import com.company.msclinic.controller.abstraction.AbstractControllerTest;
import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.service.MedicineTypeService;
import com.company.msclinic.service.ValidationService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Medicine Type Controller Test")
@WebMvcTest(controllers = MedicineTypeController.class)
class MedicineTypeControllerTest extends AbstractControllerTest {
  @MockBean
  private MedicineTypeService medicineTypeService;

  @MockBean
  private ValidationService validationService;

  @Nested
  @DisplayName("Insert Medicine Type Test Case")
  class InsertMedicineTypeTestCase {
    @Test
    @SneakyThrows
    @DisplayName("Expect to return input success when save is success")
    void success() {
      InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
      request.setMedicineType("sirup");
      request.setMedicineCodeType(1);
      doNothing().when(validationService).validate(request);
      doNothing().when(medicineTypeService).insertMedicineType(request);

      MvcResult mvcResult = mockMvc.perform(
          post(Url.MEDICINE_TYPE)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

      assertThat(mvcResult.getResponse().getContentAsString(), is("Input success"));
    }

    @Test
    @SneakyThrows
    @DisplayName("Expect to return input fail when save is fail")
    void fail() {
      InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
      request.setMedicineType("sirup");
      request.setMedicineCodeType(1);
      doNothing().when(validationService).validate(request);
      doThrow(new RuntimeException()).when(medicineTypeService).insertMedicineType(request);

      mockMvc.perform(
          post(Url.MEDICINE_TYPE)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());
    }

    @SneakyThrows
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Expect to return status 400 when medicineType is null or empty")
    void fail_medicine_type_null_or_empty(String medicineType) {
      InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
      request.setMedicineType(medicineType);
      request.setMedicineCodeType(1);
      doThrow(new ConstraintViolationException("medicineType: must not be blank", null)).when(validationService).validate(request);

      MvcResult mvcResult = mockMvc.perform(
          post(Url.MEDICINE_TYPE)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andReturn();

      assertThat(mvcResult.getResponse().getContentAsString(), is("medicineType: must not be blank"));
    }

    @Test
    @SneakyThrows
    @DisplayName("Expect to return status 400 when medicineCodeType is null")
    void fail_medicine_code_type_null() {
      InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
      request.setMedicineType("cyrup");
      request.setMedicineCodeType(null);
      doThrow(new ConstraintViolationException("medicineCodeType: must not be null", null)).when(validationService).validate(request);

      MvcResult mvcResult = mockMvc.perform(
          post(Url.MEDICINE_TYPE)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andReturn();

      assertThat(mvcResult.getResponse().getContentAsString(), is("medicineCodeType: must not be null"));
    }

    @Test
    @SneakyThrows
    @DisplayName("Expect to return status 400 when medicineType size is more than 20")
    void fail_medicine_type_size_more_than_20() {
      InsertMedicineTypeRequestDTO request = new InsertMedicineTypeRequestDTO();
      request.setMedicineType("ksoakosakoskaoksoakoaskosakokasoksaokak");
      request.setMedicineCodeType(null);
      doThrow(new ConstraintViolationException("medicineType: size must be between 0 and 20", null)).when(validationService).validate(request);

      MvcResult mvcResult = mockMvc.perform(
          post(Url.MEDICINE_TYPE)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andReturn();

      assertThat(mvcResult.getResponse().getContentAsString(), is("medicineType: size must be between 0 and 20"));
    }
  }
}
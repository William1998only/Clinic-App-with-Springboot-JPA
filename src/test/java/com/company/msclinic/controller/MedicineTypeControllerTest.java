package com.company.msclinic.controller;

import com.company.msclinic.constant.Url;
import com.company.msclinic.controller.abstraction.AbstractControllerTest;
import com.company.msclinic.dto.InsertMedicineTypeRequestDTO;
import com.company.msclinic.service.MedicineTypeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

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
            doThrow(new RuntimeException()).when(medicineTypeService).insertMedicineType(request);

            MvcResult mvcResult = mockMvc.perform(
                            post(Url.MEDICINE_TYPE)
                                    .content(objectMapper.writeValueAsString(request))
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString(), is("Input fail"));
        }
    }
}
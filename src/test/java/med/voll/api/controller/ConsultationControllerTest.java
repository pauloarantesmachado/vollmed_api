package med.voll.api.controller;

import med.voll.api.domain.consultation.AppointmentSchedule;
import med.voll.api.domain.consultation.DataSchedule;
import med.voll.api.domain.consultation.DataScheduleDetails;
import med.voll.api.domain.doctor.EnumMedicalSpecialty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ConsultationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataSchedule> dataScheduleJacksonTester;

    @Autowired
    private JacksonTester<DataScheduleDetails> dataScheduleDetailsJacksonTester;
    @Mock
    private AppointmentSchedule schedule;

    @Test
    @DisplayName("should return code http 400 when data is invalid")
    void scheduleScenario1() throws Exception {

       var response = mvc.perform(post("/consultation"))
                    .andReturn()
                    .getResponse();
       Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

//    @Test
//    @DisplayName("should return code http 200 when data is valid")
//    void scheduleScenario2() throws Exception {
//        var date = LocalDateTime.now().plusHours(1);
//        var specialty = EnumMedicalSpecialty.GINECOLOGIA;
//        when(schedule.schedule(any())).thenReturn(new DataScheduleDetails(null, 2L, 1L, date));
//
//        var response = mvc.perform(
//                post("/consultation")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(dataScheduleJacksonTester.write(
//                                new DataSchedule(2L, 1L, date, specialty )
//                        ).getJson())
//                )
//                .andReturn()
//                .getResponse();
//        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//
//        var response_json = dataScheduleDetailsJacksonTester.write(
//                new DataScheduleDetails(null, 2L, 1L, date)
//        ).getJson();
//
//        Assertions.assertThat(response.getContentAsString()).isEqualTo(response_json);
//    }
}
package com.bahnofkaplan.rest.controller;

import com.bahnofkaplan.rest.data.entity.OperationOffice;
import com.bahnofkaplan.rest.service.OperationOfficeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {OperationOfficeController.class})
@WebMvcTest(excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class OperationOfficeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationOfficeService operationOfficeService;


    @Test
    void givenOperationOffice_whenGetAllOffices_thenReturnOperationOfficesList_Test
            () throws Exception {

        OperationOffice operationOffice = new OperationOffice().setCode("DB1").setName("Test").setShortName("T").setTyp("typ");
        OperationOffice operationOffice2 = new OperationOffice().setCode("DB2").setName("Test2").setShortName("T2").setTyp("typ2");
        List<OperationOffice> operationOffices = new ArrayList<>();

        operationOffices.add(operationOffice);
        operationOffices.add(operationOffice2);

        given(operationOfficeService.getOperationOffices())
                .willReturn(operationOffices);

        ResultActions perform = mockMvc.perform(get("/api/betriebsstelle/get"));


        perform.andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.size()", is(operationOffices.size())));


    }


}
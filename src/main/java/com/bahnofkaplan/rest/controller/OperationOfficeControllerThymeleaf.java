package com.bahnofkaplan.rest.controller;

import com.bahnofkaplan.rest.dto.OperationOfficeDTO;
import com.bahnofkaplan.rest.service.IOperationOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class OperationOfficeControllerThymeleaf {

    private final IOperationOfficeService iOperationOfficeService;

    public OperationOfficeControllerThymeleaf(IOperationOfficeService iOperationOfficeService) {
        this.iOperationOfficeService = iOperationOfficeService;
    }

    @RequestMapping("/search")
    public String getOperationOffice(Model model

      ,  @RequestParam(value = "code",defaultValue = "aamp") String code
            ) throws IOException {
        String mycode=code;

        OperationOfficeDTO operationOfficeDTO=iOperationOfficeService.getOperationOfficeByCodeFromCollection(mycode);
        model.addAttribute("list",operationOfficeDTO);
        return "index";
    }
}

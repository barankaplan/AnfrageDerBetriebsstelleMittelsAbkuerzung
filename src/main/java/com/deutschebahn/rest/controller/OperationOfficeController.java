package com.deutschebahn.rest.controller;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.deutschebahn.rest.dto.OperationOfficeDTO;
import com.deutschebahn.rest.service.IOperationOfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/betriebsstelle")
@Validated
public class OperationOfficeController {
    private final IOperationOfficeService iOperationOfficeService;


    public OperationOfficeController(IOperationOfficeService iOperationOfficeService) {
        this.iOperationOfficeService = iOperationOfficeService;
    }

    @GetMapping("{code}")
    public ResponseEntity<OperationOfficeDTO> getPostById(@PathVariable(name = "code")
                                                          @NotBlank(message = "Die Abkürzung kann nicht leer sein!")
                                                          @Size(max = 6, min = 2, message = "Die Abkürzung muss mindestens 2 Zeichen und maximal 6 Zeichen enthalten!")
                                                          @Pattern(regexp = "^[^0-9][a-zA-Z0-9 ]*")
                                                          @Valid String code) throws IOException {
        return ResponseEntity.ok(iOperationOfficeService.getOperationOfficeByCode(code.trim()));
    }

    @PreAuthorize("hasRole('DEUTSCHEBAHN')")
    @GetMapping("/secured/{code}")
    public ResponseEntity<OperationOfficeDTO> getPostByIdSecure(@PathVariable(name = "code")
                                                                @NotBlank(message = "Die Abkürzung kann nicht leer sein!")
                                                                @Size(max = 6, min = 2, message = "Die Abkürzung muss mindestens 2 Zeichen und maximal 6 Zeichen enthalten!")
                                                                @Pattern(regexp = "^[^0-9][a-zA-Z0-9 ]*")
                                                                @Valid String code) throws IOException {
        return ResponseEntity.ok(iOperationOfficeService.getOperationOfficeByCodeAdmin(code.trim()));
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationOffice> getOperationOffices() {
        return iOperationOfficeService.getOperationOffices();
    }
}

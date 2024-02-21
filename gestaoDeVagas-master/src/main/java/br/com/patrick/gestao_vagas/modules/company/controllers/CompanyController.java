package br.com.patrick.gestao_vagas.modules.company.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.patrick.gestao_vagas.modules.UseCases.CreateCompanyUseCase;
import br.com.patrick.gestao_vagas.modules.company.entities.CompanyEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    private final CreateCompanyUseCase createCompanyUseCase;


    CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
}


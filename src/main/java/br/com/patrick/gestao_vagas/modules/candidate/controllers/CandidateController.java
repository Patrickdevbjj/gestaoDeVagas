package br.com.patrick.gestao_vagas.modules.candidate.controllers;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.patrick.gestao_vagas.exceptions.UserFoundException;
import br.com.patrick.gestao_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import lombok.var;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    private CandidateRepository candidateRepository;

   @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        
        


    

    
}

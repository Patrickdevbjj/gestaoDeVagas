package br.com.patrick.gestao_vagas.modules.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patrick.gestao_vagas.exceptions.UserFoundException;
import br.com.patrick.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.patrick.gestao_vagas.modules.candidate.controllers.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    
   @Autowired
    private CandidateRepository candidateRepository;



    
    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((c) -> {
            throw new UserFoundException();
        });
        return this.candidateRepository.save(candidateEntity);

    }
}

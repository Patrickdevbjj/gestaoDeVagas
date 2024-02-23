package br.com.patrick.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.patrick.gestao_vagas.exceptions.UserFoundException;
import br.com.patrick.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.patrick.gestao_vagas.modules.candidate.controllers.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    
   @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((c) -> {
            throw new UserFoundException();
        });
        
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        
        return this.candidateRepository.save(candidateEntity);

    }
}

package br.com.patrick.gestao_vagas.modules.UseCases;

import org.springframework.stereotype.Service;

import br.com.patrick.gestao_vagas.modules.company.entities.JobEntity;
import br.com.patrick.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    
    private JobRepository JobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.JobRepository.save(jobEntity);
    }
}

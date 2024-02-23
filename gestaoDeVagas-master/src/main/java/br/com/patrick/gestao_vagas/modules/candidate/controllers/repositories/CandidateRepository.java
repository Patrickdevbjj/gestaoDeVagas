package br.com.patrick.gestao_vagas.modules.candidate.controllers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.patrick.gestao_vagas.modules.candidate.CandidateEntity;
import java.util.Optional;
import java.util.List;



public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByName(String username);

}

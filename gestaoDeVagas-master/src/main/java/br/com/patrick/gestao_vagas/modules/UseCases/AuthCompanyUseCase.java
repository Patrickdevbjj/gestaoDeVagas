package br.com.patrick.gestao_vagas.modules.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.patrick.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.patrick.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public void execute(AuthCompanyDTO authCompanyDTO) {

        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
            
        });
        
    }

}

package br.com.patrick.gestao_vagas.modules.UseCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.patrick.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.patrick.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.var;

@Service
public class AuthCompanyUseCase {
   
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    

    // Verificar a senha são iguais
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    // Se não for igual -> Erro
    if (!passwordMatches) {
        throw new AuthenticationException();
    }
    }
}

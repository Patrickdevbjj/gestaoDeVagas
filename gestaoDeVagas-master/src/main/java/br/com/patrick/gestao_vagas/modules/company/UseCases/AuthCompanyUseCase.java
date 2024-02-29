package br.com.patrick.gestao_vagas.modules.company.UseCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.patrick.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.patrick.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.patrick.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.var;

@Service
public class AuthCompanyUseCase {
   
    @Value("${security.token.secret}")
    private String secretKey;

    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    

        // Verificar a senha são iguais
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se não for igual -> Erro
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
       
        // se for igual > gerar o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        
        var token  =  JWT.create().withIssuer("Javagas").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(company.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);

        var AuthCompanyResponseDTO = AuthCompanyResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();
        return AuthCompanyResponseDTO;
        
    }
}

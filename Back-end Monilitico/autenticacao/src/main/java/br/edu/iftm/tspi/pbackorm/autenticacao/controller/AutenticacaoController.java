package br.edu.iftm.tspi.pbackorm.autenticacao.controller;

import br.edu.iftm.tspi.pbackorm.autenticacao.dto.CredenciaisDTO;
import br.edu.iftm.tspi.pbackorm.autenticacao.dto.TokenDTO;
import br.edu.iftm.tspi.pbackorm.autenticacao.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/autenticacao")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody CredenciaisDTO credenciais) {
        // O Spring Security já autenticou via Basic Auth, então pegamos o Authentication do contexto
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}

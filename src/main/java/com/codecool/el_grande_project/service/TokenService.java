package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.DTO.TokenDTO;
import com.codecool.el_grande_project.entity.Token;
import com.codecool.el_grande_project.mappers.TokenMapper;
import com.codecool.el_grande_project.repository.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    public TokenService(TokenRepository tokenRepository, TokenMapper tokenMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenMapper = tokenMapper;
    }

    public void addTokenToBlackList(TokenDTO token) {
        Token tokenEntity = tokenMapper.mapDtoToToken(token);
        tokenRepository.save(tokenEntity);

    }
    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void deleteExpiredTokens(){
        List<Token> expiredTokens = tokenRepository.findExpiredTokens();
        if (expiredTokens.size()>0){
            tokenRepository.deleteAll(expiredTokens);
        }
    }

}

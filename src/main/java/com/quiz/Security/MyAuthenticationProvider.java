package com.quiz.Security;

import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.Entity.UserEntity;
import com.quiz.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        if(email == null || email.isBlank())
            throw new DataInvalidException("Email or password is invalid!");
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataInvalidException("Email or password is invalid!"));
        if(!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Email or password is invalid!");
        }
        if(!user.isAccountNonLocked()) {
            throw new DataInvalidException("Account is lock!");
        }
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(email, user.getPassword(), user.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

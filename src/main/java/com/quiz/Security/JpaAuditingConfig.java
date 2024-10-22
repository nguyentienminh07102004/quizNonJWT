package com.quiz.Security;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@EnableJpaAuditing(auditorAwareRef = "auditingConfig")
@Configuration
public class JpaAuditingConfig implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) return Optional.empty();
        Object principal = authentication.getPrincipal();
        if(principal instanceof String) {
            return Optional.of(principal.toString());
        } else {
            UserDetails userDetails = (UserDetails) principal;
            return Optional.of(userDetails.getUsername());
        }
    }

    @Bean
    public JpaAuditingConfig auditingConfig() {
        return new JpaAuditingConfig();
    }
}

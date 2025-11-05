package br.com.fingestio.api.dto.card;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateRequest {

    @NotBlank(message = "O emissor (issuer) é obrigatório.")
    private String issuer;

    @NotBlank(message = "Os últimos 4 dígitos são obrigatórios.")
    @Size(min = 4, max = 4, message = "O campo 'lastFourDigits' deve ter exatamente 4 dígitos.")
    private String lastFourDigits;

    @NotBlank(message = "O apelido (alias) é obrigatório.")
    private String alias;

    private String shared;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long userId;

    // Getters e Setters
    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    public String getLastFourDigits() { return lastFourDigits; }
    public void setLastFourDigits(String lastFourDigits) { this.lastFourDigits = lastFourDigits; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getShared() { return shared; }
    public void setShared(String shared) { this.shared = shared; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
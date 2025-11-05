package br.com.fingestio.api.service;

import br.com.fingestio.api.dto.card.CreateRequest;
import br.com.fingestio.api.dto.card.UpdateRequest;
import br.com.fingestio.api.model.Card;

import java.util.List;

public interface CardService {

    List<Card> getCardsByUserId(Long userId);

    Card create(CreateRequest createRequest);

    Card update(Long id, UpdateRequest updateRequest);

    void delete(Long id);
}
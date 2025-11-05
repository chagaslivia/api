package br.com.fingestio.api.repository;

import br.com.fingestio.api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByUser_Id(Long userId);

}
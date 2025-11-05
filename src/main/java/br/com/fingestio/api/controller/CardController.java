package br.com.fingestio.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fingestio.api.service.CardService;
import br.com.fingestio.api.dto.card.CreateRequest; // DTO específico do Card
import br.com.fingestio.api.dto.card.UpdateRequest; // DTO específico do Card
import br.com.fingestio.api.model.Card;
import br.com.fingestio.api.utils.ApiResponse;
import br.com.fingestio.api.utils.ErrorCode;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * Busca todos os cartões de um usuário específico.
     */
    @GetMapping("/all/{user_id}")
    public ResponseEntity<ApiResponse<List<Card>>> getCardsByUserId(@PathVariable Long user_id) {
        try {
            List<Card> cards = cardService.getCardsByUserId(user_id);
            ApiResponse<List<Card>> response = ApiResponse.success("Cartões encontrados com sucesso", cards);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            ApiResponse<List<Card>> response = ApiResponse.error(e.getMessage(), errorCode);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Cria um novo cartão.
     * O user_id deve estar dentro do DTO CreateRequest.
     */
    @PostMapping("")
    public ResponseEntity<ApiResponse<Card>> create(@Valid @RequestBody CreateRequest createRequest) {
        try {
            Card card = cardService.create(createRequest);
            ApiResponse<Card> response = ApiResponse.success("Cartão criado com sucesso", card);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            ApiResponse<Card> response = ApiResponse.error(e.getMessage(), errorCode);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Atualiza um cartão existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Card>> update(@PathVariable Long id, @Valid @RequestBody UpdateRequest updateRequest) {
        try {
            Card card = cardService.update(id, updateRequest);
            ApiResponse<Card> response = ApiResponse.success("Cartão atualizado com sucesso", card);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorCode errorCode;
            if (e.getMessage().contains("não encontrado")) {
                errorCode = ErrorCode.NOT_FOUND;
            } else {
                errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            }
            // Seguindo o padrão do CategoryController (retornando BAD_REQUEST)
            ApiResponse<Card> response = ApiResponse.error(e.getMessage(), errorCode);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Exclui um cartão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try {
            cardService.delete(id);
            ApiResponse<String> response = ApiResponse.success("Cartão excluído com sucesso", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorCode errorCode;
            if (e.getMessage().contains("não encontrado")) {
                errorCode = ErrorCode.NOT_FOUND;
            } else {
                errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            }
            ApiResponse<String> response = ApiResponse.error(e.getMessage(), errorCode);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
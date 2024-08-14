package com.springacademy.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fellipe Toledo
 */
@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById() {
        CashCard cashCard = new CashCard(99L, 123.45);
        return ResponseEntity.ok(cashCard);
    }
}

package com.springacademy.cashcard;

import org.springframework.data.annotation.Id;

/**
 * @author Fellipe Toledo
 */
public record CashCard(
        @Id
        Long id,
        Double amount) {
}

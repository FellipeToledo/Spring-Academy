package com.springacademy.cashcard;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Fellipe Toledo
 */
@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;
    @Autowired
    private JacksonTester<CashCard[]> jsonList;
    private CashCard[] cashCards;
    private final ClassPathResource resource = new ClassPathResource("example/cashcard/expected.json");
    private final ClassPathResource resourceList = new ClassPathResource("example/cashcard/list.json");

    // @Test
    // void myFirstTest() {
    // assertThat(42).isEqualTo(42);
    // }

    //GET
    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45, "fellipe");
        assertThat(json.write(cashCard)).isStrictlyEqualToJson(resource);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "amount": 123.45,
                    "owner": "fellipe"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45, "fellipe"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }

    //GET List
    @Test
    void cashCardListSerializationTest() throws IOException {
        assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson(resourceList);
    }

    @Test
    void cashCardListDeserializationTest() throws IOException {
        String expected="""
         [
                     {"id": 99, "amount": 123.45 , "owner": "fellipe"},
                     {"id": 100, "amount": 1.00 , "owner": "fellipe"},
                     {"id": 101, "amount": 150.00, "owner": "fellipe"}
         ]
         """;
        assertThat(jsonList.parse(expected)).isEqualTo(cashCards);
    }

    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(
                new CashCard(99L, 123.45, "fellipe"),
                new CashCard(100L, 1.00, "fellipe"),
                new CashCard(101L, 150.00, "fellipe"));
    }
}

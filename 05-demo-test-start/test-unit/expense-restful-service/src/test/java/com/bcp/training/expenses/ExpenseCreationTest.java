package com.bcp.training.expenses;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@QuarkusTest
@TestHTTPEndpoint(ExpenseResource.class)
public class ExpenseCreationTest {

    @Test
    public void testCreateExpense() {
        // Test implementation goes here
        given()
            .body(Expense.of("Lunch", Expense.PaymentMethod.CASH, "15.50"))
            .contentType("application/json")
            .post();
        when()
            .get()
            .then()
            .statusCode(200)
            .body("size()", org.hamcrest.CoreMatchers.is(1))
            .body("[0].name", org.hamcrest.CoreMatchers.is("Lunch"))
            .body("[0].paymentMethod", org.hamcrest.CoreMatchers.is(Expense.PaymentMethod.CASH.name()))
            .body("[0].amount", org.hamcrest.CoreMatchers.is(15.50F));
    }
}
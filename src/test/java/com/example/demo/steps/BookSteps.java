/**
 * @author  Ruvini Ramawickrama
 */
package com.example.demo.steps;

import com.example.demo.model.Book;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class BookSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Book> response;

    @When("the Get Book endpoint is invoked with book ID {int}")
    public void theGetBookEndpointIsInvokedWithBookId(int bookId) {
        String url = String.format("http://localhost:8080/book/%s", bookId);
        response = this.restTemplate.getForEntity(url, Book.class);
    }

    @Then("the status code of {int} is returned")
    public void theStatusCodeIsReturned(int statusCode) {
        assertEquals(HttpStatusCode.valueOf(statusCode), response.getStatusCode());
    }

    @And("the book details are returned")
    public void theBookDetailsAreReturned() {
        assertNotNull(response.getBody());
    }

    @And("no book details are returned")
    public void noBookDetailsAreReturned() {
        assertNull(response.getBody());
    }

}

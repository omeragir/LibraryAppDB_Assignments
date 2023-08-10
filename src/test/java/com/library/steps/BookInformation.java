package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;


public class BookInformation {
    //US04-05

    BookPage bookPage = new BookPage();

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        bookPage.search.sendKeys(bookName);
        BrowserUtil.waitFor(3);
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook("Slow Book").click();
        BrowserUtil.waitFor(3);
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualIsbnNumber = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        DB_Util.runQuery("select * from books\n" +
                "where name='Slow Book'");
        Map<String, String> bookInfo = DB_Util.getRowMap(1);
        String expectedBookName = bookInfo.get("name");
        String expectedIsbnNumber = bookInfo.get("isbn");
        String expectedAuthorName = bookInfo.get("author");
        String expectedYear = bookInfo.get("year");
        String expectedDesc = bookInfo.get("description");

        Assert.assertEquals(actualBookName, expectedBookName);
        Assert.assertEquals(actualAuthorName, expectedAuthorName);
        Assert.assertEquals(actualIsbnNumber, expectedIsbnNumber);
        Assert.assertEquals(actualYear, expectedYear);
        Assert.assertEquals(bookPage.description.getAttribute("value"), bookInfo.get("description"));


    }

}

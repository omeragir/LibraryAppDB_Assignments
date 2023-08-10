package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class BookNameMatchStepDefs {
    //US06

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String enterBookName) {

        bookPage.bookName.sendKeys(enterBookName);

    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String enterIsbn) {
        bookPage.isbn.sendKeys(enterIsbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String enterYear) {
        bookPage.year.sendKeys(enterYear);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String enterAuthor) {
        bookPage.author.sendKeys(enterAuthor);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String enterBookCategory) {
        Select select = new Select(bookPage.categoryDropdown);
        select.selectByVisibleText(enterBookCategory);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitFor(1);
        bookPage.saveChanges.submit();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {
        // You can verify message itself too both works
        //OPT 1
        String actualMessage = bookPage.toastMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);

        //OPT 2
        Assert.assertTrue(bookPage.toastMessage.isDisplayed());
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

        String query = "select name, author, isbn from books\n" +
                "where name = '"+expectedBookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String actualBookName = rowMap.get("name");

        Assert.assertEquals(expectedBookName,actualBookName);



    }

}

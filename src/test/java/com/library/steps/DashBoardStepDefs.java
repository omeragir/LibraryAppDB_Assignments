package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class DashBoardStepDefs {

    // US02
    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    String actualBorrowedBookNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {

        loginPage.login(userType);

    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        BrowserUtil.waitFor(2);

        actualBorrowedBookNumber=bookPage.borrowedBookNumber.getText();

        System.out.println("actualBorrowedBookNumber = " + actualBorrowedBookNumber);


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        BrowserUtil.waitFor(2);
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");

        String expectedBorrowedBookNumber=DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedBorrowedBookNumber = " + expectedBorrowedBookNumber);

        Assert.assertEquals(expectedBorrowedBookNumber,actualBorrowedBookNumber);



    }
}

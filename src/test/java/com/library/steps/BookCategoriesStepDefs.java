package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class BookCategoriesStepDefs {
    // Us03

    DashBoardPage dashBoardPage = new DashBoardPage();
    BookPage bookPage=new BookPage();
    List<String> expectedAllBooksCategories;
    List<String> allBooksCategories;

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        dashBoardPage.navigateModule(moduleName);


    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        allBooksCategories=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        allBooksCategories.remove(0);
        System.out.println("allBooksCategories = " + allBooksCategories);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select  name from book_categories");
        expectedAllBooksCategories=DB_Util.getColumnDataAsList(1);
        System.out.println("expectedAllBooksCategories = " + expectedAllBooksCategories);
        Assert.assertEquals(allBooksCategories,expectedAllBooksCategories);

    }

}

package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;


public class BooksGenreStepDefs {

    //US05
    String actualBookGenre;

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {

        DB_Util.runQuery("select bc.name, count(*)\n" +
                "from book_borrow\n" +
                "         inner join books b on book_borrow.book_id = b.id\n" +
                "         inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by name\n" +
                "order by 2 desc;");


        actualBookGenre = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualBookGenre = " + actualBookGenre);

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedBookGenre) {

        System.out.println("expectedBookGenre = " + expectedBookGenre);
        Assert.assertEquals(actualBookGenre, expectedBookGenre);
    }


}

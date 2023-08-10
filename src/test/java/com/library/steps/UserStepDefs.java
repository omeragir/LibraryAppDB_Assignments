package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;


public class UserStepDefs {

    //US01

    String actualUserCount;
    String expectedUserCount;
    List<String> actualAllColumnsData;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // DB_Util.createConnection();
        System.out.println("Connection will be created hook");


    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select count(id) from users");
        actualUserCount = DB_Util.getFirstRowFirstColumn();


    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        DB_Util.runQuery("select count(distinct id) from users");
        DB_Util.getFirstRowFirstColumn();
        expectedUserCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(actualUserCount, expectedUserCount);

        //close conn
        // DB_Util.destroy();
        System.out.println("Connection will be created hook");

    }


    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
        actualAllColumnsData= DB_Util.getAllColumnNamesAsList();
        System.out.println(actualAllColumnsData);
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedAllColumnsData) {
        System.out.println(actualAllColumnsData);
        Assert.assertEquals(expectedAllColumnsData,actualAllColumnsData);


    }


}

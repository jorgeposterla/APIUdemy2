import org.testng.annotations.Test
import org.testng.Assert
import io.restassured.response.Response

import static io.restassured.RestAssured.*
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaClasspath

class Books extends Base {

    @Test(groups = "smoke")
    void getBooksList(){
        Response response = get("/books")

        ArrayList<String> allBooks = response.path("data.title")
        Assert.assertTrue(allBooks.size() >= 1, "No books returned")
    }

    @Test
    void booksSchemaIsValid(){
        get("/books")
        .then()
        .assertThat()
        .body(matchesJsonSchemaClasspath("booksSchema.json"))
    }

    @Test(groups = "smoke")
    void createAndDeletebook(){
        File bookFile = new File(getClass().getResource("/book.json").toURI())

        Response createResponse = 
        given()
                .body(bookFile)
                .when()
                .post("/books")

        String responseID = createResponse.jsonPath().getString("post.book_id")

        Assert.assertEquals(createResponse.getStatusCode(), 201)

        Response deleteResponse =
        given()
                .body("{\n" +
                                "\t\"book_id\": " + responseID + "\n" +
                                "}")
                        .when()
                        .delete("/books")

        Assert.assertEquals(deleteResponse.getStatusCode(), 200)
        Assert.assertEquals(deleteResponse.jsonPath().getString("message"), "Book successfully deleted")
    }

    @Test(groups = "smoke")
    void deleteNonExistentBook_FailMessage(){
        String nonExistentBookID = "456123"

        Response deleteResponse =
        given()
                .body("{\n" +
                                "\t\"book_id\": " + nonExistentBookID + "\n" +
                                "}")
                        .when()
                        .delete("/books")

        Assert.assertEquals(deleteResponse.getStatusCode(), 500)
        Assert.assertEquals(deleteResponse.jsonPath().getString("error"), "Unable to find book id: " + nonExistentBookID)        
    }
}
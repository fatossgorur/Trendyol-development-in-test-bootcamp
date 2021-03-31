import com.google.common.io.Resources;
import extensions.RetryAnalyzer;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;

import static io.restassured.RestAssured.given;

public class EmployeeApiTest {
    private void createEmployee(long id) throws IOException {
        URL employeeFile = Resources.getResource("employee.json");
        String employeeJson = Resources.toString(employeeFile, Charset.defaultCharset());
        JSONObject employeeObject = new JSONObject(employeeJson);

        long employeeId = id;
        String name = "test" + String.valueOf(employeeId);

        employeeObject.getJSONObject("data").put("name", name);
        employeeObject.getJSONObject("data").put("id", employeeId);

        given() // create employee
                .contentType("application/json")
                .body(employeeObject.toString())
        .when()
                .post("/create")
        .then()
                .statusCode(200);
    }

    private void checkEmployee(long id) throws IOException {
        given()
                .contentType("application/json")
        .when()
                .get("/employee/{id}", id)
        .then()
                .statusCode(200);
    }

    private void updateEmployee(long id) throws IOException {
        URL employeeFile = Resources.getResource("employee.json");
        String employeeJson = Resources.toString(employeeFile, Charset.defaultCharset());
        JSONObject employeeObject = new JSONObject(employeeJson);

        long employeeId = id;

        employeeObject.getJSONObject("data").put("name", "fatos");
        employeeObject.getJSONObject("data").put("salary", "8000");
        employeeObject.getJSONObject("data").put("age", "23");
        employeeObject.getJSONObject("data").put("id", employeeId);

        given()
                .contentType("application/json")
                .body(employeeObject.toString())
        .when()
                .put("/update/{id}", employeeId)
        .then()
                .statusCode(200);

    }

    private void deleteEmployee(long id) throws IOException {
        given()
                .contentType("application/json")
        .when()
                .delete("/delete/{id}", id)
        .then()
                .statusCode(200);
    }

    private void validateEmployeeDeletion(long id) throws IOException {
        given()
                .contentType("application/json")
        .when()
                .get("/employee/{id}", id)
        .then()
                .statusCode(429);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void employeeJourney() throws IOException {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long id = timestamp.getTime();

        createEmployee(id);
        checkEmployee(id);
        updateEmployee(id);
        deleteEmployee(id);
        //validateEmployeeDeletion(id);

    }

}

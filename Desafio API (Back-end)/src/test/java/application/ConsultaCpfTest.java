package application;
import org.junit.Test;
import org.junit.Assert;
import util.TestConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import util.ExtentManager;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;


public class ConsultaCpfTest {
    String responseBody ;
    private ExtentReports extent;
    private ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentManager.getInstance();
    }

    @After
    public void tearDown() {
        extent.flush();
    }


    @Test
    //ID do caso de teste: 1A
    public void testConsultaCpfRestritoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Teste CPF Restrito",
                "Teste para verificar se o CPF é restrito, Caso seja deve ser retornado status 200");

        test.log(Status.INFO, "Iniciando o teste");
        String cpf = "84809766080";
        try {

            responseBody = given()
                .baseUri(TestConfig.BASE_URL)
                .port(TestConfig.APP_PORT)
                .basePath(TestConfig.basePath)
                .contentType(TestConfig.APP_CONTENT_TYPE)
        .when()
                .get("/restricoes/" + cpf)
        .then()
                .statusCode(200)
                    .log().all()
                    .extract()
                    .body()
                    .asString();

            ;


        test.log(Status.INFO, "Status 200 foi encontrado para o CPF: " + cpf);
        test.info("Resposta da requisicao:\n" + responseBody);
        test.log(Status.PASS, "Teste Bem Sucedido");
        } catch (AssertionError e) {
            test.log(Status.INFO, "Status 200 Não foi encontrado para o CPF: " + cpf);
            test.info("Resposta da requisição:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou " );
            throw e;
        }





    }
    //ID do caso de teste: 2
    @Test
    public void testConsultaCpfSemRestriçãoEntaoObtenhoStatusCode204() {
        test = extent.createTest("Teste CPF Sem Restricao",
                "Teste para verificar CPF Sem Restricao, Caso seja deve ser retornado status 204");

        test.log(Status.INFO, "Iniciando o teste");
        String cpf = "12312312312";


        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .when()
                    .get("/restricoes/" + cpf)
                    .then()
                    .statusCode(204)
                    .log().all()
                    .extract()
                    .body()
                    .asString();; // Isso imprimirá a resposta completa no terminal
            test.log(Status.INFO, "Status 204 foi encontrado para o CPF: " + cpf);
            test.info("Resposta da requisição:\n" + responseBody);
            test.log(Status.PASS, "Teste Bem Sucedido");
        } catch (AssertionError e) {
            test.log(Status.INFO, "Status 204 Não foi encontrado para o CPF: " + cpf);
            test.info("Resposta da requisição:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou");
            throw e;
        }
    }
}

package application;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.Usuario;
import util.ExtentManager;
import util.TestConfig;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import pojo.BodyBaseTests;

import static io.restassured.RestAssured.*;

public class SimulaçãoTest {
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
    //ID do caso de teste: 3
    @Test
    public void testConsultaSimulaçõesExistentesLogoRetornaStatus200() {
        test = extent.createTest("Consulta Simulacoes Existentes",
                "Teste para verificar as simulacoes existentes, caso exista retorna o status 200");
        test.log(Status.INFO, "Iniciando o teste");
        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .when()
                    .get("/simulacoes")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 200 foi encontrado");
            test.info("Resposta da requisição:\n" + responseBody);

            test.log(Status.PASS, "Teste Bem Sucedido");// Isso imprimirá a resposta completa no terminal
        }catch (AssertionError e) {
            test.log(Status.INFO, "Status 200 Não foi encontrado ");
            test.info("Resposta da requisicao:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou: ");
            throw e;
        }

    }
    @Test
    //ID do caso de teste: 4
    public void testConsultaSimulaçõesCpfCasoExistaLogoRetorna200() {
        test = extent.createTest("Consulta Simulacao por CPF",
                "Teste para verificar se existentes simulacao para o CPF, caso exista retorna o status 200");
        test.log(Status.INFO, "Iniciando o teste");
        String cpf = "17822386034";

        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .when()
                    .get("/simulacoes/" + cpf)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 200 foi encontrado");
            test.info("Resposta da requisicao:\n" + responseBody);

            test.log(Status.PASS, "Teste Bem Sucedido");

        } catch (AssertionError e) {
            test.log(Status.INFO, "Status 200 Não foi encontrado ");
            test.info("Resposta da requisicao:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou " );
            throw e;
        }
    }
    @Test
    //ID do caso de teste: 4
    public void testConsultaSimulaçõesCpfCasoNãoExistaLogoRetorna404() {
        test = extent.createTest("Teste Consulta CPF que não existe nas simulacoes",
"Teste para verificar um CPF que não existente nas simulações, caso Realmente não exista retorna o status 404");
        test.log(Status.INFO, "Iniciando o teste");
        
        String cpf = "12345678999";


        try {

            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .when()
                    .get("/simulacoes/" + cpf)
                    .then()
                    .statusCode(404)
                    .log().all()
                    .extract()
                    .body()
                    .asString();

            test.log(Status.INFO, "Status 404 foi encontrado");
            test.info("Resposta da requisicao:\n" + responseBody);


            test.log(Status.PASS, "Teste Bem Sucedido");

        } catch (AssertionError e) {
            test.log(Status.INFO, "Status 404 Não foi encontrado ");
            test.info("Resposta da requisicao:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou " );
            throw e;
        }
    }
    //ID do caso de teste: 5
    @Test

    public void testAlteraSimulaçaoCasoSucessoLogoRetorna200() {
        test = extent.createTest("Teste Altera uma simulacao existente",
                "Teste para verificarse o sistema consegue alterar uma simulação existente, caso sucesso retorna status 200");
        test.log(Status.INFO, "Iniciando o teste");
        String cpf = "17822386034";
        String uri = "/simulacoes/" + cpf;

        Usuario atualizaUsuario = BodyBaseTests.AtualizaUsuario();


        try {
            responseBody = given()
                .baseUri(TestConfig.BASE_URL)
                .port(TestConfig.APP_PORT)
                .basePath(TestConfig.basePath)
                .contentType(TestConfig.APP_CONTENT_TYPE)
                .body(atualizaUsuario)
        .when()
                .put(uri)
        .then()
                .statusCode(200)
            .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 200 foi encontrado");
            test.info("Resposta da requisicao:\n" + responseBody);
            test.log(Status.PASS, "Teste Bem Sucedido");


    }catch (AssertionError e) {
            test.log(Status.INFO, "Status Não 200 foi encontrado");
            test.info("Resposta da requisicao:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou ");
            throw e;
        }
    }




    //ID do caso de teste: 6
    @Test

    public void testDeletaSimulaçaoCasoSucessoLogoRetorna204() {
        test = extent.createTest("Teste de deletar uma simulacao",
                "Teste para verificar se o sistema deleta e reposta corretamente uma delação, caso sucesso retorna status  204");
        test.log(Status.INFO, "Iniciando o teste");
        String id = "11";
        String uri = "/simulacoes/" + id;


        try {
            responseBody = given()
                .baseUri(TestConfig.BASE_URL)
                .port(TestConfig.APP_PORT)
                .basePath(TestConfig.basePath)
                .contentType(TestConfig.APP_CONTENT_TYPE)
                .when()
                .delete(uri)
                .then()
                .statusCode(204)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
                test.log(Status.INFO, "Status 204 foi encontrado");
                test.info("Resposta da requisicao:\n" + responseBody);
                test.log(Status.PASS, "Teste Bem Sucedido");


    }catch (AssertionError e) {
            test.log(Status.INFO, "Status Não 204 foi encontrado");
            test.info("Resposta da requisicao:\n" + e.getMessage());
        test.log(Status.FAIL, "Teste Falhou");
        throw e;
    }
}

    //ID do caso de teste: 7
    @Test

    public void testCriaSimulacaoDadosCorretoLogoRetorna201() {
        test = extent.createTest("Cria Simulacao Dados Validos",
            "Teste para verificar a criação de uma simulação com dados validos, caso sucesso retorna status 201");
        test.log(Status.INFO, "Iniciando o teste");

        String uri = "/simulacoes/" ;
        Usuario criaUsuarioValido = BodyBaseTests.CriaUsuarioValido();

        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .body(criaUsuarioValido)
                    .when()
                    .post(uri)
                    .then()
                    .statusCode(201)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 201 foi encontrado");
            test.info("Resposta da requisicao:\n" + responseBody);
            test.log(Status.PASS, "Teste Bem Sucedido");


        }catch (AssertionError e) {
            test.log(Status.INFO, "Status 201 não foi encontrado");
            test.info("Resposta da requisicao:\n" + e.getMessage());
            test.log(Status.FAIL, "Teste Falhou" );
            throw e;
        }
    }
    //ID do caso de teste: 8
    @Test

    public void testCriaSimulacaoCpfExistenteLogoRetorna409() {
        test = extent.createTest("Cria Simulacao CPF de um CPF existente",
            "Teste para verificar a criacao de uma simulação com um CPF que já tem cadastro, caso o teste tenha sucesso retorna status 409");
        test.log(Status.INFO, "Iniciando o teste");

        String uri = "/simulacoes/";
        Usuario criaUsuarioCpfRepetido = BodyBaseTests.CriaUsuarioCpfRepetido();


        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .body(criaUsuarioCpfRepetido)
                    .when()
                    .post(uri)
                    .then()
                    .statusCode(409)
                    .log().all()
                    .extract()
                    .body()
                    .asString();;

            test.log(Status.INFO, "Status 409 foi encontrado");

            test.info("Resposta da requisicao:\n" + responseBody);

            test.log(Status.PASS, "Teste Bem Sucedido");


        } catch (AssertionError e) {
            test.log(Status.INFO, "Status 409 não foi encontrado");
            test.info("Resposta da requisicao:\n" + e.getMessage());

            test.log(Status.FAIL, "Teste Falhou ");
            throw e;
        }
    }

        //ID do caso de teste: 9A
        @Test

        public void testCriaSimulacaoCpfValorAbaixoCondicaoLogoRetorna400() {
            test = extent.createTest("Cria Simulacao Valor Abaixo Condicao",
            "Teste para verificar a criação de uma simulacao com um valor abaixo da condicao do projeto, Caso o teste tenha sucesso, deve retornar 400");
            test.log(Status.INFO, "Iniciando o teste");

            String uri = "/simulacoes/";
            Usuario CriaUsuarioValorMenor = BodyBaseTests.CriaUsuarioValorMenor();




            try {
                responseBody = given()
                        .baseUri(TestConfig.BASE_URL)
                        .port(TestConfig.APP_PORT)
                        .basePath(TestConfig.basePath)
                        .contentType(TestConfig.APP_CONTENT_TYPE)
                        .body(CriaUsuarioValorMenor)
                        .when()
                        .post(uri)
                        .then()
                        .statusCode(400)
                        .log().all()
                        .extract()
                        .body()
                        .asString();;
                test.info("Resposta da requisicao:\n" + responseBody);

                test.log(Status.INFO, "Status 400 foi encontrado");


                test.log(Status.PASS, "Teste Bem Sucedido");


            } catch (AssertionError e) {
                test.log(Status.INFO, "Status 400 foi encontrado");
                test.info("Resposta da requisicao:\n" + e.getMessage());

                test.log(Status.FAIL, "Teste Falhou");
                throw e;
            }
        }

            //ID do caso de teste: 9B
            @Test

            public void testCriaSimulacaoCpfValorAcimaCondicaoLogoRetorna400() {
                test = extent.createTest("Cria Simulacao Valor Acima Condicao",
                        "Teste para verificar a criacao de uma simulação com um valor abaixo da condição do projeto, Caso o teste tenha sucesso, deve retornar 400");
                test.log(Status.INFO, "Iniciando o teste");

                String uri = "/simulacoes/" ;
                Usuario CriaUsuarioValorMaior = BodyBaseTests.CriaUsuarioValorMaior();

                try {
                    responseBody = given()
                            .baseUri(TestConfig.BASE_URL)
                            .port(TestConfig.APP_PORT)
                            .basePath(TestConfig.basePath)
                            .contentType(TestConfig.APP_CONTENT_TYPE)
                            .body(CriaUsuarioValorMaior)
                            .when()
                            .post(uri)
                            .then()
                            .statusCode(400)
                            .log().all()
                            .extract()
                            .body()
                            .asString();;
                    test.log(Status.INFO, "Status 400 foi encontrado");
                    test.info("Resposta da requisicao:\n" + responseBody);


                    test.log(Status.PASS, "Teste Bem Sucedido");


                }catch (AssertionError e) {
                    test.log(Status.INFO, "Status 400 não foi encontrado");
                    test.info("Resposta da requisicao:\n" + e.getMessage());

                    test.log(Status.FAIL, "Teste Falhou " );
                    throw e;
                }
}
    //ID do caso de teste: 10A
    @Test

    public void testCriaSimulacaoCpfParcelaAbaixoCondicaoLogoRetorna400() {
        test = extent.createTest("Cria Simulacao Parcela Abaixo Condicao",
                "Teste para verificar a criação de uma simulacao com a Parcela abaixo da condição do projeto, Caso o teste tenha sucesso, deve retornar 400");
        test.log(Status.INFO, "Iniciando o teste");

        String uri = "/simulacoes/" ;
        Usuario CriaUsuarioParcelaMenor = BodyBaseTests.CriaUsuarioParcelaMenor();





        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .body(CriaUsuarioParcelaMenor)
                    .when()
                    .post(uri)
                    .then()
                    .statusCode(400)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 400 foi encontrado");

            test.info("Resposta da requisicao:\n" + responseBody);

            test.log(Status.PASS, "Teste Bem Sucedido");


        }catch (AssertionError e) {
            test.log(Status.INFO, "Status 400 não foi encontrado");
            test.info("Resposta da requisicao:\n" + e.getMessage());

            test.log(Status.FAIL, "Teste Falhou " );
            throw e;
        }
    }
    //ID do caso de teste: 10B
    @Test

    public void testCriaSimulacaoCpfParcelaAcimaCondicaoLogoRetorna400() {
        test = extent.createTest("Cria Simulacao Parcela Acima Condicao",
                "Teste para verificar a criacao de uma simulacao com a Parcela Acima da condição do projeto, Caso o teste tenha sucesso, deve retornar 400");
        test.log(Status.INFO, "Iniciando o teste");

        String uri = "/simulacoes/" ;
        Usuario CriaUsuarioParcelaMaior = BodyBaseTests.CriaUsuarioParcelaMaior();



        try {
            responseBody = given()
                    .baseUri(TestConfig.BASE_URL)
                    .port(TestConfig.APP_PORT)
                    .basePath(TestConfig.basePath)
                    .contentType(TestConfig.APP_CONTENT_TYPE)
                    .body(CriaUsuarioParcelaMaior)
                    .when()
                    .post(uri)
                    .then()
                    .statusCode(400)
                    .log().all()
                    .extract()
                    .body()
                    .asString();
            test.log(Status.INFO, "Status 400 foi encontrado");

            test.info("Resposta da requisicao:\n" + responseBody);

            test.log(Status.PASS, "Teste Bem Sucedido");


        }catch (AssertionError e) {
            test.log(Status.INFO, "Status 400 não foi encontrado");

            test.info("Resposta da requisicao:\n" + e.getMessage());

            test.log(Status.FAIL, "Teste Falhou " );
            throw e;
        }
    }

    }
























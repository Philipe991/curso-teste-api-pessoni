import io.restassured.http.ContentType;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TesteCliente {

    String enderecoApi = "http://localhost:8080";
    String recursoPost = "/cliente";

    @Test
    @DisplayName("Quando obter lista de clientes sem adicionar cliente. Então a lista deve estar vazia")
    public void obterTodosClientes(){

        String resultadoEsperado = "{}";

        given().
                contentType(JSON).
        when().
                get(enderecoApi).
        then().
                statusCode(200).
                assertThat().
                body(new IsEqual(resultadoEsperado));
    }

    @Test
    @DisplayName("Quando cadastrar um cliente. Então o cliente é cadastrado com sucesso")
    public void cadastrarCliente(){
        String corpoRequisicao = "{\n"+
                " \"nome\": \"Aline\",\n" +
                " \"idade\": \"28\",\n" +
                " \"id\": \"1991\"\n" +
                '}';

        String respostaEsperada = "{\"1991\":" +
                "{\"nome\":\"Aline\"," +
                "\"idade\":28," +
                "\"id\":1991," +
                "\"risco\":0}" +
                "}";

        given().
                contentType(JSON).
                body(corpoRequisicao).
        when().
                post(enderecoApi+recursoPost).
        then().
                statusCode(201).
                body(new IsEqual<>(respostaEsperada));
    }

}

package br.com.mgobo.web.controller;

import br.com.mgobo.web.BaseIntegratedTest;
import br.com.mgobo.web.dto.ColorDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.client.HttpClientProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.mgobo.api.parsers.ParserObject.parserObject;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ColorControllerTest extends BaseIntegratedTest {

    private MockMvc mockMvc;
    private final String url = "/api/v1/color";

    @Autowired
    private ColorController colorController;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        postgreSQLContainer.stop();
    }

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(colorController).build();
    }

    @Order(1)
    @Test
    public void testCreate() throws Exception {
        ColorDto colorDto = new ColorDto(null, "VERMELHO");
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(parserObject.toJson(colorDto)));
        int status = resultActions.andReturn().getResponse().getStatus();
        try {
            assertEquals(201, status, "Sucesso na requisição");
        } catch (Exception e) {
            fail(ERROR_REQUEST.formatted(e.getMessage(), status));
        }
    }

    @Order(2)
    @Test
    public void testUpdate() throws Exception {
        ColorDto colorDto = new ColorDto(1l, "VERMELHO");
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(parserObject.toJson(colorDto)));
        int status = resultActions.andReturn().getResponse().getStatus();
        try {
            assertEquals(202, status, "Sucesso na requisição");
        } catch (Exception e) {
            fail(ERROR_REQUEST.formatted(e.getMessage(), status));
        }
    }

    @Order(3)
    @Test
    public void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(url.concat("/find/%s").formatted(1)));
        int status = resultActions.andReturn().getResponse().getStatus();
        try {
            assertEquals(true, (status == 200 || status == 404), "Sucesso na requisição");
            System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        } catch (Exception e) {
            fail(ERROR_REQUEST.formatted(e.getMessage(), status));
        }
    }

    @Order(4)
    @Test
    public void testFindAll() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(url));
        int status = resultActions.andReturn().getResponse().getStatus();
        try{
            assertEquals(200, status, "Sucesso na requisição");
            System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        }catch (Exception e){
            fail(ERROR_REQUEST.formatted(e.getMessage(), status));
        }
    }
}

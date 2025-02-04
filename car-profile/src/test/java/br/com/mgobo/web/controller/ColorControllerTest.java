package br.com.mgobo.web.controller;

import br.com.mgobo.web.BaseIntegratedTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ColorControllerTest extends BaseIntegratedTest {

    private MockMvc mockMvc;
    private final String url = "/api/v1";

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

    @Test
    public void testFindAll() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("%s/color".formatted(url)));
        int status  = resultActions.andReturn().getResponse().getStatus();
        if (status == 200) {
            assertEquals(200, status, "Sucesso na requisição");
        }else{
            fail("Erro na requisição %s".formatted(status));
        }
    }
}

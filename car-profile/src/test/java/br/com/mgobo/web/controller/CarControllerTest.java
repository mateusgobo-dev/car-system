package br.com.mgobo.web.controller;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.api.entities.BrandCategory;
import br.com.mgobo.api.entities.Category;
import br.com.mgobo.api.entities.Color;
import br.com.mgobo.api.repository.BrandCategoryRepository;
import br.com.mgobo.api.repository.BrandRepository;
import br.com.mgobo.api.repository.CategoryRepository;
import br.com.mgobo.api.repository.ColorRepository;
import br.com.mgobo.web.BaseIntegratedTest;
import br.com.mgobo.web.dto.BrandCategoryDto;
import br.com.mgobo.web.dto.BrandDto;
import br.com.mgobo.web.dto.CarDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.mgobo.api.parsers.ParserObject.parserObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest extends BaseIntegratedTest {

    private MockMvc mockMvc;
    private final String url = "/api/v1/car";
    private Brand brand;
    private Category category;
    private BrandCategory brandCategory;
    private Color color;

    @Autowired
    private CarController carController;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private BrandCategoryRepository brandCategoryRepository;

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
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        Brand brand = new Brand();
        brand.setName("GM");
        this.brand = this.brandRepository.save(brand);

        Category category = new Category();
        category.setName("SUV");
        this.category = this.categoryRepository.save(category);

        Color color = new Color();
        color.setDescription("VERMELHO");
        this.color = this.colorRepository.save(color);
    }

    private void createBrandCategory(CarDto carDto) throws Exception {
        Brand brand = new Brand();
        Category category = new Category();
        brand.setId(carDto.brandId());
        category.setId(carDto.categoryId());

        BrandCategory brandCategory = new BrandCategory();
        brandCategory.setBrand(this.brand);
        brandCategory.setCategory(this.category);
        this.brandCategory = this.brandCategoryRepository.save(brandCategory);
    }

    @Order(1)
    @Test
    public void testCreate() throws Exception {
        CarDto carDto = new CarDto(null, "EQUINOX PREMIER", "262 CV", this.color.getId(), 1l, 1l);
        this.createBrandCategory(carDto);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(parserObject.toJson(carDto)));
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
        CarDto carDto = new CarDto(1l, "EQUINOX RS", "175 CV", this.color.getId(), 1l, 1l);
        this.createBrandCategory(carDto);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(parserObject.toJson(carDto)));
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
        try {
            assertEquals(200, status, "Sucesso na requisição");
            System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        } catch (Exception e) {
            fail(ERROR_REQUEST.formatted(e.getMessage(), status));
        }
    }
}

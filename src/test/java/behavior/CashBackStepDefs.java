package behavior;

import behavior.mock.MockHttpCall;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.green.cashback.greenCashback.GreenCashBackApplication;
import com.green.cashback.greenCashback.model.Bottle;
import com.green.cashback.greenCashback.model.CashBack;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = {"classpath:application.properties"})
@WebAppConfiguration
@ContextConfiguration(classes = {GreenCashBackApplication.class})
public class CashBackStepDefs {

    private int bottles;

    @Value("${mock.port}")
    private Integer mockPort;

    @Autowired
    protected WebApplicationContext context;

    private ResultActions resultActions;

    @Autowired
    private ObjectMapper objectMapper;

    private WireMockServer wireMockServer;


    private MockMvc mockMvc;

    @PostConstruct
    private void initMockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        wireMockServer = new WireMockServer(wireMockConfig().port(mockPort));
        wireMockServer.start();
        WireMock.configureFor("localhost", mockPort);
        WireMock.reset();
    }


    @Given("I receive a request to obtain a cashback with {int} bottles")
    public void i_receive_a_request_to_obtain_a_cashback_with_bottles(final Integer bottles) throws JsonProcessingException {
        this.bottles = bottles;

        MockHttpCall.callCalculatorCashBack(wireMockServer,mockCashBack() , mockBottle());


    }


    @When("make  request to cashback machine")
    public void make_request_to_cashback_machine() throws Exception {

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cash-back/" + bottles)
                .contentType(MediaType.APPLICATION_JSON);

        resultActions = mockMvc.perform(requestBuilder);
    }

    @Then("i must return this informations")
    public void i_must_return_this_informations(final String expectedJson) throws Exception {
        resultActions
                .andExpect(status().isOk());
    }

    private String mockBottle() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new Bottle(bottles));
    }

    private String mockCashBack() throws JsonProcessingException {

        return objectMapper.writeValueAsString(new CashBack(new BigDecimal(0.64).setScale(2, RoundingMode.HALF_EVEN)));
    }

}

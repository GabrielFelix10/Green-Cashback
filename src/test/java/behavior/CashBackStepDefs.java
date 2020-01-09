package behavior;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.cashback.greenCashback.GreenCashBackApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
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


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = {"classpath:application.properties"})
@WebAppConfiguration
@ContextConfiguration(classes = GreenCashBackApplication.class)
public class CashBackStepDefs {

    private int bottles;

    @Autowired
    protected WebApplicationContext context;

    private ResultActions resultActions;

    @Autowired
    protected ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @PostConstruct
    private void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Given("I receive a request to obtain a cashback with {int} bottles")
    public void i_receive_a_request_to_obtain_a_cashback_with_bottles(final Integer int1) {
        this.bottles = bottles;
    }


    @When("make  request to cashback machine")
    public void make_request_to_cashback_machine() {

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cash-back/" + bottles)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Then("i must return this informations")
    public void i_must_return_this_informations(final String expectedJson) throws Exception {
        resultActions
                .andExpect(status().isOk());
    }


}

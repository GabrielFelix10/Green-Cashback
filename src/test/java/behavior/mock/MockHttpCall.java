package behavior.mock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.responseDefinition;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author gfaraujo
 */
public class MockHttpCall {

    public static void callCalculatorCashBack(final WireMockServer wireMockServer,final String bodyResponse, final String json) {

        wireMockServer.stubFor(post(urlPathEqualTo("/cash-back"))
                .withRequestBody(equalToJson(json))
                .willReturn(
                        responseDefinition()
                                .withBody(bodyResponse)
                                .withStatus(200)
                                .withHeader("Content-Type", APPLICATION_JSON_VALUE))
        );

    }
}

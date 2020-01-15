package com.green.cashback.greenCashback.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.cashback.greenCashback.model.Bottle;
import com.green.cashback.greenCashback.model.CashBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author gfaraujo
 */
@Component
public class CashBackHttpClient {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @Value("${greenCashbackCalculator.url}")
    private  String url;

    @Autowired
    protected ObjectMapper objectMapper;

    public CashBack obtainCashbackCalculated(final Bottle bottleParam) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(bottleParam)))
                .uri(URI.create(url+"/cash-back"))
                .timeout(Duration.of(3, SECONDS))
                .header("Content-Type", "application/json")
                .build();



        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), CashBack.class);
    }
}

package com.epam.songservice.contract;

import com.epam.songservice.BaseIntegrationTest;
import com.epam.songservice.controller.SongController;
import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass extends BaseIntegrationTest {

    @Autowired
    SongController songController;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(songController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(new EncoderConfig(UTF_8.name(), UTF_8.name()));
    }
}

package dev.misei;

import dev.misei.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestData.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProduktPilotSystemTest extends BaseContainerizedTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestData testData;

    @Autowired
    private UserRepository userRepository;


    @Test
    void simpleTokenValid() throws Exception {
        MvcResult userJoin = this.mvc.perform(post("/api/public/auth/oneTimeJoin")
                .content(testData.userJoin.getContentAsString(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        MvcResult userLogin = this.mvc.perform(get("/api/public/auth/login?userName=john_doe&password=secretpassword")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        MvcResult organizationCreation = this.mvc.perform(post("/api/private/organization/createFirstTime")
                .content(testData.organizationFirstTime.getContentAsString(StandardCharsets.UTF_8))
                .headers(addTokenFromResult(userLogin))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        MvcResult details = this.mvc.perform(get("/api/private/organization/details")
                .headers(addTokenFromResult(userLogin))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        var userDB = userRepository.findByUserNameIgnoreCase("john_doe");

        System.out.println(details);
    }

    private HttpHeaders addTokenFromResult(MvcResult mvcResult) throws Exception {
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
        return headers;
    }

}
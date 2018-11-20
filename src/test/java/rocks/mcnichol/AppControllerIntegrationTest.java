package rocks.mcnichol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;
import rocks.mcnichol.controller.AppController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppControllerIntegrationTest {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void hasRestControllerAnnotation() {
        assertTrue(AppController.class.isAnnotationPresent(RestController.class));
    }

    @Test
    public void helloReturns200() {

        ResponseEntity<String> actualResponse = testRestTemplate
                .getForEntity("/hello", String.class);

        assertThat(actualResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void logsSpanWithCustomPrefix() {
        PrintStream sysOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(baos);
        System.setOut(testOut);

        testRestTemplate.getForEntity("/hello", String.class);

        assertThat(baos.toString(), containsString("Logging message"));
        assertThat(baos.toString(), containsString("CUSTOM_PATTERN_"));

        System.setOut(sysOut);
    }
}
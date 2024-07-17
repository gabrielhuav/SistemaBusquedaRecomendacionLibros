package ovh.gabrielhuav.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ovh.gabrielhuav.LibrosApplication;

@SpringBootTest(classes = LibrosApplication.class)
@AutoConfigureMockMvc
class SecuenciasV3ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCollatz() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/sequence/collatz/19"))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Respuesta de la API Rest: " + responseContent);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseContent);
        System.out.println("Respuesta en formato JSON: " + jsonNode.toPrettyString());
    }
}


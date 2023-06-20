package com.jdms.itemy.item;

import com.jdms.itemy.item.impl.ItemService;
import com.jdms.itemy.item.model.ItemMapper;
import com.jdms.itemy.item.security.JwtAuthConverterProperties;
import com.jdms.itemy.item.security.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRestController.class)
@Import({JwtAuthConverterProperties.class, WebSecurityConfig.class})
class ItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;
    @MockBean
    private ItemMapper itemMapper;


    private static final long ID = 1;

    @Test
    @WithMockUser(roles = "ADMIN")
    void test1() throws Exception {
        given(service.findById(ID)).willReturn(defaultItem());

        ResultActions resultActions = mockMvc.perform(get(ItemRestController.ITEMS_URI + "/item/{id}", ID))
                .andDo(print());

        resultActions.andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateWithBadRequest() throws Exception {
        Item item = defaultItem();
        given(service.update(any(), any())).willReturn(item);

        ResultActions resultActions = mockMvc.perform(put(ItemRestController.ITEMS_URI + "/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                                {
                                  "name": "Tokyo",
                                  "description": "some",
                                  "year": "06.06.2023"
                                }
                                """))
                .andDo(print());

        resultActions.andExpect(status().isBadRequest());
    }

    private Item defaultItem() {
        Item item = new Item();
        item.setId(ID);
        item.setName("TEST1");
        item.setDescription("Test");
        item.setYear(LocalDate.of(2023,06,06));
        return item;
    }
}

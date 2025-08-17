package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = ControllerCategory.class)
public class ControllerCategoryTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IServiceCategory serviceCategory;

    @WithMockUser(authorities = "CREATE")
    @DisplayName(value = "Post a category should return 201 status code")
    @Test
    void givenCategoryCreateDto_ThenReturnCategoryResponseDto() throws Exception {
        Long id = 1L;
        //Create one DTO for the output
        CategoryResponseDTO outputDto = new CategoryResponseDTO(id,
                "Phones", "Description of phones", new HashSet<>());
        //Stub
        when(serviceCategory.createCategory(any(CategoryCreateDTO.class))).thenReturn(outputDto);

        //post aimed to right endpoint
        mockMvc.perform(post("/api/v1/category/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        //Request body
                        .content("{\"name\":\"Phones\",\"description\":\"Description of phones\"}")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //Should be 201 code
                .andExpect(status().isCreated()
                        //The jsonPath must be the same
                ).andExpect(jsonPath("$.idCategory").value(id))
                .andExpect(jsonPath("$.name").value(outputDto.name()))
                .andDo(print());

        verify(serviceCategory).createCategory(any(CategoryCreateDTO.class));
    }

    @WithMockUser(authorities = "CREATE")
    @DisplayName(value = "Post a batch of categories should return 201 status code")
    @Test
    void givenBatchCategoryCreateDto_ThenReturnBatchCategoryResponseDto() throws Exception {
        Long id1 = 1L;
        Long id2 = 2L;
        //Create one DTO for the output
        List<CategoryResponseDTO> outputDtos = List.of(new CategoryResponseDTO(id1,
                        "Phones", "Description of phones", new HashSet<>()),
                new CategoryResponseDTO(id2,
                        "Laptops", "Description of laptops", new HashSet<>()));
        //Stub
        when(serviceCategory.createCategory(anyList())).thenReturn(outputDtos);

        mockMvc.perform(post("/api/v1/category/createBatch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Phones\",\"description\":\"Description of phones\"}," +
                                "{\"name\":\"Laptops\",\"description\":\"Description of laptops\"}]")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isCreated()
                ).andExpect(jsonPath("$[0].idCategory").value(id1))
                .andExpect(jsonPath("$[0].name").value(outputDtos.get(0).name()))
                .andExpect(jsonPath("$[1].name").value(outputDtos.get(1).name()))
                .andDo(print());

        verify(serviceCategory).createCategory(anyList());
    }

    @WithMockUser(authorities = "READ")
    @DisplayName(value = "Return a category should return 200 status code")
    @Test
    void givenID_ThenReturnCategoryResponseDto() throws Exception {
        Long id = 1L;
        //Create one DTO for the output
        CategoryResponseDTO outputDto = new CategoryResponseDTO(id,
                "Phones", "Description of phones", new HashSet<>());
        //Stub
        when(serviceCategory.getCategoryById(anyLong())).thenReturn(outputDto);

        mockMvc.perform(get("/api/v1/category/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(outputDto.description()));

        verify(serviceCategory).getCategoryById(anyLong());
    }

    @WithMockUser(authorities = "READ")
    @DisplayName(value = "Return all categories should return 200 status code")
    @Test
    void ReturnAllCategories() throws Exception {
        Long id = 1L;
        //Create one DTO for the output
        List<CategoryResponseDTO> responseDtos = List.of(new CategoryResponseDTO(id,
                        "Phones", "Description of phones", new HashSet<>())
                , new CategoryResponseDTO(id,
                        "PC", "Description of PC", new HashSet<>()));

        //Stub
        when(serviceCategory.allCategories()).thenReturn(responseDtos);

        mockMvc.perform(get("/api/v1/category/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(responseDtos.get(0).description()))
                .andExpect(jsonPath("$[1].description").value(responseDtos.get(1).description()))
                .andExpect(jsonPath("$.length()").value(responseDtos.size()));

        verify(serviceCategory).allCategories();
    }

    @WithMockUser(authorities = "UPDATE")
    @DisplayName(value = "Should update a Category and return CategoryResponseDTO")
    @Test
    void givenCategoryPatchDTOAndCategoryID_WhenModifyCategory_ThenReturnCategoryResponseDto() throws Exception {
        Long id = 1L;
        //Create one DTO for the output
        CategoryResponseDTO outputDto = new CategoryResponseDTO(id,
                "Phones", "Description of phones", new HashSet<>());
        //Stub
        when(serviceCategory.updateCategory(any(CategoryPatchDTO.class), any(Long.class))).thenReturn(outputDto);

        //post aimed to right endpoint
        mockMvc.perform(patch("/api/v1/category/patch/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        //Request body
                        .content("{\"name\":\"Phones modified\",\"description\":\"Description of phones modified\"}")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //Should be 200 code
                .andExpect(status().isOk()
                        //The jsonPath must be the same
                ).andExpect(jsonPath("$.name").value(outputDto.name()))
                .andDo(print());

        verify(serviceCategory).updateCategory(any(CategoryPatchDTO.class), any(Long.class));
    }
}

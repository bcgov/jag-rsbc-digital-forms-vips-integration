package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import ca.bc.gov.open.jag.ordsvipsclient.api.DocumentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDocumentOrdsResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

@WebMvcTest(DocumentController.class)
@AutoConfigureMockMvc(addFilters = false)
class DocumentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentApi documentApi;

    @MockBean
    private ConfigProperties properties;

    private static final byte[] FILE_BYTES = "test-file".getBytes(StandardCharsets.UTF_8);

    @Test
    @DisplayName("POST /document/{correlationId} returns 201 when ORDS store succeeds")
    void shouldReturnCreated_whenOrdsSuccess() throws Exception {
        String ordsUserGuid = "test-guid";
        String documentId = "DOC-123";

        VipsDocumentOrdsResponse ordsResponse = new VipsDocumentOrdsResponse();
        ordsResponse.setStatusCode(String.valueOf(DigitalFormsConstants.ORDS_SUCCESS_CD));
        ordsResponse.setDocumentId(documentId);

        when(properties.getOrdsUserGuid()).thenReturn(ordsUserGuid);
        when(documentApi.storeDocumentPost(
                eq("TEST"),
                eq("application"),
                eq("pdf"),
                eq(ordsUserGuid),
                eq(FILE_BYTES),
                eq("NT"),
                eq("SUBJ"),
                isNull()
        )).thenReturn(ordsResponse);

        MockMultipartFile metadata = new MockMultipartFile(
                "metadata",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                """
                {
                  "type_code": "TEST",
                  "notice_type_code": "NT",
                  "notice_subject_code": "SUBJ"
                }
                """.getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                FILE_BYTES
        );

        mockMvc.perform(multipart("/document/{correlationId}", "corr-1")
                .file(metadata)
                .file(file))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resp").value("success"))
                .andExpect(jsonPath("$.documentId").value(documentId));
    }

    @Test
    @DisplayName("POST /document/{correlationId} returns 400 when ORDS returns non-success status")
    void shouldReturnBadRequest_whenOrdsFailure() throws Exception {
        String ordsUserGuid = "test-guid";

        VipsDocumentOrdsResponse ordsResponse = new VipsDocumentOrdsResponse();
        ordsResponse.setStatusCode("500");

        when(properties.getOrdsUserGuid()).thenReturn(ordsUserGuid);
        when(documentApi.storeDocumentPost(
                eq("TEST"),
                eq("application"),
                eq("pdf"),
                eq(ordsUserGuid),
                eq(FILE_BYTES),
                eq("NT"),
                eq("SUBJ"),
                isNull()
        )).thenReturn(ordsResponse);

        MockMultipartFile metadata = new MockMultipartFile(
                "metadata",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                """
                {
                  "type_code": "TEST",
                  "notice_type_code": "NT",
                  "notice_subject_code": "SUBJ"
                }
                """.getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                FILE_BYTES
        );

        mockMvc.perform(multipart("/document/{correlationId}", "corr-2")
                .file(metadata)
                .file(file))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resp").value("fail"))
                .andExpect(jsonPath("$.error.message").value("Bad Request"))
                .andExpect(jsonPath("$.error.httpStatus").value(400));
    }

    @Test
    @DisplayName("POST /document/{correlationId} returns 400 when DocumentApi throws ApiException")
    void shouldReturnBadRequest_whenApiExceptionThrown() throws Exception {
        String ordsUserGuid = "test-guid";

        when(properties.getOrdsUserGuid()).thenReturn(ordsUserGuid);
        when(documentApi.storeDocumentPost(
                eq("TEST"),
                eq("application"),
                eq("pdf"),
                eq(ordsUserGuid),
                eq(FILE_BYTES),
                eq("NT"),
                eq("SUBJ"),
                isNull()
        )).thenThrow(Mockito.mock(ApiException.class));

        MockMultipartFile metadata = new MockMultipartFile(
                "metadata",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                """
                {
                  "type_code": "TEST",
                  "notice_type_code": "NT",
                  "notice_subject_code": "SUBJ"
                }
                """.getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                FILE_BYTES
        );

        mockMvc.perform(multipart("/document/{correlationId}", "corr-3")
                .file(metadata)
                .file(file))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resp").value("fail"))
                .andExpect(jsonPath("$.error.message")
                        .value("Upstream 500 error indicated from the SSG. VIPS ORDS returned 400, Bad Request"))
                .andExpect(jsonPath("$.error.httpStatus").value(400));
    }

    @Test
    @DisplayName("POST /document/{correlationId} returns 400 when file part is missing")
    void shouldReturnBadRequest_whenFileMissing() throws Exception {
        MockMultipartFile metadata = new MockMultipartFile(
                "metadata",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                """
                {
                  "type_code": "TEST",
                  "notice_type_code": "NT",
                  "notice_subject_code": "SUBJ"
                }
                """.getBytes(StandardCharsets.UTF_8)
        );

        mockMvc.perform(multipart("/document/{correlationId}", "corr-4")
                .file(metadata))
                .andExpect(status().isBadRequest());
    }
}
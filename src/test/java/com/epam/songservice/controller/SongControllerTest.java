package com.epam.songservice.controller;

import com.epam.songservice.BaseIntegrationTest;
import com.epam.songservice.entity.SongMetadata;
import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;
import com.epam.songservice.repository.SongMetadataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SongControllerTest extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    SongMetadataRepository songMetadataRepository;

    @BeforeEach
    void setup() {
        songMetadataRepository.deleteAll();
    }

    @Test
    void createSongMetaDataWithValidData() {
        SongMetadataDTO songMetadataDTO = SongMetadataDTO.builder()
                .name("Test name")
                .artist("Test artist")
                .album("Test album")
                .length("3:30")
                .resourceId(1)
                .year((short) 2024)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<SongMetadataDTO> request = new HttpEntity<>(songMetadataDTO, headers);

        ResponseEntity<CreateSongMetadataResponse> result = testRestTemplate
                .postForEntity("http://localhost:" + port + "/api/songs", request,
                        CreateSongMetadataResponse.class);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void createSongMetadataWithInvalidData() {
        SongMetadataDTO songMetadataDTO = SongMetadataDTO.builder()
                .name("Test name")
                .length("3:30")
                .resourceId(1)
                .year((short) 2024)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<SongMetadataDTO> request = new HttpEntity<>(songMetadataDTO, headers);

        ResponseEntity<String> result = testRestTemplate
                .postForEntity("http://localhost:" + port + "/api/songs", request,
                        String.class);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void getSongMetaData() {
        SongMetadata songMetadata = new SongMetadata(
                "Test name",
                "Test artist",
                "Test album",
                "3:30",
                1,
                (short) 2024);
        SongMetadata savedData = songMetadataRepository.save(songMetadata);
        Integer savedId = savedData.getId();

        ResponseEntity<SongMetadataDTO> result = testRestTemplate
                .getForEntity("http://localhost:" + port + "/api/songs/" + savedId, SongMetadataDTO.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        SongMetadataDTO songMetadataDTO = result.getBody();
        assertEquals(songMetadata.getName(), songMetadataDTO.getName());
        assertEquals(songMetadata.getArtist(), songMetadataDTO.getArtist());
        assertEquals(songMetadata.getAlbum(), songMetadataDTO.getAlbum());
    }

    @Test
    void getSongMetaDataNotExist() {
        ResponseEntity<String> result = testRestTemplate
                .getForEntity("http://localhost:" + port + "/api/songs/1", String.class);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteSongMetaData() {
        SongMetadata songMetadata = new SongMetadata(
                "Test name",
                "Test artist",
                "Test album",
                "3:30",
                1,
                (short) 2024);
        SongMetadata savedData = songMetadataRepository.save(songMetadata);
        Integer savedId = savedData.getId();

        HttpHeaders deleteRequestHeaders = new HttpHeaders();
        deleteRequestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<DeletedSongMetadataDTO> result = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/songs?id=" + savedId,
                HttpMethod.DELETE,
                new HttpEntity<>(deleteRequestHeaders),
                DeletedSongMetadataDTO.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(savedId, result.getBody().getIds().getFirst());
    }

    @Test
    void updateSongMetaData() {
        SongMetadata songMetadata = new SongMetadata(
                "Test name",
                "Test artist",
                "Test album",
                "3:30",
                1,
                (short) 2024);
        SongMetadata savedData = songMetadataRepository.save(songMetadata);
        Integer savedId = savedData.getId();

        SongMetadataDTO songMetadataDTO = SongMetadataDTO.builder()
                .name("New name")
                .artist("Test artist")
                .album("Test album")
                .length("3:30")
                .resourceId(1)
                .year((short) 2024)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<SongMetadataDTO> request = new HttpEntity<>(songMetadataDTO, headers);

        ResponseEntity<SongMetadataDTO> result = testRestTemplate
                .exchange("http://localhost:" + port + "/api/songs/" + savedId, HttpMethod.PUT, request,
                        SongMetadataDTO.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(songMetadataDTO.getName(), result.getBody().getName());
    }

    @Test
    void getByResourceId() {
        SongMetadata songMetadata = new SongMetadata(
                "Test name",
                "Test artist",
                "Test album",
                "3:30",
                1,
                (short) 2024);
        SongMetadata savedData = songMetadataRepository.save(songMetadata);
        Integer savedDataResourceId = savedData.getResourceId();

        ResponseEntity<SongMetadataDTO> result = testRestTemplate
                .getForEntity("http://localhost:" + port + "/api/songs/resource?id=" + savedDataResourceId, SongMetadataDTO.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(songMetadata.getName(), result.getBody().getName());
    }
}

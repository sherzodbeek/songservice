package com.epam.songservice;

import com.epam.songservice.repository.SongMetadataRepository;
import com.epam.songservice.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SongserviceApplicationTest extends BaseIntegrationTest {


    @Autowired
    SongService songService;

    @Autowired
    SongMetadataRepository songMetadataRepository;


    @Test
    void contextLoads() {
        assertNotNull(songService);
        assertNotNull(songMetadataRepository);
    }
}

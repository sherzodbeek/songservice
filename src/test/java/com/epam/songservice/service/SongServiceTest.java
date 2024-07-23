package com.epam.songservice.service;

import com.epam.songservice.entity.SongMetadata;
import com.epam.songservice.exception.ResourceNotFoundException;
import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;
import com.epam.songservice.repository.SongMetadataRepository;
import com.epam.songservice.service.impl.SongServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {


    @Mock
    SongMetadataRepository repository;

    @InjectMocks
    SongServiceImpl songService;


    @Test
    void createSongMetaData() {
        // given
        SongMetadataDTO songMetadataDTO = SongMetadataDTO.builder()
                .name("Test name")
                .artist("Test artist")
                .album("Test album")
                .length("3:30")
                .resourceId(1)
                .year((short) 2024)
                .build();

        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setId(1);

        when(repository.save(any())).thenReturn(songMetadata);

        // when
        CreateSongMetadataResponse result = songService.createSongMetadata(songMetadataDTO);

        // then
        assertNotNull(result);
        assertEquals(songMetadata.getId(), result.getId());
    }

    @Test
    void getSongMetaDataReturnSongMetadata() {
        // given
        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setId(1);
        when(repository.findById(any())).thenReturn(Optional.of(songMetadata));

        // when
        SongMetadataDTO result = songService.getSongMetadata(1);

        // then
        assertNotNull(result);
        assertEquals(songMetadata.getId(), result.getId());
    }

    @Test
    void getSongMetadataNotFond() {
        // when
        when(repository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class,
                () -> songService.getSongMetadata(1));
    }

    @Test
    void deleteSongMetaData() {
        // given
        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setId(1);
        when(repository.findAllById(any())).thenReturn(List.of(songMetadata));

        // when
        DeletedSongMetadataDTO result = songService.deleteSongMetadata("1,2");

        // then
        assertNotNull(result);
        assertEquals(songMetadata.getId(), result.getIds().getFirst());
        verify(repository).deleteAllById(List.of(songMetadata.getId()));
    }

    @Test
    void updateSongMetaData() {
        // given
        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setId(1);
        songMetadata.setName("Old Name");
        when(repository.findById(any())).thenReturn(Optional.of(songMetadata));

        SongMetadataDTO songMetadataDTO = SongMetadataDTO.builder()
                .name("New name")
                .build();

        // when
        songService.updateSongMetaData(1, songMetadataDTO);

        // then
        verify(repository).save(songMetadata);
    }

    @Test
    void getByResourceId() {
        // given
        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setId(1);
        when(repository.findByResourceId(any())).thenReturn(Optional.of(songMetadata));

        // when
        SongMetadataDTO result = songService.getByResourceId(1);

        // then
        assertNotNull(result);
        assertEquals(songMetadata.getId(), result.getId());
    }

    @Test
    void getByResourceIdNotFound() {
        // given
        when(repository.findByResourceId(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class,
                () -> songService.getByResourceId(1));

    }
}

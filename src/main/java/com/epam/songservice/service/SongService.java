package com.epam.songservice.service;

import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;

import java.util.List;

public interface SongService {


    CreateSongMetadataResponse createSongMetadata(SongMetadataDTO songMetaDataDTO);

    SongMetadataDTO getSongMetadata(Long id);

    DeletedSongMetadataDTO deleteSongMetadata(List<Long> ids);

    void updateSongMetaData(Long id, SongMetadataDTO songMetadataDTO);

    SongMetadataDTO getByResourceId(Long id);

}

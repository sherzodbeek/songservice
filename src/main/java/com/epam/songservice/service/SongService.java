package com.epam.songservice.service;

import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;

import java.util.List;

public interface SongService {


    CreateSongMetadataResponse createSongMetadata(SongMetadataDTO songMetaDataDTO);

    SongMetadataDTO getSongMetadata(Integer id);

    DeletedSongMetadataDTO deleteSongMetadata(String ids);

    void updateSongMetaData(Integer id, SongMetadataDTO songMetadataDTO);

    SongMetadataDTO getByResourceId(Integer id);

}

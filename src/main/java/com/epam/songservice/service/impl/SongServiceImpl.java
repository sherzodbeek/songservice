package com.epam.songservice.service.impl;

import com.epam.songservice.entity.SongMetadata;
import com.epam.songservice.exception.ResourceNotFoundException;
import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;
import com.epam.songservice.repository.SongMetadataRepository;
import com.epam.songservice.service.SongService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongMetadataRepository repository;

    @Autowired
    public SongServiceImpl(SongMetadataRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CreateSongMetadataResponse createSongMetadata(SongMetadataDTO songMetaDataDTO) {

        SongMetadata songMetaData = new SongMetadata(
                songMetaDataDTO.getName(),
                songMetaDataDTO.getArtist(),
                songMetaDataDTO.getAlbum(),
                songMetaDataDTO.getLength(),
                songMetaDataDTO.getResourceId(),
                songMetaDataDTO.getYear());
        SongMetadata savedEntity = repository.save(songMetaData);

        return CreateSongMetadataResponse.builder()
                .id(savedEntity.getId())
                .build();
    }

    @Override
    public SongMetadataDTO getSongMetadata(Long id) {
        SongMetadata songMetadata = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The song metadata with the specified id (%d) does not exist", id)));

        return SongMetadataDTO.builder()
                .id(songMetadata.getId())
                .name(songMetadata.getName())
                .artist(songMetadata.getArtist())
                .album(songMetadata.getAlbum())
                .length(songMetadata.getLength())
                .resourceId(songMetadata.getResourceId())
                .year(songMetadata.getYear())
                .build();
    }

    @Override
    public DeletedSongMetadataDTO deleteSongMetadata(List<Long> ids) {
        List<SongMetadata> songMetadatas = repository.findAllById(ids);
        List<Long> deletedIds = songMetadatas.stream().map(SongMetadata::getId).toList();
        repository.deleteAllById(deletedIds);
        return DeletedSongMetadataDTO.builder().ids(deletedIds).build();
    }

    @Override
    public void updateSongMetaData(Long id, SongMetadataDTO songMetadataDTO) {
        SongMetadata songMetadata = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The song metadata with the specified id (%d) does not exist", id)));

        songMetadata.setAlbum(songMetadataDTO.getAlbum());
        songMetadata.setName(songMetadataDTO.getName());
        songMetadata.setLength(songMetadataDTO.getLength());
        songMetadata.setYear(songMetadataDTO.getYear());
        songMetadata.setResourceId(songMetadataDTO.getResourceId());
        songMetadata.setArtist(songMetadataDTO.getArtist());

        repository.save(songMetadata);
    }

    @Override
    public SongMetadataDTO getByResourceId(Long id) {
        SongMetadata songMetadata = repository
                .findByResourceId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The song metadata with the specified resource id (%d) does not exist", id)));

        return SongMetadataDTO.builder()
                .id(songMetadata.getId())
                .name(songMetadata.getName())
                .artist(songMetadata.getArtist())
                .album(songMetadata.getAlbum())
                .length(songMetadata.getLength())
                .resourceId(songMetadata.getResourceId())
                .year(songMetadata.getYear())
                .build();
    }

}

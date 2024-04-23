package com.epam.songservice.controller;


import com.epam.songservice.payload.CreateSongMetadataResponse;
import com.epam.songservice.payload.DeletedSongMetadataDTO;
import com.epam.songservice.payload.SongMetadataDTO;
import com.epam.songservice.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<CreateSongMetadataResponse> create(@RequestBody @Valid SongMetadataDTO songMetaDataDTO) {
        CreateSongMetadataResponse response = songService.createSongMetadata(songMetaDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongMetadataDTO> get(@PathVariable Long id) {
        SongMetadataDTO songMetadata = songService.getSongMetadata(id);
        return ResponseEntity.ok(songMetadata);
    }

    @DeleteMapping
    public ResponseEntity<DeletedSongMetadataDTO> delete(@RequestParam("id") List<Long> ids) {
        DeletedSongMetadataDTO response = songService.deleteSongMetadata(ids);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongMetadataDTO> update(
            @PathVariable Long id, @RequestBody @Valid SongMetadataDTO songMetadataDTO) {
        songService.updateSongMetaData(id, songMetadataDTO);
        return ResponseEntity.ok(songMetadataDTO);
    }

    @GetMapping("/resource")
    public ResponseEntity<SongMetadataDTO> getByResourceId(@RequestParam("id") Long id) {
        SongMetadataDTO songMetadata = songService.getByResourceId(id);
        return ResponseEntity.ok(songMetadata);
    }

}

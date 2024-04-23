package com.epam.songservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "song_meta_data", schema = "public")
@Getter
@Setter
public class SongMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    private String artist;

    @Column(name = "album")
    private String album;

    @Column(name = "length")
    private String length;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "year")
    private Short year;


    public SongMetadata(String name, String artist, String album, String length, Long resourceId, Short year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.resourceId = resourceId;
        this.year = year;
    }
}

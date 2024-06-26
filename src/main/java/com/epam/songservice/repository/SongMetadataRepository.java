package com.epam.songservice.repository;

import com.epam.songservice.entity.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongMetadataRepository extends JpaRepository<SongMetadata, Integer> {

    Optional<SongMetadata> findByResourceId(Integer id);
}

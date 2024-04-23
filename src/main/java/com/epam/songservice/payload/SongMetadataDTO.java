package com.epam.songservice.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongMetadataDTO {

    private Long id;

    @NotEmpty(message = "Song name must not be empty!")
    private String name;

    @NotEmpty(message = "Song artis must not be empty!")
    private String artist;

    @NotEmpty(message = "Song album must not be empty!")
    private String album;

    @NotEmpty(message = "Song length must not be empty!")
    private String length;

    @NotNull(message = "Resource ID must not be empty!")
    private Long resourceId;

    @NotNull(message = "Song year must not be empty!")
    private Short year;
}

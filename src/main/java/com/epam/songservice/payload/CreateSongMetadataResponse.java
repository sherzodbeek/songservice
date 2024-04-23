package com.epam.songservice.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSongMetadataResponse {

    Long id;
}

package dev.eshacu.api.dto.post;

import dev.eshacu.api.dto.common.TagDto;
import java.time.Instant;
import java.util.List;

public record PostCardDto(
        Long id, String title, String bodyHtml, List<TagDto> tags,
        Instant publishedAt, Instant updatedAt
) { }
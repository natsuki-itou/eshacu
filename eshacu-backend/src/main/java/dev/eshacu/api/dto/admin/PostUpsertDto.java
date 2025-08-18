package dev.eshacu.api.dto.admin;

import dev.eshacu.api.dto.common.TagDto;

import java.time.Instant;
import java.util.List;

public record PostUpsertDto(Long id, String title, String bodyHtml, List<TagDto> tags,
                            Instant publishedAt, Instant updatedAt) { }
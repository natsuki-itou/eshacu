package dev.eshacu.api.dto.post;

import java.util.List;

public record PostDetailDto(
        String title, String summary, String bodyMarkdown,
        List<String> techStack, List<String> tags,
        String heroImageUrl, String repoUrl, String appUrl, Boolean draft
) { }
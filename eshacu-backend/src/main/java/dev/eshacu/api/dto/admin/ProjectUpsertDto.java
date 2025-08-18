package dev.eshacu.api.dto.admin;

import java.util.List;

public record ProjectUpsertDto(
        String title, String summary, String bodyMarkdown,
        List<String> techStack, List<String> tags,
        String heroImageUrl, String repoUrl, String appUrl, Boolean draft
) {
}

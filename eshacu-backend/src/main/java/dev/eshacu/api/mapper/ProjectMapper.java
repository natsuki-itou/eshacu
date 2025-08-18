package dev.eshacu.api.mapper;

import dev.eshacu.api.dto.project.ProjectDetailDto;
import org.mapstruct.Mapper;
import java.util.List;

// TODO: GPTが怪しいので後で大幅にやばそう 直す必要ありそう
// TODO: #11 で Project エンティティが定まったら型を差し替え
class Project {
    Long id; String title; String summary; String bodyMarkdown;
    java.time.Instant updatedAt; String heroImageUrl; String repoUrl; String appUrl;
    java.util.List<String> tags; java.util.List<String> techStack; java.util.List<String> images;
    // getter想定（スタブ）
}

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDetailDto toCard(Project entity);

    // 本文は Markdown→HTML 変換前提。#11 で Service を噛ませる想定だが、
    // ここでは簡易に Markdown をそのまま bodyHtml に入れる（スタブ運用）。
    default ProjectDetailDto toDetail(Project entity) {
        String html = entity.bodyMarkdown; // TODO: MarkdownService で変換
        return new ProjectDetailDto(
                entity.id,
                entity.title,
                entity.summary,
                html,
                entity.techStack,
                entity.images,
                List.of(), // TODO: TagDTO へ変換
                entity.updatedAt
        );
    }
}
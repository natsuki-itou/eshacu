package dev.eshacu.api.mapper;

import dev.eshacu.api.dto.common.TagDto;
import dev.eshacu.api.dto.project.ProjectCardDto;
import dev.eshacu.api.dto.project.ProjectDetailDto;
import dev.eshacu.domain.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "tags", expression = "java(toTagDto(entity.getTags()))")
    ProjectCardDto toCard(Project entity);

    @Mapping(target = "excerpt", source = "summary")
    @Mapping(target = "bodyHtml", expression = "java(md.toHtml(entity.getBodyMarkdown()))")
    @Mapping(target = "tags", expression = "java(toTagDto(entity.getTags()))")
    ProjectDetailDto toDetail(Project entity, @Context MarkdownService md);

    default List<TagDto> toTagDto(List<String> tags) {
        return (tags == null) ? List.of() : tags.stream().map(TagDto::new).toList();
    }

    interface  MarkdownService {
        String toHtml(String markdown);
    }
}
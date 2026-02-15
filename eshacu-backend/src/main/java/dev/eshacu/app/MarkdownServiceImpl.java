package dev.eshacu.app;

import dev.eshacu.api.mapper.ProjectMapper.MarkdownService;
import org.springframework.stereotype.Service;

@Service
public class MarkdownServiceImpl implements MarkdownService {
    @Override
    public String toHtml(String markdown){
        if(markdown == null || markdown.isBlank()) return "";
        // TODO: 本番は flexmark 等に差し替え。MVPでは簡易でOK
        return markdown;
    }
}

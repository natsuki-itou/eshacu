package dev.eshacu.api.dto.profile;

import java.util.Map;

public record ProfilePublicDto(String displayName, String bio, String avatarUrl, Map<String, String> socials) {

}
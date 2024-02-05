package com.justsayit.story.service.edit.command;

import com.justsayit.infra.s3.dto.StoryPhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EditStoryCommand {

    private Long storyId;
    private String emotion;
    private String content;
    private List<Long> removedPhoto;
    private List<StoryPhoto> newPhoto;
    private boolean opened;
    private boolean anonymous;
    private List<String> emotionOfEmpathy;
}

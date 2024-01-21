package com.justsayit.story.write.command;

import com.justsayit.infra.s3.dto.StoryImgInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AddStoryCommand {

    private Long memberId;
    private String emotion;
    private String content;
    private List<StoryImgInfo> storyImgInfoList;
    private boolean opened;
    private boolean anonymous;
    private List<String> feelingsOfEmpathy;
}

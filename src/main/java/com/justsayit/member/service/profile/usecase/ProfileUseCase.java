package com.justsayit.member.service.profile.usecase;

import com.justsayit.member.service.profile.command.UpdateProfileCmd;
import com.justsayit.member.service.profile.dto.GetProfileRes;

public interface ProfileUseCase {

    void updateProfile(UpdateProfileCmd cmd);

    GetProfileRes getProfile();
}

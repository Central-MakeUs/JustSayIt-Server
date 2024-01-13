package com.justsayit.member.service.profile.usecase;

import com.justsayit.member.service.profile.command.UpdateProfileCmd;

public interface ProfileUseCase {

    void updateProfile(UpdateProfileCmd build);
}

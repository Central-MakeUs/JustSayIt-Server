package com.justsayit.story.service.empathy.usecase;

import com.justsayit.story.service.empathy.command.EmpathizeCommand;

public interface EmpathizeUseCase {

    void empathize(EmpathizeCommand cmd);
}

package com.justsayit.story.service.empathize.usecase;

import com.justsayit.story.service.empathize.command.CancelEmpathizeCommand;
import com.justsayit.story.service.empathize.command.EmpathizeCommand;

public interface EmpathizeUseCase {

    void empathize(EmpathizeCommand cmd);

    void cancelEmpathize(CancelEmpathizeCommand cmd);
}

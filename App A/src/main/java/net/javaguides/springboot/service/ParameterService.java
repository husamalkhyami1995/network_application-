package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Parameter;
import net.javaguides.springboot.web.dto.ParameterInfo;

import java.util.List;

public interface ParameterService {

    List<Parameter> read();
    void edit (ParameterInfo parameterInfo);
}

package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Parameter;
import net.javaguides.springboot.repository.ParameterRepository;
import net.javaguides.springboot.web.dto.ParameterInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }



    @Override
    @Cacheable("parameters")
    public List<Parameter> read() {
        return this.parameterRepository.findAll();
    }

    @Override
    @CacheEvict(value="parameters", allEntries = true )
    public void edit(ParameterInfo parameterInfo) {
       Parameter parameter = this.parameterRepository.findById(parameterInfo.getId()).get() ;
       parameter.setSeatNumber(parameterInfo.getSeatNumber());
       parameter.setBenefit(parameterInfo.getBenefit());

       this.parameterRepository.save(parameter);

    }
}

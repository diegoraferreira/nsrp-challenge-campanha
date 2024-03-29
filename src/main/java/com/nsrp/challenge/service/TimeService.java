package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Time;
import com.nsrp.challenge.model.time.TimeModel;
import com.nsrp.challenge.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Transactional
    public Time save(String time) {
        Time timeDoCoracao = new Time(time);
        return this.timeRepository.save(timeDoCoracao);
    }

    @Transactional(readOnly = true)
    public Optional<TimeModel> findModelByNome(String nome) {
        Optional<Time> timeOptional = this.timeRepository.findByNome(nome);
        if (timeOptional.isPresent()) {
            Time time = timeOptional.get();
            TimeModel timeModel = new TimeModel(time.getId(), time.getNome());
            return Optional.of(timeModel);
        }
        return Optional.empty();
    }


    @Transactional(readOnly = true)
    public Optional<Time> findByNome(String nome) {
        return this.timeRepository.findByNome(nome);
    }

    @Transactional(readOnly = true)
    public Optional<TimeModel> findByTimeId(Long id) {
        return this.timeRepository.findByTimeId(id);
    }
}

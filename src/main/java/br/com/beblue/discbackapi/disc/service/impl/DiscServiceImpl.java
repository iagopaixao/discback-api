package br.com.beblue.discbackapi.disc.service.impl;

import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.CarService;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscServiceImpl implements CarService {

  private final DiscRepository repository;

  private final DiscMapper mapper;
}

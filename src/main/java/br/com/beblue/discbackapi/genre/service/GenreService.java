package br.com.beblue.discbackapi.genre.service;

import br.com.beblue.discbackapi.genre.repository.GenreRepository;
import br.com.beblue.discbackapi.genre.service.mapper.GenreMapper;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreService {

  private final GenreRepository repository;

  private final GenreMapper mapper;

  @Transactional(readOnly = true)
  public List<GenreVO> getAll() {
    return mapper.toVO(repository.findAll());
  }
}

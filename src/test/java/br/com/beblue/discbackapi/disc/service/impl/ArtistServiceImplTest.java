package br.com.beblue.discbackapi.disc.service.impl;

import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.dto.DiscDTO;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.mock.DiscMockFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

//TODO: add tests
@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceImplTest {

  @Mock private DiscRepository repository;

  @Mock private DiscMapper mapper;

  @InjectMocks
  DiscServiceImpl service;

  @Test
  public void shouldReturnAllDiscs_whenGetAllIsCalled() {
    assert true;
  }
}

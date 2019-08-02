package br.com.beblue.discbackapi.disc.repository;

import br.com.beblue.discbackapi.disc.domain.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {}

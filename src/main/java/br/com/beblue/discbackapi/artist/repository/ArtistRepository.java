package br.com.beblue.discbackapi.artist.repository;

import br.com.beblue.discbackapi.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {}

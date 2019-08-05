package br.com.beblue.discbackapi.disc.repository;

import br.com.beblue.discbackapi.disc.domain.Disc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {

  @Query(
      value =
          "SELECT *"
              + "FROM disc d"
              + "         LEFT JOIN disc_artist da ON da.disc_id = d.id "
              + "         LEFT JOIN artist_genre ag ON ag.artist_id = da.artist_id "
              + "         LEFT JOIN genre g on ag.genre_id = g.id "
              + "WHERE UPPER(g.name) LIKE UPPER('%genre%') "
              + "ORDER BY d.name ASC",
      countQuery = "SELECT COUNT(*) FROM disc",
      nativeQuery = true)
  Page<Disc> findAllByGenre(@Param("genre") String genre, Pageable pageable);
}

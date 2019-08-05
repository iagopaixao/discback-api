package br.com.beblue.discbackapi.sale.repository;

import br.com.beblue.discbackapi.sale.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(
      "from #{#entityName} s "
          + "where s.auditDate.createdAt <= :initialDate "
          + "and s.auditDate.createdAt >= :endDate "
          + "order by s.auditDate.createdAt desc ")
  Page<Sale> searchBy(
      @Param("initialDate") LocalDateTime initialDate,
      @Param("endDate") LocalDateTime endDate,
      Pageable pageable);
}

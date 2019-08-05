package br.com.beblue.discbackapi.sale.repository;

import br.com.beblue.discbackapi.sale.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(
      "from #{#entityName} s "
          + "where s.auditDate.createdAt <= :initalDate "
          + "and s.auditDate.createdAt >= :endDate "
          + "order by s.auditDate.createdAt asc")
  Page<Sale> searchBy(
      @Param("initalDate") LocalDateTime initialDate,
      @Param("endDate") LocalDateTime endDate,
      Pageable pageable);
}

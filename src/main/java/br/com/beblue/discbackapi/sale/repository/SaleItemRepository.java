package br.com.beblue.discbackapi.sale.repository;

import br.com.beblue.discbackapi.sale.domain.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {}

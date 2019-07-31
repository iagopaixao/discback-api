package br.com.beblue.discbackapi.sale.repository;

import br.com.beblue.discbackapi.sale.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {}

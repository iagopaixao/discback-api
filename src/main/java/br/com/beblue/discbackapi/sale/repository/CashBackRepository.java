package br.com.beblue.discbackapi.sale.repository;

import br.com.beblue.discbackapi.sale.domain.CashBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBackRepository extends JpaRepository<CashBack, Long> {}

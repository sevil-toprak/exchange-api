package com.akinon.exchangeapi.repository;
import com.akinon.exchangeapi.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate, Long> {
}
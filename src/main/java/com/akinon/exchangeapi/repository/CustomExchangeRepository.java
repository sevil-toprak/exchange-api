package com.akinon.exchangeapi.repository;

import com.akinon.exchangeapi.model.entity.ExchangeRate;
import com.akinon.exchangeapi.model.request.FilterExchangeRateOrTransactionRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomExchangeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // it is filtered by transactionId or createdAt date range
    public List<ExchangeRate> filterExchangeRate(FilterExchangeRateOrTransactionRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> cq = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> exchangeRateRoot = cq.from(ExchangeRate.class);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(request.getTransactionId())) {
            predicates.add(cb.equal(exchangeRateRoot.get("transactionId"), request.getTransactionId()));
        }
        if (Objects.nonNull(request.getStartDate()) && Objects.nonNull(request.getEndDate())) {
            predicates.add(cb.between(exchangeRateRoot.get("createdAt"), request.getStartDate(), request.getEndDate()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        if(CollectionUtils.isEmpty(predicates)) {
            return new ArrayList<>();
        }
        return entityManager.createQuery(cq).getResultList();
    }
}
package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JPAExchangeRateRepository implements ExchangeRepository {

    @PersistenceContext
    public EntityManager entityManager;


    @Override
    public void put(ExchangeRate exchangeRate) {
        if (exchangeRateExists(exchangeRate))
            entityManager.merge(exchangeRate);
        else
            entityManager.persist(exchangeRate);
    }

    private boolean exchangeRateExists(ExchangeRate exchangeRate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> criteriaQuery = criteriaBuilder.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = criteriaQuery.from(ExchangeRate.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("date"), exchangeRate.getDate()), criteriaBuilder.equal(root.get("currency"), exchangeRate.getCurrency()));
        TypedQuery<ExchangeRate> query = entityManager.createQuery(criteriaQuery);
        return !query.getResultList().isEmpty();
    }
}

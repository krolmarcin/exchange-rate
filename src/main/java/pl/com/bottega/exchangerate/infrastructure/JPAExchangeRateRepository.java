package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class JPAExchangeRateRepository implements ExchangeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void put(ExchangeRate e) {
        entityManager.persist(e);
    }

    @Override
    public ExchangeRate find(LocalDate date, String currency) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> criteriaQuery = criteriaBuilder.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = criteriaQuery.from(ExchangeRate.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("date"), date), criteriaBuilder.equal(root.get("currency"), currency));
        TypedQuery<ExchangeRate> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().get(0);
    }

    @Override
    public boolean exist(LocalDate date, String currency) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> criteriaQuery = criteriaBuilder.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = criteriaQuery.from(ExchangeRate.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("date"), date), criteriaBuilder.equal(root.get("currency"), currency));
        TypedQuery<ExchangeRate> query = entityManager.createQuery(criteriaQuery);
        return !query.getResultList().isEmpty();
    }


    @Override
    public void update(ExchangeRate exchangeRate) {
        entityManager.merge(exchangeRate);
    }

}

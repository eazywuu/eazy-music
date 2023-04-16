package xyz.eazywu.music.repository.strategy;

import xyz.eazywu.music.repository.spec.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GreaterThanEqualStrategy<T> implements SpecificationStrategy<T> {
    @Override
    public Predicate apply(Root<T> root, CriteriaBuilder builder, SearchCriteria criteria) {
        return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
    }
}

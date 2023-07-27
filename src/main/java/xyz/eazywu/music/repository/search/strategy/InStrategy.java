package xyz.eazywu.music.repository.search.strategy;

import xyz.eazywu.music.repository.search.SearchCriteria;
import xyz.eazywu.music.repository.search.SearchStrategy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InStrategy<T> implements SearchStrategy<T> {
    @Override
    public Predicate apply(Root<T> root, CriteriaBuilder builder, SearchCriteria criteria) {
        return builder.in(root.get(criteria.getKey())).value(criteria.getValue());
    }
}

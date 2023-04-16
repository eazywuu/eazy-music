package xyz.eazywu.music.repository.strategy;

import xyz.eazywu.music.repository.spec.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface SpecificationStrategy<T> {
    Predicate apply(Root<T> root, CriteriaBuilder builder, SearchCriteria criteria);
}

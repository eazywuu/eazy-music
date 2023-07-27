package xyz.eazywu.music.repository.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 搜索策略
 */
@FunctionalInterface
public interface SearchStrategy<T> {
    Predicate apply(Root<T> root, CriteriaBuilder builder, SearchCriteria criteria);
}

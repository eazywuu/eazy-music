package xyz.eazywu.music.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import xyz.eazywu.music.repository.strategy.SpecificationStrategy;
import xyz.eazywu.music.repository.strategy.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> list;
    private final Map<SearchOperation, SpecificationStrategy<T>> strategyMap;

    protected AbstractSpecification() {
        this.list = new ArrayList<>();
        this.strategyMap = new EnumMap<>(SearchOperation.class);
        strategyMap.put(SearchOperation.GREATER_THAN, new GreaterThanStrategy<>());
        strategyMap.put(SearchOperation.LESS_THAN, new LessThanStrategy<>());
        strategyMap.put(SearchOperation.GREATER_THAN_EQUAL, new GreaterThanEqualStrategy<>());
        strategyMap.put(SearchOperation.LESS_THAN_EQUAL, new LessThanEqualStrategy<>());
        strategyMap.put(SearchOperation.NOT_EQUAL, new NotEqualStrategy<>());
        strategyMap.put(SearchOperation.EQUAL, new EqualStrategy<>());
        strategyMap.put(SearchOperation.MATCH, new MatchStrategy<>());
        strategyMap.put(SearchOperation.MATCH_END, new MatchEndStrategy<>());
        strategyMap.put(SearchOperation.MATCH_START, new MatchStartStrategy<>());
        strategyMap.put(SearchOperation.IN, new InStrategy<>());
        strategyMap.put(SearchOperation.NOT_IN, new NotInStrategy<>());
    }

    public void add(SearchCriteria searchCriteria) {
        list.add(searchCriteria);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            SpecificationStrategy<T> strategy = strategyMap.get(criteria.getOperation());
            if (strategy == null) {
                throw new IllegalArgumentException("Invalid search operation: " + criteria.getOperation());
            }
            predicates.add(strategy.apply(root, builder, criteria));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

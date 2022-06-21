package br.com.utilities.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.utilities.interfaces.ICustomRepository;

/**
 * TODO ainda por fazer
 * 
 * @author gustavo
 *
 * @param <E>
 * @param <Rep> I must be Number and Serializable
 */
public class CustomRepository<E, I extends Number> {

	public ICustomRepository<E, I> repository;

	public List<E> findByCriteria(Map<String, Object> maps) {
		return repository.findAll(new Specification<E>() {

			private static final long serialVersionUID = 2934722725165068524L;

			@Override
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				maps.entrySet().iterator().forEachRemaining(f -> {
					if (f.getValue() != null && f.getKey() != null) {
						predicates.add(cb.and(cb.equal(root.get(f.getKey()), f.getValue())));
					}
				});
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	public List<E> findByCriteriaComplex(Map<String, Map<String, Object>> maps) {
		return repository.findAll(new Specification<E>() {
			
			private static final long serialVersionUID = -6336582393575025010L;

			@Override
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				maps.entrySet().iterator().forEachRemaining(f -> {
					if (f.getKey() != null && f.getValue() != null) {
						f.getValue().entrySet().iterator().forEachRemaining(v -> {
							if (v.getValue() != null && v.getKey() != null) {
								switch (v.getKey().toLowerCase().trim()) {
								case "=":
								case "equal":
								case "eq": {
									predicates.add(cb.and(cb.equal(root.get(f.getKey()), v.getValue())));
								}
									break;
								case "!=":
								case "not equal":
								case "different":
								case "<>":
								case "diff":
								case "><": {
									// predicates.add(cb.and(cb.diff(root.get(f.getKey()), v.getValue())));
								}
									break;
								case "like": {
									predicates.add(
											cb.and(cb.like(root.get(f.getKey()), "%" + v.getValue().toString() + "%")));
								}
									break;
								case ">":
								case "greater":
								case "gt": {
									// predicates.add(cb.and(cb.greaterThan(root.get(f.getKey()), v.getValue())));
									// predicates.add(cb.and(cb.g(root.get(f.getKey()), v.getValue())));
								}
									break;
								case "<":
								case "lesser":
								case "ls": {
									//TODO
								}
									break;
								case ">=":
								case "greater or equal":
								case "ge": {
									//TODO
								}
									break;
								case "<=":
								case "lesser or equal":
								case "le": {
									//TODO
								}
									break;
								default:
									break;
								}
							}
						});
					}
				});
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

}

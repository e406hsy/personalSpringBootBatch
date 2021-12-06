package com.soonyong.hong.batch.crawl.filter;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlFilterChain implements CrawlFilter {

	private CrawlFilter delegate;
	private CrawlFilter next;
	private DelegateCondition delegateCondition;

	@Override
	public boolean isAllowed(String value) {
		log.debug("is allowed called with value {}", value);
		boolean result = this.delegateCondition.getPredicate(delegate, next).test(value);
		log.debug("and result : {}", result);
		return result;
	}

	@RequiredArgsConstructor
	public enum DelegateCondition {
		AND((pre1, pre2) -> pre1.and(pre2)), OR((pre1, pre2) -> pre1.or(pre2));

		private final BiFunction<Predicate<String>, Predicate<String>, Predicate<String>> predicateGenerator;

		private Predicate<String> getPredicate(CrawlFilter delegate, CrawlFilter next) {
			return predicateGenerator.apply((input) -> delegate.isAllowed(input), (input) -> next.isAllowed(input));
		}
	}

	public static class TriPredicate<E1, E2, E3> {

	}
}

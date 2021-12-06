package com.soonyong.hong.batch.crawl.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.soonyong.hong.batch.crawl.filter.CrawlFilterChain.DelegateCondition;

public class CrawlFilterChainTest {

	CrawlFilter trueFilter = (value) -> true;
	CrawlFilter falseFilter = (value) -> false;

	@Test
	@DisplayName("AND test (true, ture) -> true")
	public void andTest1() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(trueFilter)
				.delegateCondition(DelegateCondition.AND).next(trueFilter).build();

		assertTrue(crawlFilterChain.isAllowed("value"));

	}

	@Test
	@DisplayName("AND test (true, false) -> false")
	public void andTest2() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(trueFilter)
				.delegateCondition(DelegateCondition.AND).next(falseFilter).build();

		assertFalse(crawlFilterChain.isAllowed("value"));

	}

	@Test
	@DisplayName("AND test (false, ture) -> false")
	public void andTest3() {

		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(falseFilter)
				.delegateCondition(DelegateCondition.AND).next(trueFilter).build();

		assertFalse(crawlFilterChain.isAllowed("value"));
	}

	@Test
	@DisplayName("AND test (false, false) -> false")
	public void andTest4() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(falseFilter)
				.delegateCondition(DelegateCondition.AND).next(falseFilter).build();

		assertFalse(crawlFilterChain.isAllowed("value"));
	}

	@Test
	@DisplayName("OR test (true, false) -> true")
	public void orTest1() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(trueFilter)
				.delegateCondition(DelegateCondition.OR).next(falseFilter).build();

		assertTrue(crawlFilterChain.isAllowed("value"));

	}

	@Test
	@DisplayName("OR test (false, ture) -> true")
	public void orTest2() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(falseFilter)
				.delegateCondition(DelegateCondition.OR).next(trueFilter).build();

		assertTrue(crawlFilterChain.isAllowed("value"));

	}

	@Test
	@DisplayName("OR test (false, false) -> false")
	public void orTest3() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(falseFilter)
				.delegateCondition(DelegateCondition.OR).next(falseFilter).build();

		assertFalse(crawlFilterChain.isAllowed("value"));

	}

	@Test
	@DisplayName("OR test (true, ture) -> true")
	public void orTest4() {
		CrawlFilterChain crawlFilterChain = CrawlFilterChain.builder().delegate(trueFilter)
				.delegateCondition(DelegateCondition.OR).next(trueFilter).build();

		assertTrue(crawlFilterChain.isAllowed("value"));

	}
}

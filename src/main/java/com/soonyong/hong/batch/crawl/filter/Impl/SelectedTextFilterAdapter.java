package com.soonyong.hong.batch.crawl.filter.Impl;

import java.util.function.Predicate;

import org.jsoup.nodes.Element;

import com.soonyong.hong.batch.crawl.filter.CrawlFilter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectedTextFilterAdapter implements CrawlFilter {

	private String cssSelector;
	private Predicate<String> delegate;

	@Override
	public boolean isAllowed(Element value) {
		return delegate.test(value.select(cssSelector).text().trim());
	}

}

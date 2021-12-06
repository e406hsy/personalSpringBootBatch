package com.soonyong.hong.batch.crawl.filter;

@FunctionalInterface
public interface CrawlFilter {

	public boolean isAllowed(String value);
}

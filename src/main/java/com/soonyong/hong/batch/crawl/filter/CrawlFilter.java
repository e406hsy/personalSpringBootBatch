package com.soonyong.hong.batch.crawl.filter;

import org.jsoup.nodes.Element;

@FunctionalInterface
public interface CrawlFilter {

	public boolean isAllowed(Element value);
}

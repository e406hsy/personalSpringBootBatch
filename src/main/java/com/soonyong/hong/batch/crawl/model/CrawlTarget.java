package com.soonyong.hong.batch.crawl.model;

import com.soonyong.hong.batch.crawl.filter.CrawlFilter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlTarget {

	private String title;
	private CrawlFilter filter;
	private String url;
	private String baseCssSeletor;
	private String targetCssSeletor;
}

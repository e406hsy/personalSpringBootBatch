package com.soonyong.hong.batch.crawl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soonyong.hong.batch.config.crwal.CrawlTargetFactory;
import com.soonyong.hong.batch.crawl.model.CrawlTarget;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlService {
	private final WebCrawler webCrawler;

	public List<String> getTexts(String title) {
		CrawlTarget crawlTarget = CrawlTargetFactory.get(title);
		return webCrawler.getTexts(crawlTarget);
	}
}

package com.soonyong.hong.batch.crawl.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.soonyong.hong.batch.config.crwal.CrawlTargetFactory;
import com.soonyong.hong.batch.crawl.model.CrawlTarget;

@SpringBootTest
@ActiveProfiles("test")
public class WebCrawlerTest {

	private static final Logger log = LoggerFactory.getLogger(WebCrawlerTest.class);

	@Autowired
	WebCrawler webCrawler;

	@Test
	public void getText() {
		CrawlTarget target = CrawlTargetFactory.get("ppomppu");
		List<String> results = webCrawler.getTexts(target);

		log.debug("{}", results);
	}
}

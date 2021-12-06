package com.soonyong.hong.batch.crawl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.soonyong.hong.batch.crawl.model.CrawlTarget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebCrawler {

	public List<String> getTexts(CrawlTarget target) {
		try {
			Document document = Jsoup.connect(target.getUrl().toString()).get();
			log.debug("getTexts: document = {}", document);
			Elements elements = document.select(target.getBaseCssSeletor());
			return elements.stream()
					.filter(element -> StringUtils.hasText(target.getFilterCssSelector())
							? target.getFilter().isAllowed(element.select(target.getFilterCssSelector()).text().trim())
							: true)
					.map(element -> StringUtils.hasText(target.getTargetCssSeletor())
							? element.select(target.getTargetCssSeletor()).text()
							: element.text())
					.collect(Collectors.toList());
		} catch (IOException e) {
			log.error("target : {}", target, e);
			return Collections.emptyList();
		}
	}
}

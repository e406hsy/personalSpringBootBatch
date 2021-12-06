package com.soonyong.hong.batch.crawl.filter;

import java.time.LocalTime;
import java.util.regex.Pattern;

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
public class PatternMatchFilter implements CrawlFilter {

	private Pattern pattern;

	@Override
	public boolean isAllowed(String value) {

		log.debug("is allowed called with value {}", value);
		boolean result = pattern.matcher(value).matches();
		log.debug("and result : {}", result);
		return result;
	}

}

package com.soonyong.hong.batch.crawl.filter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.soonyong.hong.batch.crawl.filter.LocalTimeBaseCrawlFilter.Type;

public class LocalTimeBaseCrawlFilterTest {

	@Test
	public void LocalTimeTest() {
		CrawlFilter filter = LocalTimeBaseCrawlFilter.builder()
				.formatter(DateTimeFormatter.ofPattern("HH-mm-ss"))
				.interval(10)
				.unit(ChronoUnit.MINUTES)
				.type(Type.AFTER)
				.build();
		
		assertTrue(filter.isAllowed("00-00-00"));
	}
	
	@Test
	public void LocalTimeMinusTest() {
		LocalTime.now().plus(-1,ChronoUnit.MINUTES).isBefore(LocalTime.now());
	}
	
	@Test
	public void LocalTimeBaseTest() {
		LocalTime.now().minus(23,ChronoUnit.HOURS).minus(55, ChronoUnit.MINUTES).isBefore(LocalTime.now());
	}
}

package com.soonyong.hong.batch.crawl.filter.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.soonyong.hong.batch.crawl.filter.Impl.LocalTimeBaseStringComparater;
import com.soonyong.hong.batch.crawl.filter.Impl.LocalTimeBaseStringComparater.Type;

public class LocalTimeBaseCrawlFilterTest {

	@Test
	public void LocalTimeTest() {
		Predicate<String> filter = LocalTimeBaseStringComparater.builder()
				.formatter(DateTimeFormatter.ofPattern("HH-mm-ss")).zoneId(ZoneId.of("Asia/Seoul")).interval(10)
				.unit(ChronoUnit.MINUTES).type(Type.AFTER).build();

		assertTrue(filter.test("00-00-00"));
	}

	@Test
	public void LocalTimeMinusTest() {
		LocalTime.now().plus(-1, ChronoUnit.MINUTES).isBefore(LocalTime.now());
	}

	@Test
	public void LocalTimeBaseTest() {
		LocalTime.now().minus(23, ChronoUnit.HOURS).minus(55, ChronoUnit.MINUTES).isBefore(LocalTime.now());
	}

	@Test
	public void LocalTimeBaseTestWithZone() {
		LocalTime.now(ZoneId.of("Asia/Seoul")).isAfter(LocalTime.of(0, 0, 0));
	}

	@Test
	public void LocalTimeBaseTestWithZone2() {
		LocalTime.now(ZoneId.of("Asia/Seoul")).isBefore(LocalTime.of(23, 59, 55));
	}
}

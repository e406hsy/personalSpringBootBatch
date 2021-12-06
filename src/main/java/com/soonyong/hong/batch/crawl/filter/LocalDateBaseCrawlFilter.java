package com.soonyong.hong.batch.crawl.filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalUnit;
import java.util.function.BiPredicate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateBaseCrawlFilter implements CrawlFilter {

	private DateTimeFormatter formatter;
	private boolean allowInvalidPattern;
	private int interval;
	private TemporalUnit unit;
	private Type type;

	@Override
	public boolean isAllowed(String value) {
		LocalDate targetTime;
		try {
			targetTime = LocalDate.parse(value, formatter);
		} catch (DateTimeParseException e) {
			log.info("invalid pattern found", e);
			return allowInvalidPattern;
		}
		return type.compare(LocalDate.now().plus(interval, unit), targetTime);
	}

	@RequiredArgsConstructor
	public enum Type {
		AFTER((v1, v2) -> v1.isAfter(v2)), BEFORE((v1, v2) -> v2.isAfter(v1)), EQUALS((v1, v2) -> v1.equals(v2));

		private final BiPredicate<LocalDate, LocalDate> comparater;

		public boolean compare(LocalDate time1, LocalDate time2) {
			return comparater.test(time1, time2);
		}
	}
}

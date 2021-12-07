package com.soonyong.hong.batch.crawl.filter.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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
public class LocalDateBaseStringComparater implements Predicate<String> {

	private DateTimeFormatter formatter;
	private ZoneId zoneId;
	private int interval;
	private TemporalUnit unit;
	private Type type;

	@Override
	public boolean test(String value) {
		LocalDate targetTime;
		targetTime = LocalDate.parse(value, formatter);
		log.debug("is allowed called with value {}", value);
		boolean result = type.compare(LocalDate.now(zoneId).minus(interval, unit), targetTime);
		log.debug("and result : {}", result);
		return result;
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

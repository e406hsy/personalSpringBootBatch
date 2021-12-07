package com.soonyong.hong.batch.crawl.filter.Impl;

import java.time.LocalTime;
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
public class LocalTimeBaseStringComparater implements Predicate<String> {

	private DateTimeFormatter formatter;
	private int interval;
	private TemporalUnit unit;
	private Type type;

	@Override
	public boolean test(String value) {
		LocalTime targetTime;
		targetTime = LocalTime.parse(value, formatter);

		log.debug("is allowed called with value {}", value);
		boolean result = type.compare(LocalTime.now().plus(interval, unit), targetTime);
		log.debug("and result : {}", result);
		return result;
	}

	@RequiredArgsConstructor
	public enum Type {
		AFTER((v1, v2) -> v1.isAfter(v2)), BEFORE((v1, v2) -> v2.isAfter(v1)), EQUALS((v1, v2) -> v1.equals(v2));

		private final BiPredicate<LocalTime, LocalTime> comparater;

		public boolean compare(LocalTime time1, LocalTime time2) {
			return comparater.test(time1, time2);
		}
	}
}

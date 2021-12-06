package com.soonyong.hong.batch.config.crwal;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.soonyong.hong.batch.crawl.filter.CrawlFilterChain;
import com.soonyong.hong.batch.crawl.filter.CrawlFilterChain.DelegateCondition;
import com.soonyong.hong.batch.crawl.filter.LocalTimeBaseCrawlFilter;
import com.soonyong.hong.batch.crawl.filter.LocalTimeBaseCrawlFilter.Type;
import com.soonyong.hong.batch.crawl.filter.PatternMatchFilter;
import com.soonyong.hong.batch.crawl.model.CrawlTarget;

public class CrawlTargetFactory {

	private static final Map<String, CrawlTarget> crawlTargetMap;

	static {
		crawlTargetMap = new HashMap<>();
		add(CrawlTarget.builder().title("ppomppu")
				.url("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu&hotlist_flag=999")
				.baseCssSeletor("#revolution_main_table > tbody > tr[align=\"center\"]")
				.filterCssSelector("td:nth-child(4) > nobr")
				.filter(CrawlFilterChain.builder()
						.delegate(PatternMatchFilter.builder().pattern(Pattern.compile("\\d\\d:\\d\\d:\\d\\d")).build())
						.delegateCondition(DelegateCondition.AND)
						.next(LocalTimeBaseCrawlFilter.builder().formatter(DateTimeFormatter.ofPattern("HH:mm:ss"))
								.interval(12).unit(ChronoUnit.HOURS).type(Type.BEFORE).build())
						.build())
				.targetCssSeletor("td:nth-child(3) > table > tbody > tr > td:nth-child(2) > div > a > font").build());
		add(CrawlTarget.builder().title("ppomppu_foreign")
				.url("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu4&hotlist_flag=999")
				.baseCssSeletor("#revolution_main_table > tbody > tr[align=\"center\"]")
				.filterCssSelector("td:nth-child(4) > nobr")
				.filter(CrawlFilterChain.builder()
						.delegate(PatternMatchFilter.builder().pattern(Pattern.compile("\\d\\d:\\d\\d:\\d\\d")).build())
						.delegateCondition(DelegateCondition.AND)
						.next(LocalTimeBaseCrawlFilter.builder().formatter(DateTimeFormatter.ofPattern("HH:mm:ss"))
								.interval(12).unit(ChronoUnit.HOURS).type(Type.BEFORE).build())
						.build())
				.targetCssSeletor("td:nth-child(3) > table > tbody > tr > td:nth-child(2) > div > a > font").build());
	}

	public static CrawlTarget get(String title) {
		return crawlTargetMap.get(title);
	}

	private static void add(CrawlTarget target) {
		crawlTargetMap.put(target.getTitle(), target);
	}
}

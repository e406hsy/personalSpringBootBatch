package com.soonyong.hong.batch.config.crwal;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.soonyong.hong.batch.crawl.filter.Impl.CrawlFilterChain;
import com.soonyong.hong.batch.crawl.filter.Impl.CrawlFilterChain.DelegateCondition;
import com.soonyong.hong.batch.crawl.filter.Impl.LocalTimeBaseStringComparater;
import com.soonyong.hong.batch.crawl.filter.Impl.LocalTimeBaseStringComparater.Type;
import com.soonyong.hong.batch.crawl.filter.Impl.PatternMatchStringComparater;
import com.soonyong.hong.batch.crawl.filter.Impl.SelectedTextFilterAdapter;
import com.soonyong.hong.batch.crawl.model.CrawlTarget;

public class CrawlTargetFactory {

	private static final Map<String, CrawlTarget> crawlTargetMap;

	static {
		crawlTargetMap = new HashMap<>();
		add(CrawlTarget.builder().title("ppomppu")
				.url("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu&hotlist_flag=999")
				.baseCssSeletor("#revolution_main_table > tbody > tr[align=\"center\"]")
				.filter(CrawlFilterChain.builder()
						.delegate(SelectedTextFilterAdapter.builder().cssSelector("td:nth-child(4) > nobr")
								.delegate((value) -> PatternMatchStringComparater.builder()
										.pattern(Pattern.compile("\\d\\d:\\d\\d:\\d\\d")).build()
										.and(LocalTimeBaseStringComparater.builder()
												.formatter(DateTimeFormatter.ofPattern("HH:mm:ss")).interval(8)
												.unit(ChronoUnit.HOURS).type(Type.BEFORE).build())
										.test(value))
								.build())
						.delegateCondition(DelegateCondition.AND)
						.next(SelectedTextFilterAdapter.builder().cssSelector("td:nth-child(5)")
								.delegate(PatternMatchStringComparater.builder()
										.pattern(Pattern.compile("(1[5-9]|[2-9]\\d)\\s*-\\s*0")).build())
								.build())
						.build())
				.targetCssSeletor("td:nth-child(3) > table > tbody > tr > td:nth-child(2) > div > a > font").build());
		add(CrawlTarget.builder().title("ppomppu_foreign")
				.url("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu4&hotlist_flag=999")
				.baseCssSeletor("#revolution_main_table > tbody > tr[align=\"center\"]")
				.filter(CrawlFilterChain.builder()
						.delegate(SelectedTextFilterAdapter.builder().cssSelector("td:nth-child(4) > nobr")
								.delegate((value) -> PatternMatchStringComparater.builder()
										.pattern(Pattern.compile("\\d\\d:\\d\\d:\\d\\d")).build()
										.and(LocalTimeBaseStringComparater.builder()
												.formatter(DateTimeFormatter.ofPattern("HH:mm:ss")).interval(8)
												.unit(ChronoUnit.HOURS).type(Type.BEFORE).build())
										.test(value))
								.build())
						.delegateCondition(DelegateCondition.AND)
						.next(SelectedTextFilterAdapter.builder().cssSelector("td:nth-child(5)")
								.delegate(PatternMatchStringComparater.builder()
										.pattern(Pattern.compile("\\d\\d\\s*-\\s*0")).build())
								.build())
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

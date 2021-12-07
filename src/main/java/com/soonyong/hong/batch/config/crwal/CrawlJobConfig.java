package com.soonyong.hong.batch.config.crwal;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soonyong.hong.batch.crawl.job.WebCrawlTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class CrawlJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final WebCrawlTasklet webCrawlTasklet;
	private final Step notificationStep;

	@Bean
	public Job crawlJob() {
		return jobBuilderFactory.get("crawlJob").start(webCrawlStep()).on("hook").to(notificationStep)
				.from(webCrawlStep()).on("COMPLETED").end().build().build();
	}

	@Bean
	public Step webCrawlStep() {
		return stepBuilderFactory.get("firstStep").tasklet(webCrawlTasklet).build();
	}
}

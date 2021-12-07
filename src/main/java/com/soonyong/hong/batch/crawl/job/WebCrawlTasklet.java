package com.soonyong.hong.batch.crawl.job;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.soonyong.hong.batch.crawl.service.CrawlService;
import com.soonyong.hong.batch.notification.DoorayMessageNotificationTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
@RequiredArgsConstructor
public class WebCrawlTasklet implements Tasklet {

	private final CrawlService crawlService;
	@Value("#{jobParameters[title]}")
	private String title;
	@Value("#{jobParameters[hookUrl]}")
	private String hookUrl;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<String> result = crawlService.getTexts(title);

		log.info("crawl result : {}", result);
		if (!result.isEmpty()) {
			chunkContext.setAttribute(DoorayMessageNotificationTasklet.HOOK_URL_ATTRIBUTE_KEY, hookUrl);
			chunkContext.setAttribute(DoorayMessageNotificationTasklet.HOOK_MESSAGE_ATTRIBUTE_KEY, result);
			contribution.setExitStatus(new ExitStatus("hook"));
		}
		return RepeatStatus.FINISHED;
	}

}

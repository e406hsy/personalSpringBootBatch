package com.soonyong.hong.batch.notification;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
public class DoorayMessageNotificationTasklet implements Tasklet {

	public static final String HOOK_URL_ATTRIBUTE_KEY = "hookUrl";
	public static final String HOOK_MESSAGE_ATTRIBUTE_KEY = "hookMessage";

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Object message = chunkContext.getAttribute(HOOK_MESSAGE_ATTRIBUTE_KEY);
		String hookUrl = (String) chunkContext.getAttribute(HOOK_URL_ATTRIBUTE_KEY);
		log.debug("hookUrl {}. message {}", hookUrl, message);
		return RepeatStatus.FINISHED;
	}

}

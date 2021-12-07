package com.soonyong.hong.batch.config.notification;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soonyong.hong.batch.notification.DoorayMessageNotificationTasklet;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class NotificationConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final DoorayMessageNotificationTasklet notificationTasklet;

	@Bean
	public Step notificationStep() {

		return stepBuilderFactory.get("notificationStep").tasklet(notificationTasklet).build();
	}
}

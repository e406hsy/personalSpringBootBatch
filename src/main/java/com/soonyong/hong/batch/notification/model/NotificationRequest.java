package com.soonyong.hong.batch.notification.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationRequest {
	private final String botName;
	private final String text;
}

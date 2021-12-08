package com.soonyong.hong.batch.notification.service;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soonyong.hong.batch.notification.model.NotificationRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public void notify(String hookUrl, String message) throws IOException, HttpException {
		log.info("notification requested with message {}", message);
		try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
			HttpPost httpPost = new HttpPost(hookUrl);

			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(objectMapper
					.writeValueAsString(NotificationRequest.builder().botName("My Noti").text(message).build())));
			try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
				if (httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new HttpException("http failed with response : " + httpResponse);
				}
			}
		}
	}

}

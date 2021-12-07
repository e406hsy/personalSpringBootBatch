package com.soonyong.hong.batch.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBatchTest
public class DoorayMessageNotificationTaskletTest {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@BeforeEach
	public void clearMetadata() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	public void testJob() throws Exception {
		// given
		JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParameters();

		// when
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("webCrawlStep", jobParameters);

		// then
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
}

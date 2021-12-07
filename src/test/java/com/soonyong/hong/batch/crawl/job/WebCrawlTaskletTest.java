package com.soonyong.hong.batch.crawl.job;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBatchTest
public class WebCrawlTaskletTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Autowired
	private Job crawlJob;

	@BeforeEach
	public void clearMetadata() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	public void testJob() throws Exception {
		// given
		JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParametersBuilder().addString("title", "ppomppu")
				.addString("hookUrl", "test.com").toJobParameters();

		jobLauncherTestUtils.setJob(crawlJob);
		// when
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

		// then
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
}

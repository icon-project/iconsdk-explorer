package com.dfg.icon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.dfg.icon.config.DataSourceConfig;


@SpringBootApplication
@EnableScheduling
@ComponentScan(
		basePackages = "com.dfg.icon",
		excludeFilters =
		@ComponentScan.Filter(
				type = FilterType.ASPECTJ,
				pattern = {"com.dfg.icon.web.v0.controller"}
				)
		)
@Import({DataSourceConfig.class})
public class Application {

	private static String SERVER_URL;


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Value("${server.url}")
	private void setIcxUrl(String serverUrl) {
		SERVER_URL = serverUrl;
	}
}

package com.sainsburys.scraper;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.sainsburys.scraper.controllers.MainController;

@SpringBootApplication
public class SainsburysProductWebScraper
{

    @Autowired
    private MainController mainController;
	
	public static void main(String[] args)
	{
		final ConfigurableApplicationContext context = SpringApplication.run(SainsburysProductWebScraper.class);
        final SainsburysProductWebScraper webScraperApp = context.getBean(SainsburysProductWebScraper.class);

        System.out.println();
        webScraperApp.scrape();
		System.out.println();

		SpringApplication.exit(context);
	}

	private void scrape()
	{
			mainController.scrapeWebPageData(getUriDataForUrl());
	}
	
	private URI getUriDataForUrl()
	{
		return UriComponentsBuilder.fromHttpUrl(StringAllocator.fullUrl).build().toUri();
	}
}

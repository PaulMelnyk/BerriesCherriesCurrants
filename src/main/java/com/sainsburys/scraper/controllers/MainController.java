package com.sainsburys.scraper.controllers;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sainsburys.scraper.productModels.ProductDataObjectModel;
import com.sainsburys.scraper.productDataScraperService.ProductDataScraperService;

@Component
public class MainController
{
	@Autowired
	private ProductDataScraperService scraperService;
	
    public void scrapeWebPageData(final URI uri)
    {
    	try
    	{
    		ProductDataObjectModel productDataObjectModel = scraperService.getProductDataModels(uri);
    		System.out.println(new ObjectMapper().writeValueAsString(productDataObjectModel));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}

package com.sainsburys.scraper.productModels;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sainsburys.scraper.productDataScraperService.ProductDataScraperServiceUtility;
import org.jsoup.nodes.Document;

import static com.sainsburys.scraper.StringAllocator.*;

@JsonPropertyOrder(value = {modelTitleProperty, modelKcalProperty,
		modelUnitPriceProperty, modelDescriptionProperty})
public class ResultJsonProductDataObjectModel
{
	@NotNull
	@JsonProperty(modelTitleProperty)
	private String title;
	
	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ExcludeKcalFieldWhenUnavailable.class)
	@JsonProperty(modelKcalProperty)
	private int kcalPerHundredGrams;
	
	@NotNull
	@JsonProperty(modelUnitPriceProperty)
	private Double unitPrice;
	
	@NotNull
	@JsonProperty(modelDescriptionProperty)
	private String description;

	public void setAllValues(Document productPage) {
		setTitle(ProductDataScraperServiceUtility.getScrapedProductTitle(productPage));
		setKcalPerHundredGrams(ProductDataScraperServiceUtility.getScrapedProductKcalPer100g(productPage));
		setUnitPrice(ProductDataScraperServiceUtility.getScrapedProductUnitPrice(productPage));
		setDescription(ProductDataScraperServiceUtility.getScrapedProductDescription(productPage));
	}
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getKcalPerHundredGrams() {
		return kcalPerHundredGrams;
	}

	public void setKcalPerHundredGrams(int kcalPerHundredGrams) {
		this.kcalPerHundredGrams = kcalPerHundredGrams;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}

package com.sainsburys.scraper.productDataScraperService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.sainsburys.scraper.productModels.ProductDataObjectModel;
import com.sainsburys.scraper.productModels.ResultJsonProductDataObjectModel;

import static com.sainsburys.scraper.StringAllocator.*;

@Service
public class ProductDataScraperService
{

	public ProductDataObjectModel getProductDataModels(URI uri) throws IOException
	{
		Document webPage = Jsoup.connect(uri.toURL().toString()).get();
		Set<String> productLinks = retrieveAllLinks(webPage);

		return convertScrapedProductsToJson(productLinks);
	}

	private ProductDataObjectModel convertScrapedProductsToJson(final Set<String> scrapedProductData) throws IOException
	{
		ProductDataObjectModel productDataObjectModel = new ProductDataObjectModel();
		List<ResultJsonProductDataObjectModel> scrapedProductDataModels = new ArrayList<>();
		Double total = new Double(0);
		Double vat = new Double(0);

		for (String productLink : scrapedProductData)
		{
			Document productPage = Jsoup.connect(productLink).get();

			ResultJsonProductDataObjectModel scrapedObject = new ResultJsonProductDataObjectModel();
			scrapedObject.setAllValues(productPage);

			scrapedProductDataModels.add(scrapedObject);
			if (scrapedObject.getUnitPrice() != null)
			{
				total += scrapedObject.getUnitPrice();
				vat += scrapedObject.getUnitPrice() * vatPercentage;
			}
		}

		productDataObjectModel.setAllValues(scrapedProductDataModels, total, vat);

		return productDataObjectModel;
	}

	private Set<String> retrieveAllLinks(Document webPage) {
		return webPage.select(scraperServiceLinkObjectSelector).stream()
				.map(element -> element.attr(scraperServiceLinkHrefObjectSelector))
				.map(element -> baseUrl + element.replaceFirst(linkClearDownPatternRegex, ""))
				.filter(element -> element.contains(urlProductCategory))
				.collect(Collectors.toSet());
	}
}

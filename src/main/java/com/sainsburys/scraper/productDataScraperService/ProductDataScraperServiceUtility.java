package com.sainsburys.scraper.productDataScraperService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import static com.sainsburys.scraper.StringAllocator.*;

@Service
public class ProductDataScraperServiceUtility
{

	public static String getScrapedProductTitle(final Document productDocument) {
		Elements productTitle = productDocument.select(scraperServiceProductTitleSelector);
		return !productTitle.isEmpty() ? productTitle.get(0).text() : null;
	}

	public static int getScrapedProductKcalPer100g(final Document productDocument) {
		Elements productKcalPer100g = productDocument.select(scraperServiceProductKcalPer100gSelector);

		return  !productKcalPer100g.isEmpty() ? formatKcalToInt(productKcalPer100g) : 0;
	}

	public static Double getScrapedProductUnitPrice(final Document productDocument) {
		Elements productUnitPrice = productDocument.select(scraperServiceProductPriceSelector);

		return !productUnitPrice.isEmpty() ? formatPrice(productUnitPrice) : null;
	}

	public static String getScrapedProductDescription (final Document productDocument) {
		List<Element> filteredProductDescription;
		Elements productDescription = productDocument.select(scraperServiceProductDescriptionContainerSelector);

		if(productDescription.isEmpty()) {
			productDescription = productDocument.select(scraperServiceProductDescriptionMultiLineSelector);
			productDescription.removeIf(element -> (element.text().equals("")));

			filteredProductDescription = productDescription;

		} else  {
			filteredProductDescription = singleDescriptionFilter(productDescription);
		}

		if(!filteredProductDescription.isEmpty()) {
			boolean descriptionIsBlank = productDescription.get(0).nextElementSibling() == null || productDescription.get(0).nextElementSibling().text().isEmpty();

			if(!descriptionIsBlank)  {
				productDescription = productDescription.get(0).nextElementSibling().select(scraperServiceProductDescriptionSingleLineSelector);
			}

			if(!productDescription.isEmpty()) {
				return productDescription.get(0).text();
			}
		}

		return null;
	}

	private static int formatKcalToInt(Elements productKcalPer100g) {
		String trimKcal = productKcalPer100g.get(0).text().replace("kcal", "");

		if(trimKcal.contains(".")) {
			return Math.round(Float.parseFloat(trimKcal));
		}

		return Integer.parseInt(trimKcal);
	}

	private static Double formatPrice(Elements productUnitPrice) {
		String price = productUnitPrice.get(0).text();

		final Matcher matcher = Pattern.compile(PricePatternRegex).matcher(price);

		return matcher.find() ? new Double(Double.parseDouble(matcher.group())) : null;
	}

	private static List<Element> singleDescriptionFilter(Elements descriptionElements) {
		return descriptionElements.stream().filter(elem -> "Description".equals(elem.text()))
				.collect(Collectors.toList());
	}
}

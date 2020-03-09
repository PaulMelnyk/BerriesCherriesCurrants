package com.sainsburys.scraper;

public class StringAllocator {

    public static final String resultProperty = "results";

    public static final String totalProperty = "total";

    public static final String vatProperty = "vat";

    public static final String modelTitleProperty = "title";

    public static final String modelKcalProperty = "kcalPer100g";

    public static final String modelUnitPriceProperty = "unitPrice";

    public static final String modelDescriptionProperty = "description";

    public static final String scraperServiceProductTitleSelector = "div.productSummary div.productTitleDescriptionContainer h1";

    public static final String scraperServiceProductPriceSelector = "div.productSummary div.priceTabContainer div.pricing p.pricePerUnit";

    public static final String PricePatternRegex = "\\d\\.\\d+";

    public static final String scraperServiceProductDescriptionContainerSelector = "h3.productDataItemHeader";

    public static final String scraperServiceProductDescriptionSingleLineSelector = "div.productText p";

    public static final String scraperServiceProductDescriptionMultiLineSelector = "div.itemTypeGroupContainer.productText p";

    public static final String scraperServiceProductKcalPer100gSelector = "div.productText div.tableWrapper table.nutritionTable tr.tableRow0 td.nutritionLevel1";

    public static final String baseUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/";

    public static final String fullUrl = baseUrl + "webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static final String urlProductCategory = "berries-cherries-currants";

    public static final String linkClearDownPatternRegex = "(../)+";

    public static final String scraperServiceLinkObjectSelector = "ul.productLister a";

    public static final String scraperServiceLinkHrefObjectSelector = "href";

    public static final double vatPercentage = 0.2;

}

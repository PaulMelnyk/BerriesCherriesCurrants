package com.sainsburys.scraper.productDataScraperService;

import org.hamcrest.CoreMatchers;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.constraints.AssertFalse;

import static com.sainsburys.scraper.StringAllocator.scraperServiceProductDescriptionContainerSelector;
import static com.sainsburys.scraper.StringAllocator.scraperServiceProductDescriptionMultiLineSelector;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductDataScraperServiceUtilityTest {

    private static final String TEST_TAG = "testTag";

    private static final String TEST_PRODUCT_STRING = "testProductString";

    private static final String TEST_PRODUCT_NUMBER = "5.5";

    private static final String DESCRIPTION_FIRST_LINE = "First Line Of Description";

    private static final String DESCRIPTION_SECOND_LINE = "Second Line Of Description";

    private static final Double TEST_NUMBER_CONVERT = Double.parseDouble(TEST_PRODUCT_NUMBER);

    private Elements populateStringElement = populateElements(0);

    private Elements populatedNumberElement = populateElements(1);

    private Elements populateDescriptionElement = populateElements(2);

    @Mock
    public Document testDocument;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnNullWhenTitleMissing() {
        // Given
        when(testDocument.select(anyString())).thenReturn(new Elements());

        // When
        String result = ProductDataScraperServiceUtility.getScrapedProductTitle(testDocument);

        // Then
        assertNull(result);
    }

    @Test
    public void shouldReturnTestProductTitleWhenProductPopulated() {
        // Given
        when(testDocument.select(anyString())).thenReturn(populateStringElement);

        // When
        String result = ProductDataScraperServiceUtility.getScrapedProductTitle(testDocument);

        // Then
        assertEquals(TEST_PRODUCT_STRING, result);
    }

    @Test
    public void shouldReturn0WhenKcalMissing() {
        // Given
        when(testDocument.select(anyString())).thenReturn(new Elements());

        // When
        int result = ProductDataScraperServiceUtility.getScrapedProductKcalPer100g(testDocument);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnValueWithKcalTrimmedOff() {
        // Given
        when(testDocument.select(anyString())).thenReturn(populatedNumberElement);

        // When
        int result = ProductDataScraperServiceUtility.getScrapedProductKcalPer100g(testDocument);

        // Then
        assertEquals(convertDoubleToInt(), result);
    }

    @Test
    public void shouldReturnNullWhenProductPriceMissing() {
        // Given
        when(testDocument.select(anyString())).thenReturn(populateStringElement);

        // When
        Double result = ProductDataScraperServiceUtility.getScrapedProductUnitPrice(testDocument);

        // Then
        assertNull(result);
    }

    @Test
    public void shouldReturnDoubleWhenProductPricePopulated() {
        // Given
        when(testDocument.select(anyString())).thenReturn(populatedNumberElement);

        // When
        Double result = ProductDataScraperServiceUtility.getScrapedProductUnitPrice(testDocument);

        // Then
        assertEquals(TEST_NUMBER_CONVERT, result);

    }

    @Test
    public void shouldReturnNullIfProductDescriptionMissing() {
        // Given
        when(testDocument.select(anyString())).thenReturn(populateStringElement);

        // When
        String result = ProductDataScraperServiceUtility.getScrapedProductDescription(testDocument);

        // Then
        assertNull(result);
    }

    @Test
    public void shouldReturnFirstLineOfDescriptionOnly() {
        // Given
        when(testDocument.select(scraperServiceProductDescriptionContainerSelector)).thenReturn(new Elements());
        when(testDocument.select(scraperServiceProductDescriptionMultiLineSelector)).thenReturn(populateDescriptionElement);

        // When
        String result = ProductDataScraperServiceUtility.getScrapedProductDescription(testDocument);

        // Then
        assertThat(result, CoreMatchers.containsString(DESCRIPTION_FIRST_LINE));
        assertThat(result, not(CoreMatchers.containsString(DESCRIPTION_SECOND_LINE)));
    }

    private int convertDoubleToInt() {
        return Math.round(Float.parseFloat(TEST_PRODUCT_NUMBER));
    }

    private Elements populateElements(int type) {
        Elements populate = new Elements();

        Element containsData = new Element(TEST_TAG);

        switch (type) {
            case 0:
                containsData.text(TEST_PRODUCT_STRING);
                addToElements(populate, containsData);
                break;
            case 1:
                containsData.text(TEST_PRODUCT_NUMBER);
                addToElements(populate, containsData);
                break;
            case 2:
                containsData.text(DESCRIPTION_FIRST_LINE);
                Element secondDescriptionLine = new Element(TEST_TAG);
                secondDescriptionLine.text(DESCRIPTION_SECOND_LINE);
                addToElements(populate, containsData, secondDescriptionLine);
                break;
        }

        return populate;
    }

    private void addToElements(Elements populate, Element... elements) {
        for(Element e: elements) {
            populate.add(e);
        }
    }
    
    
}
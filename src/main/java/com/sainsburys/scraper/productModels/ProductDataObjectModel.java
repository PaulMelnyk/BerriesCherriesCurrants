package com.sainsburys.scraper.productModels;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import static com.sainsburys.scraper.StringAllocator.*;

@JsonPropertyOrder(value = { resultProperty, totalProperty})
public class ProductDataObjectModel
{

    @NotNull
    @NotEmpty
    @JsonProperty(resultProperty)
    private List<ResultJsonProductDataObjectModel> results;

    @NotNull
    @JsonProperty(totalProperty)
    private Double totalPrice;

    @NotNull
	@JsonProperty(vatProperty)
	private Double vatPrice;

    public void setAllValues(List<ResultJsonProductDataObjectModel> results, Double total, Double vat) {
    	setResults(results);
    	setTotalPrice(total);
    	setVatPrice(vat);
	}

	public List<ResultJsonProductDataObjectModel> getResults() {
		return results;
	}

	public void setResults(List<ResultJsonProductDataObjectModel> results) {
		this.results = results;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getVatPrice() {
		return vatPrice;
	}

	public void setVatPrice(Double vatPrice) {
		this.vatPrice = vatPrice;
	}
}

package com.sainsburys.scraper.productModels;

public class ExcludeKcalFieldWhenUnavailable {

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.equals(0)) {
            return true;
        }

        return false;
    }
}

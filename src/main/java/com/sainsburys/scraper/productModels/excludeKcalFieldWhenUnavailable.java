package com.sainsburys.scraper.productModels;

public class excludeKcalFieldWhenUnavailable {

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.equals(0)) {
            return true;
        }

        return false;
    }
}

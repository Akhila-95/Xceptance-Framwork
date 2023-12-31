package com.xceptance.loadtest.posters.models.components.homepage;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;

/**
 * Promoted products component.
 * 
 * @author Xceptance Software Technologies
 */
public class PromotedProducts implements Component
{
	public static final PromotedProducts instance = new PromotedProducts();

    @Override
    public LookUpResult locate()
    {
       // return Page.find().byCss("#productList");
        return Page.find().byCss("div.homepage");

    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}
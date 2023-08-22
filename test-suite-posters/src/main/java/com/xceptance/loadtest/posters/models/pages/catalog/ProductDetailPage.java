package com.xceptance.loadtest.posters.models.pages.catalog;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import org.htmlunit.html.HtmlElement;
import com.xceptance.common.util.RegExUtils;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

/**
 * Represents a product detail page.
 *
 * @author Xceptance Software Technologies
 */
public class ProductDetailPage extends GeneralPages
{
	public static final ProductDetailPage instance = new ProductDetailPage();
	
    @Override
    public void validate()
    {
        super.validate();

     //   Assert.assertTrue("Product detail page validation failed", Page.find().byId("addToCartForm").exists());
    //    Assert.assertTrue("Product detail page validation failed", Page.find().byId("titleProductName").exists());
        
        Assert.assertTrue("Product detail page validation failed", Page.find().byXPath("//span[contains(text(),'Add to Cart')]").exists());
        Assert.assertTrue("Product detail page validation failed", Page.find().byCss("h1.d-sm-block").exists());
    }

    @Override
    public boolean is()
    {
    	return super.is() &&
    		//	Page.find().byId("addToCartForm").exists() &&
    		//	Page.find().byId("titleProductName").exists();
    	
		    	Page.find().byXPath("//span[contains(text(),'Add to Cart')]").exists() &&
				Page.find().byCss("h1.d-sm-block").exists(); 
    }
    
    public boolean isProductAvailable()
    {
    	// NOTE
    	// This is not implemented for the posters demo shop. At the moment all products are always in stock.
    	// There could be some dedicated in stock and availability logic here which needs to be handled.
    	return true;
    }
    
    public List<HtmlElement> getVariationAttributes()
    {
    	// Returns all configurable variation attributes
    	
    	return Page.find().byId("main").byXPath(".//div[contains(@class, 'form-group') and contains(@class, 'row') and not(./p[@id='prodDescriptionDetail'])]").all();
    
    }
    
    public HtmlElement getAddToCartButton()
    {
    	// Returns add to cart button
    	return Page.find().byCss("div.add-update-btns a.add-to-cart").asserted("Failed to find add to cart button for given product item").single();
    }
    
    public String getProductId()
    {

        String productId = Page.find().byCss("div.add-update-btns a.add-to-cart").asserted("Expected add to cart button").single().getAttribute("data-pid");
        System.out.println("Product id " + productId);
         
        productId = RegExUtils.getFirstMatch(productId, "([A-Za-z0-9]+)", 1);
          
        System.out.println("Product id " + productId);
        Assert.assertTrue("Expected valid productId", !StringUtils.isBlank(productId));
        
        return productId;
    }
    
    public String getSelectedSize()
    {
        String selectedSize = Page.find().byId("selectSize").asserted("Expected size attribute").single().getAttribute("value");
        
        Assert.assertTrue("Expected valid size attribute that is selected", !StringUtils.isBlank(selectedSize));
        
        return selectedSize;
    }
    
    public String getSelectedFinish()
    {
    	String selectedFinish = Page.find().byCss("#addToCartForm input[name=finish]:checked").asserted("Expected selected finish attribute").single().getAttribute("value");
    	
    	Assert.assertTrue("Expected valid finish attribute that is selected", !StringUtils.isBlank(selectedFinish));
    	
    	return selectedFinish;
    }
}
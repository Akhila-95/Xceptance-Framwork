package com.xceptance.loadtest.posters.actions.cart;

import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.devtools.v109.domsnapshot.model.NameValue;

import java.util.ArrayList;
import java.util.List;

//import org.apache.http.NameValuePair;
import org.htmlunit.WebResponse;
import org.htmlunit.util.NameValuePair;

import com.xceptance.loadtest.api.actions.AjaxAction;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

/**
 * Adds the product items of the current product page to the cart.
 * 
 * @author Xceptance Software Technologies
 */
public class AddToCart extends AjaxAction<AddToCart>
{
    private int previousCartQuantity;
    
    private String productId;
    
    private String size;
    
    private String finish;

    @Override
    public void precheck()
    {
        // Retrieve current quantity
        previousCartQuantity = GeneralPages.instance.miniCart.getQuantity();

        // Retrieve PID
        productId = ProductDetailPage.instance.getProductId();
        
        // Retrieve selected size
     //   size = ProductDetailPage.instance.getSelectedSize();
        
        // Retrieve selected finish
       // finish = ProductDetailPage.instance.getSelectedFinish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	//This line initializes a list named AddToCartParams of type NameValuePair. It's intended to hold parameters that will be sent in the HTTP POST request to add a product to the cart.
    	 final List<NameValuePair> AddToCartParams  = new ArrayList<NameValuePair>();   	

    	 //Here, two parameters are added to the AddToCartParams list. These parameters are pid (product ID) and quantity (set to "1"). These parameters will likely be used to identify the product and specify the quantity to be added to the cart.
    	 AddToCartParams.add((NameValuePair) new NameValuePair("pid", productId));
    	 AddToCartParams.add((NameValuePair) new NameValuePair("quantity", "1"));

           HttpRequest req = new HttpRequest()
        		   //(XMLHttpRequest) 
                       .XHR()

                       .url("/on/demandware.store/Sites-fireMountainGems-Site/default/Cart-AddProduct")

                      .POST()    
                      
                      .postParams(AddToCartParams);

           //Sending the Request
           		WebResponse response=req.fire();


    	JSONObject addToCartJson = AjaxUtils.convertToJson(response.getContentAsString());

    	var error = addToCartJson.get("error").toString();

        boolean isError = Boolean.parseBoolean(error);

        if(!isError) {

            Context.get().data.totalAddToCartCount++;
         }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
    	// Nothing to validate
    }
}
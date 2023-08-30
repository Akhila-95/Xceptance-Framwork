package com.xceptance.loadtest.posters.actions.checkout;

import java.util.ArrayList;
import java.util.List;

import org.htmlunit.WebResponse;
import org.htmlunit.util.NameValuePair;
import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.account.LoginPage;
import com.xceptance.loadtest.posters.models.pages.checkout.PaymentPage;
import com.xceptance.loadtest.posters.models.pages.checkout.ShippingAddressPage;

/**
 * Handles the shipping address page.
 * 
 * @author Xceptance Software Technologies
 */
public class CheckoutShippingAddress extends PageAction<CheckoutShippingAddress>
{
	private Account account;
	
	public CheckoutShippingAddress()
	{
		this(Context.get().data.getAccount().get());
	}
	
	public CheckoutShippingAddress(Account account)
	{
		this.account = account;
	}
	
	@Override
	public void precheck()
	{
		super.precheck();

		// Validate that we have the shipping address form
		Assert.assertTrue("Expected shipping address form", ShippingAddressPage.shippingAddressForm.exists());
	}
	
	@Override
	protected void doExecute() throws Exception
	{
		// Fill shipping address form
		//ShippingAddressPage.shippingAddressForm.fillForm(account);
		 String csrf=ShippingAddressPage.shippingAddressForm.GetCsrf();
	
   	 final List<NameValuePair> Params  = new ArrayList<NameValuePair>();   	
   	 Params.add((NameValuePair) new NameValuePair("dwfrm_coCustomer_email", "akhila.m@etg.digital"));
   	 Params.add((NameValuePair) new NameValuePair("csrf_token", csrf));

          HttpRequest req = new HttpRequest()

                      .XHR()

                      .url("/on/demandware.store/Sites-fireMountainGems-Site/default/CheckoutServices-SubmitCustomer")

                     .POST()    
                     
                     .postParams(Params);

          		WebResponse response=req.fire();

		// Click continue button
		loadPageByClick(ShippingAddressPage.shippingAddressForm.getContinueButton());
		
		
		//shipping addressss....
		
		 
			
	   	 final List<NameValuePair> Params1  = new ArrayList<NameValuePair>();   	
	   	 Params1.add((NameValuePair) new NameValuePair("dwfrm_coCustomer_email", "akhila.m@etg.digital"));
	   	 Params1.add((NameValuePair) new NameValuePair("csrf_token", csrf));
	   	 
	   	 Params1.add((NameValuePair) new NameValuePair("dwfrm_coCustomer_email", "akhila.m@etg.digital"));
	   	 Params1.add((NameValuePair) new NameValuePair("csrf_token", csrf));

	          HttpRequest req1 = new HttpRequest()

	                      .XHR()

	                      .url("/on/demandware.store/Sites-fireMountainGems-Site/default/CheckoutShippingServices-SubmitShipping")

	                     .POST()    
	                     
	                     .postParams(Params1);

	          		WebResponse response1=req1.fire();

		
	}

	@Override
	protected void postValidate() throws Exception
	{
        Validator.validatePageSource();

        // Validate that it is the payment page
        PaymentPage.instance.validate();
	}
}
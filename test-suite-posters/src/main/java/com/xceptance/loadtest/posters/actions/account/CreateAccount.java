package com.xceptance.loadtest.posters.actions.account;

import java.util.ArrayList;
import java.util.List;

import org.htmlunit.WebResponse;
import org.htmlunit.util.NameValuePair;
import org.json.JSONObject;
import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.account.CreateAccountPage;
import com.xceptance.loadtest.posters.models.pages.account.LoginPage;

/**
 * Creates a new account.
 * 
 * @author Xceptance Software Technologies
 */
public class CreateAccount extends PageAction<CreateAccount>
{
	private String firstname;	
	private String lastname;
	private String phonenumber;
	private String email;
	private String password;
	private String confirmpassword;
	private String csrf;
    private final Account account;

    public CreateAccount(final Account account)
    {
        this.account = account;
    }

    @Override
    protected void doExecute() throws Exception
    {
       // CreateAccountPage.instance.createAccountForm.fillCreateAccountForm(account);
       
        //loadPageByClick(CreateAccountPage.instance.createAccountForm.getCreateAccountButton());
        final List<NameValuePair> createAccount = new ArrayList<NameValuePair>();

		createAccount.add(new NameValuePair("dwfrm_profile_customer_firstname", firstname));
		createAccount.add(new NameValuePair("dwfrm_profile_customer_lastname", lastname));
		createAccount.add(new NameValuePair("dwfrm_profile_customer_phone",  phonenumber));
		createAccount.add(new NameValuePair("dwfrm_profile_customer_email",  email));
		createAccount.add(new NameValuePair("dwfrm_profile_login_password",  password));
		createAccount.add(new NameValuePair("dwfrm_profile_login_passwordconfirm", confirmpassword));
		createAccount.add(new NameValuePair("csrf_token", csrf));
		
            HttpRequest req = new HttpRequest()

                        .XHR()

                        .url("/on/demandware.store/Sites-fireMountainGems-Site/default/CheckoutServices-SubmitCustomer")

                       .POST()             

                        .postParams(createAccount);

            WebResponse response1=req.fire();

            JSONObject createaccount = AjaxUtils.convertToJson(response1.getContentAsString());
            /*
            //Assert.assertEquals("Responce expected",response1.getContentAsString());

          //  String shipmetUId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("shipmentUUID");

           // String UId=new JSONObject(response1.getContentAsString()).getJSONObject("order").getJSONObject("items").getJSONArray("items").getJSONObject(0).getString("UUID");

          

            // Fill shipping address form

            CreateAccountPage.createAccountForm.fillCreateAccountForm(account);

            final List<NameValuePair> parms = new ArrayList<NameValuePair>();

            // parms.add(new NameValuePair("originalShipmentUUID", shipmetUId));

             //parms.add(new NameValuePair("shipmentUUID", shipmetUId));

             parms.add(new NameValuePair("dwfrm_profile_customer_firstname", account.firstname));

             parms.add(new NameValuePair("dwfrm_profile_customer_lastname", account.lastname));

             parms.add(new NameValuePair("dwfrm_profile_customer_phone", account.phonenumber));

             parms.add(new NameValuePair("dwfrm_profile_customer_email", "+"+account.email));             

             parms.add(new NameValuePair("dwfrm_profile_login_password", account.password));

             parms.add(new NameValuePair("dwfrm_profile_login_passwordconfirm", account.confirmpassword));
             */
             

    }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();

        Assert.assertTrue("Failed to register. Expected login form.", LoginPage.instance.loginForm.exists());        

        account.isRegistered = true;
    }
}
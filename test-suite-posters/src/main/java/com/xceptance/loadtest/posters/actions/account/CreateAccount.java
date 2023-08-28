package com.xceptance.loadtest.posters.actions.account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    private final Account account;

    public CreateAccount(final Account account)
    {
        this.account = account;
    }

    @Override
    protected void doExecute() throws Exception
    {
    	 CreateAccountPage.createAccountForm.fillCreateAccountForm(account);
         String csrf=CreateAccountPage.createAccountForm.GetCsrf();
       
        //loadPageByClick(CreateAccountPage.instance.createAccountForm.getCreateAccountButton());
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
         Date date1 = new Date();
         String timestamp = dateFormat.format(date1);
         Random randomGenerator = new Random();  
         int randomInt = randomGenerator.nextInt(10000);
         String Emailadress=account.firstname+account.lastname+"+"+timestamp+randomInt+"@gmail.com";

         final List<NameValuePair> createAccount = new ArrayList<NameValuePair>();

         createAccount.add(new NameValuePair("dwfrm_profile_customer_email", Emailadress));

		createAccount.add(new NameValuePair("dwfrm_profile_customer_firstname", account.firstname));
		createAccount.add(new NameValuePair("dwfrm_profile_customer_lastname", account.lastname));
		createAccount.add(new NameValuePair("dwfrm_profile_customer_phone",  account.phonenumber));		
		createAccount.add(new NameValuePair("dwfrm_profile_login_password",  account.password));
		createAccount.add(new NameValuePair("dwfrm_profile_login_passwordconfirm", account.password));
		createAccount.add(new NameValuePair("csrf_token", csrf));
		
            HttpRequest req = new HttpRequest()

                        .XHR()

                        .url("/on/demandware.store/Sites-fireMountainGems-Site/default/Account-SubmitRegistration?rurl=1")

                       .POST()             

                        .postParams(createAccount);

          
            WebResponse response=req.fire();
              
       
            String url = "";
            if(response.getStatusCode()==200)
              url=new JSONObject(response.getContentAsString()).getString("redirectUrl");
            else
              Assert.fail(response.getStatusMessage());
              loadPageByUrlClick(url);
            
         }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();

        Assert.assertTrue("Failed to register. Expected login form.", LoginPage.instance.loginForm.exists());        

        account.isRegistered = true;
    }
}
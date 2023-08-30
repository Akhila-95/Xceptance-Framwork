package com.xceptance.loadtest.posters.actions.account;

import java.util.ArrayList;
import java.util.List;

import org.htmlunit.WebResponse;
import org.htmlunit.util.NameValuePair;
import org.json.JSONObject;
import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.account.CreateAccountPage;
import com.xceptance.loadtest.posters.models.pages.account.LoginPage;
import com.xceptance.loadtest.posters.models.pages.general.HomePage;

/**
 * Logs in with the given account.
 * 
 * @author Xceptance Software Technologies
 */
public class Login extends PageAction<Login>
{
    private final Account account;

    public Login(final Account account)
    {
        this.account = account;
    }

    @Override
    protected void doExecute() throws Exception
    {
       
        
       // loadPageByClick(LoginPage.instance.loginForm.getSignInButton());
        //LoginPage.loginForm.fillLoginForm(account);
        String csrf=LoginPage.loginForm.GetCsrf();
 
       final List<NameValuePair> login = new ArrayList<NameValuePair>();

       login.add(new NameValuePair("loginEmail","bhaskarrao.s@etggs.com" ));
       login.add(new NameValuePair("loginPassword", "Bhaskaretg@123"));     
       login.add(new NameValuePair("g-recaptcha-action", "login"));  
       login.add(new NameValuePair("csrf_token", csrf));
		
       HttpRequest req1 = new HttpRequest()

               .XHR()

               .url("/on/demandware.store/Sites-fireMountainGems-Site/default/Account-Login")

               .POST()             

               .postParams(login);

    WebResponse response=req1.fire();
    System.out.println( response);
    String url = "";

    if(response.getStatusCode()==200) {
    	System.out.println(url);
     url=new JSONObject(response.getContentAsString()).getString("redirectUrl");
     System.out.println("rggegd"+ url);
    }
    else
      Assert.fail(response.getStatusMessage());
 
     loadPageByUrlClick(url);
    
    }

    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();
        
        Assert.assertTrue("User is not logged in", HomePage.instance.user.isLoggedIn());
    }
}
package com.xceptance.loadtest.posters.models.components.account;

import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlForm;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.FormUtils;

/**
 * LoginForm component.
 * 
 * @author Xceptance Software Technologies
 */
public class LoginForm implements Component
{
	public static final LoginForm instance = new LoginForm();

    @Override
    public LookUpResult locate()
    {
       // return Page.find().byId("formLogin");
        return Page.find().byCss(".login-title");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public HtmlElement getSignInButton()
    {
    	return locate().byXPath("//button[contains(text(),'Login')]").asserted().single();
    }

    public HtmlElement fillLoginForm(final Account account)
    {
        final  HtmlElement  form = locate().asserted().single();

        FormUtils.setInputValue(HPU.find().in(form).byId("login-form-email-modal-login"), account.email);
        FormUtils.setInputValue(HPU.find().in(form).byId("login-form-password-modal-login"), account.password);

        return form;
    }
    public String GetCsrf()
    {
    	 final HtmlElement  element = HPU.find().in(locate().single()).byXPath("(//input[@type='hidden'][@name='csrf_token'])[2]").first();
    	 return element.getAttribute("value");
    }
}
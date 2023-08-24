package com.xceptance.loadtest.posters.models.components.account;

import org.htmlunit.html.HtmlElement;
//import org.htmlunit.html.HtmlForm;
import  org.htmlunit.html.HtmlDivision;
import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.FormUtils;

/**
 * Create account form component.
 * 
 * @author Xceptance Software Technologies
 */
public class CreateAccountForm implements Component
{
	public static final CreateAccountForm instance = new CreateAccountForm();

    @Override
    public LookUpResult locate()
    {
        //return Page.find().byId("formRegister");
        return Page.find().byCss("div.login-page");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public HtmlElement getCreateAccountButton()
    {
    	//return locate().byCss("#btnRegister").asserted().single();
    	return locate().byCss(".registration button.btncreate-account").asserted().single();
    }

    public void fillCreateAccountForm(final Account account)
    {
        final HtmlElement form = locate().asserted().single();

        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-fname"), account.firstname);        
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-lname"), account.lastname);
       
        
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-email"), account.email);

        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-password"), account.password);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-password-confirm"), account.password);
        FormUtils.setInputValue(HPU.find().in(form).byId("registration-form-phone"), account.phonenumber);
    }
}
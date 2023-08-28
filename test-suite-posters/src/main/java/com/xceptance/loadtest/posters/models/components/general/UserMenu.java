package com.xceptance.loadtest.posters.models.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;

/**
 * User menu component.
 * 
 * @author Xceptance Software Technologies
 */
public class UserMenu implements Component
{
	public static final UserMenu instance = new UserMenu();

    @Override
    public LookUpResult locate()
    {
        //return Header.instance.locate().byCss("#userMenu");
        return Header.instance.locate().byCss(".login-section");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public LookUpResult getLoginLink()
    {
       // return locate().byCss("a.goToLogin");
        return locate().byCss("a.login-button");
    }
    
    public LookUpResult getCreateAccountLink()
    {
    	//return locate().byCss("a.goToRegistration");
    	return locate().byCss("a.create-button");
    }

    public LookUpResult getMyAccountLink()
    {
      //  return locate().byCss("a.goToAccountOverview");
        return locate().byCss("[alt='person-icon']");
    }

    public LookUpResult getLogoutLink()
    {
      //  return locate().byCss("a.goToLogout");
    	return locate().byCss("div.overlay-account-create div.text-right a");
    }
    
    public boolean isLoggedIn()
    {
        return getLogoutLink().exists();
    }

    public boolean isNotLoggedIn()
    {
        return !isLoggedIn();
    }
}
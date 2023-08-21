package com.xceptance.loadtest.posters.models.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.Context;

/**
 * Navigation component.
 * 
 * @author Xceptance Software Technologies
 */
public class Navigation implements Component
{
	public static final Navigation instance = new Navigation();

    @Override
    public LookUpResult locate()
    {
      //  return Header.instance.locate().byCss("#categoryMenu");
        return Header.instance.locate().byCss("section.main-menu>div.container");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public LookUpResult getTopCategories()
    {
       // return filterinvalidLinks(locate().byCss(".header-menu-item a.topCategoryMenuItem"));
        return filterinvalidLinks(locate().byCss("li.level-2>a"));
    }

    public LookUpResult getCategories()
    {
        //return filterinvalidLinks(locate().byCss(".header-menu-item a:not(.topCategoryMenuItem)"));
        return filterinvalidLinks(locate().byCss("li.level-3>a"));
    }

    private LookUpResult filterinvalidLinks(final LookUpResult links)
    {
        return links.filter(Page.VALIDLINKS)
                        .discard(Context.configuration().filterCategoryUrls.unweightedList(),
                        e -> e.getAttribute("href"));
    }
}
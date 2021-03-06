/*
 * #%L
 * share-po
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.po.share.dashlet;

import org.alfresco.po.HtmlPage;
import org.alfresco.po.RenderTime;
import org.alfresco.po.RenderWebElement;
import org.alfresco.po.exception.PageOperationException;
import org.alfresco.po.share.ShareDialogue;
import org.alfresco.po.share.site.SiteDashboardPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * Site Notice dashlet Dialogue box object, holds all element of the HTML relating to site Notice dashlet Dialogue box.
 *
 * @author Chiran
 */
public class ConfigureSiteNoticeDialogBoxPage extends ShareDialogue
{
    private final Log logger = LogFactory.getLog(ConfigureSiteNoticeDialogBoxPage.class);

    @RenderWebElement
    private static final By CONFIGURE_SITE_NOTICE_DIALOG_BOX = By.cssSelector("div[id$='default-configDialog-configDialog']");
    @RenderWebElement
    private static final By TITLE_BOX = By.cssSelector("input[name='title']");
    @RenderWebElement
    private static final By OK_BUTTON = By.cssSelector("button[id$='default-configDialog-ok-button']");
    @RenderWebElement
    private static final By CANCEL_BUTTON = By.cssSelector("button[id$='default-configDialog-cancel-button']");
    @RenderWebElement
    private static final By CLOSE_BUTTON = By.cssSelector("a.container-close");

    @SuppressWarnings("unchecked")
    @Override
    public synchronized ConfigureSiteNoticeDialogBoxPage render(RenderTime timer)
    {
        webElementRender(timer);
        return this;

    }

    @SuppressWarnings("unchecked")
    @Override
    public ConfigureSiteNoticeDialogBoxPage render()
    {
        return render(new RenderTime(maxPageLoadingTime));
    }
    ConfigureSiteNoticeTinyMceEditor configureSiteNoticeTinyMceEditor;
    /**
     * Get Rich Text Editor to edit the contents of Comments etc.
     *
     * @return ConfigureSiteNoticeTinyMceEditor
     */
    public ConfigureSiteNoticeTinyMceEditor getContentTinyMceEditor()
    {
        return configureSiteNoticeTinyMceEditor;
    }

    /**
     * This method sets the given text into Site Content Configure text editor.
     *
     * @param text String
     */
    public void setText(String text)
    {
        getContentTinyMceEditor().setText(text);
    }

    /**
     * This method is used to Finds OK button and clicks on it.
     *
     * @return {@link SiteDashboardPage}
     */
    public HtmlPage clickOnOKButton()
    {
        try
        {
            findAndWait(OK_BUTTON).click();
            waitUntilAlert(1);
            return getCurrentPage();
        }
        catch (TimeoutException te)
        {
            logger.error("Unable to find the OK button.", te);
            throw new PageOperationException("Unable to click the OK Button.");
        }
    }

    /**
     * This method is used to Finds Cancel button and clicks on it.
     */
    public void clickOnCancelButton()
    {
        try
        {
            findAndWait(CANCEL_BUTTON).click();
        }
        catch (TimeoutException te)
        {
            logger.error("Unable to find the CANCEL button.", te);
            throw new PageOperationException("Unable to click the CANCEL Button.");
        }
    }

    /**
     * This method is used to Finds Close button and clicks on it.
     */
    public void clickOnCloseButton()
    {
        try
        {
            findAndWait(CLOSE_BUTTON).click();
        }
        catch (TimeoutException te)
        {
            logger.error("Unable to find the CLOSE button.", te);
            throw new PageOperationException("Unable to click the CLOSE Button.");
        }
    }

    /**
     * This method sets the given title into Site Content Configure title box.
     *
     * @param title String
     */
    public void setTitle(String title)
    {
        if (title == null)
        {
            throw new IllegalArgumentException("Title is required");
        }

        try
        {
            WebElement titleBox = findAndWait(TITLE_BOX);
            titleBox.clear();
            titleBox.sendKeys(title);
        }
        catch (TimeoutException te)
        {
            logger.error("Unable to find the Title box.", te);
            throw new PageOperationException("Unable to find the Title box.");
        }
    }

    @Override
    public String getTitle()
    {
        try
        {
            WebElement titleBox = findAndWait(TITLE_BOX);
            return titleBox.getAttribute("value");
        }
        catch (TimeoutException te)
        {
            logger.error("Unable to find the Title box.", te);
            throw new PageOperationException("Unable to find the Title box.");
        }
    }
}

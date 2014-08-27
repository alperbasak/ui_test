package com.oksijen.lbs.lbas.functest.pages

import geb.Page

/**
 * Created by cpekyaman on 3/25/2014.
 */
class WelcomePage extends LocatePage {
    static url = "welcome"
    static at = { logoutBtn.displayed == true && tabsHolder.find('ul.ui-tabs-nav').size() > 0 }

    static content = {
        logoutBtn(required: true) 		{ $("a#btn_logout") }
        accountBtn(required: true) 		{ $("a#btn_username") }
        adminBtn(required: false) 		{ $("a#btn_admin") }
		privacyTerms(required: true)	{ $('a#btn_privacy_terms')}

        globalSearch(required: true)	{ $("input#inpt_search") }

        tabsHolder(required: true)		{ $("div.tabsHolder") }

        mapMenu(required: true) 		{ $('a#btn_map') }
        inboxMenu(required: true) 		{ $('a#btn_messages') }
		calendarMenu(required: true)	{ $('a#btn_calendar') }
        privacyMenu(required: true)		{ $('a#btn_privacy') }
		
		locateLogo(required:true) 		{$("div.logo")}
		popupInbox(required: false) 	{$('ul.tab-access li',0)}
		defaultVisibility(required: false)	{$('div').last().find('li').last()}
		requestsTab (required: false)	{ $("li.tab-requests") }
		locationReject(reqired: false)		{$('button#inboxLocationRequestRejectButton')}
		locationAccept(required: false)		{$('button#inboxLocationRequestAcceptButton')}

    }
}
package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage


/**
 * Created by
 */
class InboxPopupMenu extends WelcomePage {
    static url = "welcome"
    static at = { popupTabs.displayed == true && popupMenu.displayed==true}

    static content = {
		inboxPopup(required: true) { $('div.s div#dialog') }
		popupTabs(required: true)  {$('ul.tab-access')}
		requestsTab (required: false)	{ $("li.tab-requests") }
		popupMenu(required:true)		{$('.menu-popup')}
				
		locationReject(reqired: false)		{$('button#inboxLocationRequestRejectButton')}
		locationAccept(required: false)		{$('button#inboxLocationRequestAcceptButton')}
		defaultVisibility(required: false)	{$('div').last().find('li').last()}
		
    }
}

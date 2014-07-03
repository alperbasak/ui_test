package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage


/**
 * Created by
 */
class InboxPopupMenu extends WelcomePage {
    static url = "welcome"
    static at = { popupTabs.displayed == true }

    static content = {
		inboxPopup(required: true) { $('div.s div#dialog') }
		popupTabs(required: true)  {$('ul.tab-access')}
		requestsTab (required: false)	{ $("li.tab-requests") }
		
    }
}

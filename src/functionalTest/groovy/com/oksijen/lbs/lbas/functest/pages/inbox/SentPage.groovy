package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage
/**
 * 
 */
class SentPage extends InboxHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
		sentMessages (required: true)	{ $('a.tab-sent-messages') }
		sentRequests (required: true)	{ $('a.tab-sent-requests') }
		sentTab (required: true)		{ $("li.tab-messages") }
		requestsTab (required: true)	{ $("li.tab-requests") }
		
    }
}

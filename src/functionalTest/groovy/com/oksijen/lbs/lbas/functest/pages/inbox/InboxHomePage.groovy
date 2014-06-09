package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class InboxHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
       mainDiv(required: true) 	{ $("div#messages") }
        leftPanel(required: true) 	{ $("div#inbox") }
        rightPanel(required: true) 	{ $("div#messageDiv") }
		
		messagesTab (required: true)	{ $("a#tab-mex") }
		requestsTab (required: true)	{ $("a.tab-inbox-requests") }
		inboxMessages (required: true)	{ $("a#tab-inbox-messages") }
		
		requestsInbox (required: false)	{ $("div#tab-inbox-requests") }
		
		searchMessage(required:true){ $("input#searchMessageInput") }
		
		inboxPanel(required: false) 	{ $('a.inbox span') }
		sentPanel(required: false) 	{ $('a.sent span')  }
		trashPanel(required: false) 	{ $('a.trash span')  }
    }
}

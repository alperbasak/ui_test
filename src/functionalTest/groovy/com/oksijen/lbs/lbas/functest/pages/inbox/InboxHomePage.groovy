package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
class InboxHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#messages") }
        leftPanel(required: true) 	{ $("div#inbox") }
        rightPanel(required: true) 	{ $("div#messageDiv") }
		
		messages (required: true)	{ $("a#tab-mex") }
		requests (required: true)	{ $("a.tab-inbox-requests") }
		
		searchMessage(required:true){ $("a#searchMessageArea") }
		
		inboxMenu(required: true) 	{ $("li.INBOX_menu") }
		sentMenu(required: true) 	{ $("li.SENT_menu") }
		trashMenu(required: true) 	{ $("li.TRASH_menu") }
    }
}

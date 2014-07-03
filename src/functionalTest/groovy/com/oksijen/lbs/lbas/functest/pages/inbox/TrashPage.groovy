package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage
/**
 * 
 */
class TrashPage extends InboxHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
		mainDiv(required: true) 		{ $("div#tab-trash") }
		trash (required: true)	{ $('a#tab-trash') }
    }
}

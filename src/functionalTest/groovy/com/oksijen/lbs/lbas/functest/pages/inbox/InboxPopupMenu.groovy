package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by
 */
class InboxPopupMenu extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
		
		mainDiv(required: true) 	{ $("a#btn_logout") }
		inboxPopup(required: false) { $('div.menu-popup-wrapper') }
    }
}

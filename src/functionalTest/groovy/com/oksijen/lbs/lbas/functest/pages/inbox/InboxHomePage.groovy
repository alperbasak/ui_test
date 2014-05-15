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
    }
}

package com.oksijen.lbs.lbas.functest.pages.privacy

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
class PrivacyHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#privacy") }
        leftPanel(required: true) 	{ $("div#visibility") }
        rightPanel(required: true) 	{ $("div#availabilityDiv") }
    }
}

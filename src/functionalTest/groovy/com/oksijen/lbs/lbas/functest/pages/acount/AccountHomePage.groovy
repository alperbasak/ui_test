package com.oksijen.lbs.lbas.functest.pages.account

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
class AccountHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#account") }
        leftPanel(required: true) 	{ $("div#accountNav") }
        rightPanel(required: true) 	{ $("div#accountContent") }
    }
}

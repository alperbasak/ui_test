package com.oksijen.lbs.lbas.functest.pages.privacyterms

import com.oksijen.lbs.lbas.functest.pages.WelcomePage


class PrivacyTermsHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#PrivacyStatements") }
        leftPanel(required: true) 	{ $("div#privacyStatementLeft") }
        rightPanel(required: true) 	{ $("div#privacyStatementBody") }
    }
}

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
		
		personalDetails(required: true) {$('ul.accountMenu li',0)}
		password(required: true)		{$('ul.accountMenu li',1)}
		groups(required: true)			{$('ul.accountMenu li',2)}
		timeZone(required: true)		{$('ul.accountMenu li',3)}
		units(required: true)			{$('ul.accountMenu li',4)}
		temp(required: true)			{$('ul.accountMenu li',5)}
		workAddress(required: true)		{$('ul.accountMenu li',6)}
		lang(required: true)			{$('ul.accountMenu li',7)}
    }
}

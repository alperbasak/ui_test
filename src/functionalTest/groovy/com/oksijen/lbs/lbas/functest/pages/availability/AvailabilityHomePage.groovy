package com.oksijen.lbs.lbas.functest.pages.availability

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class AvailabilityHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 			{ $("div#privacy") }
        leftPanel(required: true) 			{ $("div#visibility") }
        rightPanel(required: true) 			{ $("div#availabilityDiv") }
		visibilityStatus(required: false)	{ $('a#visibilityStatus-button')}
		notVisible(required: false)			{$('ul#visibilityStatus-menu li',1)}
		visible(required: false)			{$('ul#visibilityStatus-menu li',0)}
    }
}

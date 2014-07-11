package com.oksijen.lbs.lbas.functest.pages.availability

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class WhomILocatePage extends AvailabilityHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 			{$("div#privacy") }
        leftPanel(required: true) 			{$("div#visibility") }
        rightPanel(required: true) 			{$("div#whomILocate-container") }
		allPermission(required:false)		{$('table#whomILocateMe').find('tr.makeHover')}
		permPermission(required:false)		{$('table#whomILocateMe').find('tr.makeHover', type: "permanent")}
		tempPermission(required:false)		{$('table#whomILocateMe').find('tr.makeHover', type: "temporary")}
		
		filterBy(required:false)			{$('a#requestType-button')}
		allFilter(required:false)			{$("ul#requestType-menu li",0)}
		permFilter(required:false)			{$("ul#requestType-menu li",1)}
		tempFilter(required:false)			{$("ul#requestType-menu li",2)}

		profileInfo(required:false)			{$('div#calendarX')}

	    }
}


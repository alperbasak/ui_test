package com.oksijen.lbs.lbas.functest.pages.availability

import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.specs.LocateSpec

/**
 * 
 */
class LocateMePage extends AvailabilityHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 			{$("div#privacy") }
        leftPanel(required: true) 			{$("div#visibility") }
        rightPanel(required: true) 			{$("div#whoLocate-container") }
		allPermission(required:false)		{$('table#whoLocateMe').find('tr.makeHover td.eight',0)}
		cagPermission(required:false)		{$('table#whoLocateMe').find('tr.makeHover',username:'cagdas turkdogan').find('td.eight')}										
		alpPermission(required:false)		{$('table#whoLocateMe').find('tr.makeHover',username:startsWith('Alper Ba')).find('td.eight')}
		permPermission(required:false)		{$('table#whoLocateMe').find('tr.makeHover', type: "permanent")}
		tempPermission(required:false)		{$('table#whoLocateMe').find('tr.makeHover', type: "temporary")}
		
		filterBy(required:false)			{$('a#requestType-button')}
		allFilter(required:false)			{$("ul#requestType-menu li",0)}
		permFilter(required:false)			{$("ul#requestType-menu li",1)}
		tempFilter(required:false)			{$("ul#requestType-menu li",2)}
		
		closeView(required:false)			{$('a.ui-dialog-titlebar-close')}
		profileInfo(required:false)			{$('div#calendarX')}
		
		sendBtn(required:false)				{$('div#dialog').find('button.send')}
	
	    }
}


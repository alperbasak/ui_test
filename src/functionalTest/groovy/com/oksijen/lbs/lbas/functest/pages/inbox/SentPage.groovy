package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage
/**
 * 
 */
class SentPage extends InboxHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
		mainDiv(required: true) 		{ $("div#messages") }
		sentMessages (required: true)	{ $('a.tab-sent-messages') }
		sentRequests (required: true)	{ $('a.tab-sent-requests') }
		sentTab (required: true)		{ $("li.tab-messages") }
		requestsTab (required: true)	{ $("li.tab-requests") }
		pendingMsg(required: false)		{$('table#sentList').find('tr.inboxTbTRRowCell').has('td', status:"false").find('input')}
		acceptedMsg(required: false)	{$('table#sentList').find('tr.inboxTbTRRowCell').has('td', status:"true").find('input')}
		sendReminder(required: false)	{$('div#tab-sent-requests ul li',2).children()}
		deleteReminder(required: false)	{$('div#tab-sent-requests ul li',1).children()}
		successSent(required: false) 	{ $("div.successMessageCheck") }
		errorSent(required: false) 		{$('div.ui-dialog')}
    }
}

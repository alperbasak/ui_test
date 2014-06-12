package com.oksijen.lbs.lbas.functest.pages.inbox

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class InboxHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
		mainDiv(required: true) 		{ $("div#messages") }
        leftPanel(required: true) 		{ $("div#inbox") }
        rightPanel(required: true) 		{ $("div#messageDiv") }
		
		inboxPanel(required: false) 	{ $('li.INBOX-menu') }
		sentPanel(required: false) 		{ $('li.SENT-menu')  }
		trashPanel(required: false) 	{ $('li.TRASH-menu')  }
		
		messagesTab (required: true)	{ $("a#tab-mex") }
		requestsTab (required: true)	{ $("li.tab-requests") }
			
		searchMessage(required:true)	{ $("input#searchMessageInput") }
		
		newMessage(required:true)		{ $("li.newMsg")}
		toInput(required: false) 		 { $("input#token-input-messageTo") }
		subjectInput(required: false) 	 { $("input#messageSubject") }
		messageInput(required: false) 	 { $("textarea#messageContent") }
		
		addressList(required: false)		{ $('div.token-input-dropdown-facebook') }
		addressListItems(required: false)	{ addressList.find('ul').children()}
		
		successSent(required: false) 		{ $("div.successMessageCheck") }
		errorMessage(required: false)		{$("a.js-error-message") }
		errorMessageDetail(required: false)	{$("div.tooltip-alert-generic-left")}
		
		markBtns(required: false)			{$("a.inboxActionLink")}
		markAsRead(required: false)			{markBtns.find('mark-read')}
		markAsUnread(required: false)		{markBtns.find('mark-unread')}
		markDelete(required: false)			{markBtns.find('mark-delete')}
    }
}

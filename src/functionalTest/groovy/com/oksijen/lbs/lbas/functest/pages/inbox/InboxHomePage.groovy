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
		
		inboxPanel(required: false) 	{ $('li.INBOX_menu') }
		sentPanel(required: false) 		{ $('li.SENT_menu')  }
		trashPanel(required: false) 	{ $('li.TRASH_menu')  }
		
		messagesTab (required: true)	{ $("a#tab-mex") }
		requestsTab (required: false)	{ $("li.tab-requests") }
			
		searchMessage(required:true)	{ $("input#searchMessageInput") }
		
		newMessage(required:true)		{ $("li.newMsg")}
		toInput(required: false) 		 { $("input#token-input-messageTo") }
		subjectInput(required: false) 	 { $("input#messageSubject") }
		messageInput(required: false) 	 { $("textarea#messageContent") }
		messageDetail(required: false)		{$("div#messageDetail")}
						
		addressList(required: false)		{ $('div.token-input-dropdown-facebook ul') }
		addressListItems(required: false)	{ addressList.children()}
		
		successSent(required: false) 		{ $("div.successMessageCheck") }
		errorMessage(required: false)		{$("ul#send-list-wrapper li a") }
		errorMessageDetail(required: false)	{$("div.tooltip-alert-generic-left")}
		
		selectAllBox(required: false)		{$("li.selectAll")}
		markAsRead(required: false)			{$("div#tab-inbox-messages ul li").eq(2).children()}
		markAsUnread(required: false)		{$("div#tab-inbox-messages ul li").eq(3).children()}
		markDelete(required: false)			{$("div#tab-inbox-messages ul li").eq(4).children()}
		markDeleteTrash(required: false)	{$("div#tab-trash ul li").eq(1).children()}
		backToInbox(required: false)		{$("div#tab-trash ul li").eq(2).children()}
		
		replyBtn(required: false)			{$("ul.messageTools.clearfix li").eq(3)}
		replyAllBtn(required: false)		{$("ul.messageTools.clearfix li").eq(2)}
		forwardBtn(required: false)			{$("ul.messageTools.clearfix li").eq(1)}
		deleteBtn(required: false)			{$("ul.messageTools.clearfix li").eq(0)}
		
		cancelTrash(required: false)		{$("div.ui-dialog").find('button.cancel')}
		deleteTrash(required: false)		{$("div.ui-dialog").find('button.send')}
		
    }
}

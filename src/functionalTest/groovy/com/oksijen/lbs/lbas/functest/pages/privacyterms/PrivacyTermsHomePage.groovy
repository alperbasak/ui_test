package com.oksijen.lbs.lbas.functest.pages.privacyterms

import com.oksijen.lbs.lbas.functest.pages.WelcomePage


class PrivacyTermsHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#PrivacyStatements") }
        leftPanel(required: true) 	{ $("div#privacyStatementLeft") }
        rightPanel(required: true) 	{ $("div#privacyStatementBody") }
		vdfnPrivacyTab(required: true)	{$('ul.privacyStatementMenu li',0)}
		companyPrivacyTab(required: true)	{$('ul.privacyStatementMenu li',1)}
		successDialog(required: false)		{$('div.ui-dialog').last()}
		showAtLogin(required:false)			{$('#dontShowPrivacySetting')}
		showAtLoginCompany(required:false)	{$('#dontShowPrivacySettingCompany')}
		companyPrivacyPage(required:false) {$('div#privacyStatementBox2')}
		
    }
}

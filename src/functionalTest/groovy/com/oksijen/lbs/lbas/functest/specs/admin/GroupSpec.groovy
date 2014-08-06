package com.oksijen.lbs.lbas.functest.specs.admin

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.admin.*


/**
 * 
 */
@Stepwise
class GroupSpec extends LocateSpec {
    
def "Group Management Page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Admin tab"
	adminBtn.click()
	waitFor { at AdminHomePage }
		
	then: "Click Group Management and page should render"
	groupMan.click()
	waitFor { rightPanel.find('h1').text().contains('Gr') }
	}

def "Create a group, select one admin"(){
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I select Add Group from dropdown and click apply"
	selectMenu.click()
	addGroup.click()
	apply.click()
	
	then:"New group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewGroup1"
	groupPermissionsTab.click()
	waitFor {permissions.displayed==true}
	
	def checkboxes= permissions.find('input',type:"checkbox")
	checkboxes[0].click()
	checkboxes[1].click()
	checkboxes[2].click()
	checkboxes[3].click()
	checkboxes[4].click()
	checkboxes[5].click()
	checkboxes[6].click()
	checkboxes[7].click()
	checkboxes[8].click()
	
	and: "Check for only one admin and save"
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==1
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}

@Ignore
def "Create a group, select multi admin"(){							////DEVELOPMENT NEEDED
	given:"We are at Group Management Page"															
	at AdminHomePage
	
	when:"I select Add Group from dropdown and click apply"
	selectMenu.click()
	addGroup.click()
	apply.click()
	
	then:"New group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewGroup2"
	groupPermissionsTab.click()
	waitFor {permissions.displayed==true}
	
	def checkboxes= permissions.find('input',type:"checkbox")
	checkboxes[0].click()
	checkboxes[1].click()
	checkboxes[2].click()
	checkboxes[3].click()
	checkboxes[4].click()
	checkboxes[5].click()
	checkboxes[6].click()
	checkboxes[7].click()
	checkboxes[8].click()
	
	and: "Add more than one admin and save"
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert allAdmins.size()>4
	interact{
		keyDown(Keys.SHIFT)	
	allAdmins[0].click()
	allAdmins[3].click()
		keyUp(Keys.SHIFT)
	}
	arrowAdd.click()
	
	waitFor('fast') {groupAdminUsers.size()==5}
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}

@Ignore
def "Edit group, remove one admin from group admins"(){									////DEVELOPMENT NEEDED
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin group"
	$('td.name',text:'NewGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I remove one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	groupAdminUsers[0].click()
	arrowRemove.click()
	waitFor('fast')	{groupAdminUsers.size()==4}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}

@Ignore
def "Edit group, add one admin to group admins"(){									////DEVELOPMENT NEEDED
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin group"
	$('td.name',text:'NewGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I add one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	addAdmins[0].click()
	arrowAdd.click()
	waitFor('fast')	{groupAdminUsers.size()==5}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}


}
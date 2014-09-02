package com.oksijen.lbs.lbas.functest.specs.admin

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
@Ignore
@Stepwise
class SecondAssetAdminSpec extends LocateSpec {
  
	def "Add another admin to an asset group"(){
		given:"We are at asset management page"
		at WelcomePage
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
		groupMan.click()
		at AdminHomePage
		
		when:
		$('td.name',text:'AA').parent().find('a.btn-update').click()
		
		then:"Dialog opens and I add one admin"
		waitFor {groupDialog.displayed==true}
		groupAdminTab.click()
		waitFor {admin.displayed==true}
		assert groupAdminUsers.size()==1
		allAdmins[0].click()
		arrowAdd.click()
		waitFor('fast')	{groupAdminUsers.size()==2}
		
		and:"Save"
		sendDialog.click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
	}
		
	def "Logout and login with 2nd admin"(){
		given:"We are at Group Management Page"
		at AdminHomePage
		$('a#btn_logout').click()
		waitFor {at LoginPage}
			
		when:"I login with 2nd admin"
		username='admin1'
		password=params.get('password')
		loginButton.click()
		
		then:
		waitFor {at WelcomePage}
		
		}
	
	 def "Select an asset group and locate them all with new admin"(){
		given: "We are at the AssetsPage"
		at MapHomePage
		assetsTab.click()
		waitFor {at AssetsPage}
		
		when:"I select a group and click Locate"
		assetGroup.click()
		$('a#btn_tab-assets_showOnMap').click()
		
		then:"Tooltip should be displayed"
		waitFor('fast') { tooltip.displayed == true }
		if (tooltip.find('span.globalSearchText').text()=='Show'){
			tooltip.find('.globalSearchButton').click()
			waitFor {hasLink(tooltip, 'Show Nearest Users')==true}
			waitFor {hasLink(tooltip, 'Save Place')==true}
			waitFor {hasLink(tooltip, 'Get Directions')==true}
			waitFor {hasLink(tooltip, 'Setup Meeting')==true}
		}
		
		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		$('#btn_map_clear').click()
		expect tooltip.displayed, is(false)
		$('#refreshAssets').click()
		waitFor{allAssets.size()>0}
		}
	
	def "Create location report of an asset"(){	
		given:"At AssetsPage"
		at AssetsPage
		
		when:"I select an asset"
		assetArrow.click()
		if( $('div#tab-assets').find('ul.users').displayed==false){
		assetArrow.click()
		}		
		$('div#tab-assets').find('ul.users').find('img.openClose').click()
		waitFor {$('ul.item_details').displayed==true}
		$('div.usersList').find('input.user.check-box').click()
		$('#btn_tab-assets_createReport').click()
		
		then: "Report dialog opens and click create"
		waitFor {$('form#requestReportPermission').displayed==true}
		permissionSend.click()
		
		and: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		when:"I click View Report to see latest location reports"
		viewReport.click()
		waitFor {$('div#locReportListDiv').displayed==true}
		
		then:"I open detailed report view"
		locReportUser.click()
		waitFor {locReportHist.displayed==true}
		locReportUser.click()
		
		and:"Close Report dialog"
		$('a.closeReport').click()
		waitFor {locReportDiv.displayed==false}
		$('#btn_map_clear').click()
		$('#refreshAssets').click()
		waitFor{allAssets.size()>0}
	}
	
	def "Login as admin"(){
		given:"We are at Group Management Page"
		at AssetsPage
		$('a#btn_logout').click()
		waitFor {at LoginPage}
			
		when:"I login with 2nd admin"
		username=params.get('username')
		password=params.get('password')
		loginButton.click()
		
		then:
		waitFor {at WelcomePage}
		
		}
	
	def "Revoke 2nd admin rights"(){
		given:"We are at asset management page"
		at WelcomePage
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
		groupMan.click()
		at AdminHomePage
		
		when:
		$('td.name',text:'AA').parent().find('a.btn-update').click()
		
		then:"Dialog opens and I remove other admin"
		waitFor {groupDialog.displayed==true}
		groupAdminTab.click()
		waitFor {admin.displayed==true}
		assert groupAdminUsers.size()==2
		groupAdminUsers[1].click()
		arrowRemove.click()
		waitFor('fast')	{groupAdminUsers.size()==1}
		
		and:"Save"
		sendDialog.click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
	}
	}
	


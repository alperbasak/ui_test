package com.oksijen.lbs.lbas.functest.specs.map.assets

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*

@Stepwise
class AssetsSpec extends LocateSpec {
  
	 def "Select an asset group and locate them all"(){
		given: "We are at the AssetsPage"
		at MapHomePage
		assetsTab.click()
		waitFor {at AssetsPage}
		
		when:"I select a group and click Locate"
		$('div#tab-assets').find('input.groupId').click()
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
		}
	
	def "Create location report of an asset"(){	
		given:"At AssetsPage"
		at AssetsPage
		
		when:"I select an asset"
		$('div#tab-assets').find('img.openClose').click()
		if( $('div#tab-assets').find('ul.users').displayed==false){
		$('div#tab-assets').find('img.openClose').click()
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
	}

}


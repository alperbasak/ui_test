package com.oksijen.lbs.lbas.functest.specs.map.assets

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
@Stepwise
class MultiAssetSpec extends LocateSpec {
	@RetryOnFailure
	def "Show only locatable assets"() {
		given:"We are at the UsersPage"
		at MapHomePage
		assetsTab.click()		
		waitFor {at AssetsPage}
		
		when:"I select show only locatable from dropdown box"
		$('a#filter_all_tab-assets-button').click()
		showLocatable.click()
		
		then:"Only locatable users will be shown"
		waitFor {nolocatableUsers.displayed==false}
	}
	@RetryOnFailure
	 def "Select two asset group and locate them all"(){
		given: "We are at the AssetsPage"
		waitFor {at AssetsPage}
		
		when:"I select a group and click Locate"
		locatableAssets[4].find('input.groupId').click()
		locatableAssets[3].find('input.groupId').click()
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
		}
	 @RetryOnFailure
	def "Select three asset group and locate them all"(){	
		given: "We are at the AssetsPage"
		waitFor {at AssetsPage}
		
		when:"I select a group and click Locate"
		locatableAssets[2].find('input.groupId').click()
		if(locatableAssets[4].find('input.groupId').value()==false){
			locatableAssets[4].find('input.groupId').click()
			}
			if(locatableAssets[3].find('input.groupId').value()==false){
			locatableAssets[3].find('input.groupId').click()
			}
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
		}
	@RetryOnFailure
	def "Select five asset group and locate them all"(){
		given: "We are at the AssetsPage"
		waitFor {at AssetsPage}
		
		when:"I select a group and click Locate"
		locatableAssets[1].find('input.groupId').click()
		locatableAssets[0].find('input.groupId').click()
		if(locatableAssets[4].find('input.groupId').value()==false){
			locatableAssets[4].find('input.groupId').click()
			}
		if(locatableAssets[3].find('input.groupId').value()==false){
		locatableAssets[3].find('input.groupId').click()
			}
		if(locatableAssets[2].find('input.groupId').value()==false){
			locatableAssets[2].find('input.groupId').click()
			}
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
	}
}


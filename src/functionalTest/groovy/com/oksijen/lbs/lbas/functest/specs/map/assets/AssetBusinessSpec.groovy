package com.oksijen.lbs.lbas.functest.specs.map.assets

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.admin.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*

@Stepwise
class AssetBusinessSpec extends LocateSpec {
//	@RetryOnFailure(times=5)
def "Create an asset and see that it's locatable within business hours"(){
	given:"We are at asset management page"
		at MapHomePage
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
		assetMan.click()
		at AdminHomePage
		
	when:"Add asset to a group"
		selectMenu.click()
		addAsset.click()
		apply.click()
		waitFor{assetDialog.displayed==true}
		assetName<<"BusinessAsset"
		assetModel<<"Test"
		assetAllocated<<"Alper"
		waitFor{$('ul.ui-autocomplete li').size()>0}
		$('li.ui-menu-item',0).find('a.ui-corner-all').click()
		$('#id_agps_enabled').click()
		assetGroupMenu.click()
		assetGroup.click()
		
	then:"I enable permanent periodic tracking"
		$('ul.tabs li')[1].click()
		waitFor{$('#assetTracking').displayed==true}
		waitFor{$('#permanentPeriodicTracking').value()==false}
		$('#permanentPeriodicTracking').click()
		$('#assetVisibilityType',value:'company').click()
		sendDialog.click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
	and:"Check if asset locatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'AA').displayed==true}
	$('span.groupName',text:'AA').click()
	$('div.container span',text:'BusinessAsset Test').click()
	waitFor{$('span',text:'BusinessAsset Test').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('.groupElementExpanded').find('a.globalSearchButton').click()
	waitFor{tooltip.displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
		
	}
@RetryOnFailure(times=5)
def "Check if its unlocatable out of business hours"(){
	given:"We are at admin page"
	at MapHomePage
	$('a#btn_admin').click()
	waitFor {at AdminHomePage}
	companyMan.click()
	at AdminHomePage
	
	when:"I change company working hours to see the asset is unlocatable"
	companyWorkingHours.click()
	waitFor {$('#defaultVisibilityProfile').displayed==true}
	companyVisibility[0].find('input.checkBox').click()
	companyVisibility[1].find('input.checkBox').click()
	companyVisibility[2].find('input.checkBox').click()
	companyVisibility[3].find('input.checkBox').click()
	companyVisibility[4].find('input.checkBox').click()
	$('#update-company').click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	then:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'AA').displayed==true}
	$('span.groupName',text:'AA').click()
	$('div.container span',text:'BusinessAsset Test').click()
	waitFor{$('span',text:'BusinessAsset Test').parent().find('ul.item_details').displayed==true}
	waitFor{$('span',text:'BusinessAsset Test').parent().find('li.icon.locked.first').displayed==true}
	
}
@RetryOnFailure(times=5)
def "Restore company hours and delete asset"(){
		given:"We are at asset management page"
		at AssetsPage
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
		companyMan.click()
		at AdminHomePage
		
		when:"I edit working hours and delete asset"
		companyWorkingHours.click()
		waitFor {$('#defaultVisibilityProfile').displayed==true}
		companyVisibility[0].find('input.checkBox').click()
		companyVisibility[0].find('.fromTime div input.inputText.time.hours')<<"08"
		companyVisibility[0].find('.fromTime div input.inputText.time.mins')<<"00"
		companyVisibility[0].find('.toTime div input.inputText.time.hours')<<"17"
		companyVisibility[0].find('.toTime div input.inputText.time.mins')<<"00"
		
		companyVisibility[1].find('input.checkBox').click()
		companyVisibility[1].find('.fromTime div input.inputText.time.hours')<<"08"
		companyVisibility[1].find('.fromTime div input.inputText.time.mins')<<"00"
		companyVisibility[1].find('.toTime div input.inputText.time.hours')<<"17"
		companyVisibility[1].find('.toTime div input.inputText.time.mins')<<"00"
		
		companyVisibility[2].find('input.checkBox').click()
		companyVisibility[2].find('.fromTime div input.inputText.time.hours')<<"08"
		companyVisibility[2].find('.fromTime div input.inputText.time.mins')<<"00"
		companyVisibility[2].find('.toTime div input.inputText.time.hours')<<"17"
		companyVisibility[2].find('.toTime div input.inputText.time.mins')<<"00"
		
		companyVisibility[3].find('input.checkBox').click()
		companyVisibility[3].find('.fromTime div input.inputText.time.hours')<<"08"
		companyVisibility[3].find('.fromTime div input.inputText.time.mins')<<"00"
		companyVisibility[3].find('.toTime div input.inputText.time.hours')<<"17"
		companyVisibility[3].find('.toTime div input.inputText.time.mins')<<"00"
		
		companyVisibility[4].find('input.checkBox').click()
		companyVisibility[4].find('.fromTime div input.inputText.time.hours')<<"08"
		companyVisibility[4].find('.fromTime div input.inputText.time.mins')<<"00"
		companyVisibility[4].find('.toTime div input.inputText.time.hours')<<"17"
		companyVisibility[4].find('.toTime div input.inputText.time.mins')<<"00"
		$('#update-company').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
			
		assetMan.click()
		at AdminHomePage
		$('td.name',text:'BusinessAsset').parent().find('a.btn-delete').click()
		
		then:"Delete dialog opens"
		waitFor{$('.noClose').displayed==true}
		$('#dialog').find('a.graphicBtn.violet').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}	
	}

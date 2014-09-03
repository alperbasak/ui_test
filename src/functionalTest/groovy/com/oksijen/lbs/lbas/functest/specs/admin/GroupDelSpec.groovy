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
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.calendar.*

import spock.lang.Specification

import com.oksijen.lbs.spock.extensions.retry.*

/**
 * 
 */
@Stepwise
class GroupDelSpec extends LocateSpec {
	
@RetryOnFailure
def "Delete enterprise category"(){
	when: "We are at the PlacesPage"
	at WelcomePage
	$('a#btn_tab-places').click()
	at PlacesPage
	then:
	$('#btn_tab-places-enterprise').click()
	waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
	if (enterprisePlace.displayed==true){
		enterprisePlace.click()
	$('a#btn_tab-places_Delete').click()
	waitFor{$('.dialog.undefined').displayed==true}
	$('div.ui-dialog-buttonset button').click()
	waitFor{$('#ui-dialog-title-dialog', text:'Delete').displayed==false}
	waitFor{$('#ui-dialog-title-dialog', text:'Success').displayed==true}
	waitFor{$('div.ui-dialog-buttonset button span',text:'OK').displayed==true}
//	$('div.ui-dialog-buttonset button span',text:'OK').click()
}
	}
@RetryOnFailure
def "Delete Groups"(){
	when:"We are at Group Management Page"	
	adminBtn.click()
	at AdminHomePage
	groupMan.click()
	at AdminHomePage
	
	then:"I select all added groups"
	if($('td.name',text:'NewGroup1').displayed==true){
	$('td.name',text:'NewGroup1').parent().find('input').click()
	if($('td.name',text:'NewGroup2').displayed==true){
	$('td.name',text:'NewGroup2').parent().find('input').click()
	if($('td.name',text:'NewAssetGroup1').displayed==true){
	$('td.name',text:'NewAssetGroup1').parent().find('input').click()
	if($('td.name',text:'NewAssetGroup2').displayed==true){
	$('td.name',text:'NewAssetGroup2').parent().find('input').click()}}}
	selectMenu[1].click()
	delete.click()
	apply.click()
	
	waitFor{$('.confirmation').displayed==true}
	$('#dialog').find('span',text:'OK').click()
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	}
}
}
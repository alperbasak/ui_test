package com.oksijen.lbs.lbas.functest.specs.map.places

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class PlacesDelSpec extends LocateSpec {

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
	
def "Delete personal category"(){
	when: "We are at the PlacesPage"
	at WelcomePage
	$('a#btn_tab-places').click()
	at PlacesPage
	then:
	$('#btn_tab-places-personal').click()
	waitFor {$('#tab-places-personal').hasClass('ui-tabs-hide')==false}
	if (personalPlace.displayed==true){
		personalPlace.click()
	$('a#btn_tab-places_Delete').click()
	waitFor{$('.dialog.undefined').displayed==true}
	$('div.ui-dialog-buttonset button').click()
	waitFor{$('#ui-dialog-title-dialog', text:'Delete').displayed==false}
	waitFor{$('#ui-dialog-title-dialog', text:'Success').displayed==true}
	waitFor{$('div.ui-dialog-buttonset button span',text:'OK').displayed==true}
//	$('div.ui-dialog-buttonset button span',text:'OK').click()
}
	}
	
}

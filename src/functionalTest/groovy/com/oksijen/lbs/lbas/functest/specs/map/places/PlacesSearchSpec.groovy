package com.oksijen.lbs.lbas.functest.specs.map.places

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*

/**
 * Created by cpekyaman on 3/25/2014.
 */
@Stepwise
class PlacesSearchSpec extends LocateSpec {
    def "I should be able to search with autocomplete search input"() {
    	given: "We are at the PlacesPage"    	
    	at MapHomePage
    	placesTab.click()
    	waitFor {at PlacesPage}
    	    	
        when: "I enter searchtext"
        searchInput << params.get('placesSearch.searchInput')

        then: "Autocomplete list should be visible"    
        waitFor('fast') { autocompleteList.displayed == true }
        expect autocompleteListItems.size(), greaterThan(0)
        
        and: "Tooltip should be shown on map"
        clickFirstItem(autocompleteListItems)
        waitFor('fast') { tooltip.displayed == true }
        
        and: "There must be links visible on tooltip"
        expect hasLink(tooltip, 'Show Nearest Users'), is(true)
        expect hasLink(tooltip, 'Save Place'), is(true)
        expect hasLink(tooltip, 'Get Directions'), is(true)
        expect hasLink(tooltip, 'Setup Meeting'), is(true)
        
        and: "Tooltip should be closed when close button is clicked"
        expect tooltipClose.displayed, is(true)
        tooltipClose.click()
        expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
    }
	
	def "Select a place and show on map"() {
		given: "We are at the PlacesPage"    
		at PlacesPage
		
		when:"I select a place and click show on map"
		$('input.place').click()
		$('a#btn_tab-places_showOnMap').click()
		
		then: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
		
		}

	
	def "Add a new category to enterprise"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		$('#btn_tab-places-enterprise').click()
		waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
		
		when:"I click new category"
		$('#btn_tab-places_newCategory').click()
		waitFor {$('#tabs_in_enterpriseCategoryDialog').displayed==true}
		
		then:"I enter category details"
		$('#categoryDetailForm_categoryName') << params.get('newCategoryName')
		$('a#tab2').click()
		waitFor {$('#updateCatPermission').displayed==true}
		$('#permissionSearchInput') << params.get('newUser.name')
		waitFor {$('div#wp_autocomplete').displayed==true}
		$('div#wp_autocomplete').find('li.ui-menu-item a').click()
	
		and:"Add permissions"
		$('#changePermissions0').click()
		$('#changePermissions1').click()
		$('#changePermissions2').click()
		$('#changePermissions3').click()
		$('#changePermissions4').click()
		$('#changePermissions5').click()
		$('#changePermissions6').click()
		$('.ui-dialog-buttonset button').click()
		
		and:"Success dialog is shown"
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
	}	
	
	def "Edit category"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		
		when:"I select a category to edit"
		$('#tab-places-enterprise').find('input.placeId').click()
		$('#editCategoryGroupAction').click()
		waitFor {$('.newCategoryPopUp').displayed==true}
		$('#categoryDetailForm_categoryName') << params.get('editUser.name')
		
		then:"I edit admin rights"
		$('a#tab2').click()
		$('a#tab3').click()
		$('.ui-dialog-buttonset button').click()
				
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		}
	
	def "Delete category"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		
		when:"I select a category to delete"
		$('#tab-places-enterprise').find('input.placeId').click()
		$('#btn_tab-places_Delete').click()
		
		then:"Delete confirmation dialog is shown"
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
		}
	
	def "Show enterprise location"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		$('#btn_tab-places-enterprise').click()
		waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
		
		when: "I click Show under a category"
		$('#tab-places-enterprise').find('span.openClose').click()
		$('span.nameUser').jquery.mouseover()
		$('ul.places a.globalSearchButton').click()
		
		then: "Tooltip opens"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
	}
	
	def "Creating a new category for personal places"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		$('#btn_tab-places-personal').click()
		waitFor {$('#tab-places-personal').hasClass('ui-tabs-hide')==false}
		
		when:"I click New Category"
		$('#btn_tab-places_newCategory').click()
		waitFor {$('#tabs_in_personalCategoryDialog').displayed==true}
		
		then:"I enter category details"
		$('#categoryDetailForm_categoryName') << params.get('newCategoryName')
		$('a#tab2').click()
		waitFor {$('#updateCategorySharing').displayed==true}
		$('span.groupNameContainerCat').click()
		$('.action-arrow.add').click()
		$('.newPersonalCategory').find('.ui-dialog-buttonset button').click()
		
		and:"Success dialog displayed"
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
	}
	
	def "Editing a personal places category"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		
		when:"I select a category to edit"
		$('#tab-places-personal').find('input.placeId').click()
		$('#editCategoryGroupActionPer').click()
		waitFor {$('.newCategoryPopUp').displayed==true}
		$('#categoryDetailForm_categoryName') << params.get('editUser.name')
		
		then:"I add permissions"
		$('a#tab2').click()
		waitFor {$('#updateCatPermission').displayed==true}
		$('#permissionSearchInput') << params.get('newUser.name')
		waitFor {$('div#wp_autocomplete').displayed==true}
		$('div#wp_autocomplete').find('li.ui-menu-item a').last().click()
	
		and:"Add permissions"
		$('#changePermissions0').click()
		$('#changePermissions1').click()
		$('#changePermissions2').click()
		$('#changePermissions3').click()
		$('#changePermissions4').click()
		$('#changePermissions5').click()
		$('#changePermissions6').click()
				
		$('a#tab3').click()
		$('.ui-dialog-buttonset button').click()
				
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		}
	
	def "Delete personal category"(){
		given: "We are at the PlacesPage"
		at PlacesPage
		
		when:"I select a category to delete"
		$('#tab-places-personal').find('input.placeId').click()
		$('#btn_tab-places_Delete').click()
		
		then:"Delete confirmation dialog is shown"
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
		waitFor {$('.dialog.undefined').displayed==true}
		$('.undefined').find('.ui-dialog-buttonset button').click()
		}
	
}

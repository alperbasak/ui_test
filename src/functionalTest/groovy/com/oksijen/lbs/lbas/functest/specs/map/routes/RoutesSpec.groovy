package com.oksijen.lbs.lbas.functest.specs.map.routes

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
@Stepwise
class RoutesSpec extends LocateSpec {
	@RetryOnFailure
	def "Empty destination input will generate error"(){
		given:"We are at RoutesPage"
		at MapHomePage
		routesTab.click()
		waitFor {at RoutesPage}
		
		when:"I click Route without entering any destination"
		$('a#btn_route_route').click()
		
		then:"Error information is displayed"
		waitFor  {$('#routing').find('div#error-view-sendmessage').displayed==true}
		}
	@RetryOnFailure
	def "Creating a route will display a route on the map"(){
		given: "We are at the RoutesPage"
		at RoutesPage
		
		when:"I enter starting and target destinations"
		$('input#routePoint0') << params.get('placesSearch.searchInput')
		waitFor('fast') { autocompleteList.displayed == true }
		waitFor {autocompleteListItems.size()>0}
		autocompleteListItems.click()
		waitFor('fast') { autocompleteList.displayed == false }
		
		$('input#routePoint1') << params.get('placesSearch.targetInput')
		waitFor('fast') { autocompleteListLast.displayed == true }
		waitFor {autocompleteListItemsLast.size()>0}
		autocompleteListItemsLast.click()
		waitFor('fast') { autocompleteListLast.displayed == false }
		
		then:"Route will be created and shown on map"
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		waitFor {$('td.adp-text').displayed==true}
		$('td.adp-text').click()
		waitFor {$('div.gm-style-iw').displayed==true}
		$('div.gm-style-iw').next('div').click()
		waitFor {$('div.gm-style-iw').displayed==false}
		}
	@RetryOnFailure
	def "Adding destination will add a new point and update the route"(){
		given:"We are at RoutesPage"
		at RoutesPage

		when:"I have a route and add a new destination"
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		$('a#routing_addDestination').click()
		waitFor {$('table#routePointTable tbody tr').size()>2}
		
		then:"I enter new destination"
		$('input#routePoint2') << params.get('placesSearch.newInput')
		waitFor('fast') { autocompleteListLast.displayed == true }
		waitFor {autocompleteListItemsLast.size()>0}
		autocompleteListItemsLast.click()
		
		and:"New point will be shown on map"
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		}
	@RetryOnFailure
	def "Deleting a destination from the route will update the route"(){
		given:"We are at RoutesPage"
		at RoutesPage
		
		when:"I delete the point in the middle"
		$('a#deletePoint1').click()
		
		then:"Only starting and latest target destinations remain"
		waitFor {$('table#routePointTable tbody tr').size()==2}
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		
		and:"Points on the map"
		waitFor {$('td.adp-text').displayed==true}
		$('td.adp-text').click()
		waitFor {$('div.gm-style-iw').displayed==true}
		$('div.gm-style-iw').next('div').click()
		waitFor {$('div.gm-style-iw').displayed==false}
		}
	@RetryOnFailure
	def "Include traffic information and save the route"(){
		given:"We are at RoutesPage"
		at RoutesPage
		
		when:"I check include traffic info"
		$('input#routingTrafficEnableCheck').click()
		$('a#btn_route_route').click()
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		$('span.btnSpan.lm').click()
		
		then:"I save the route"
		waitFor {$('.saveDialogRoute').displayed==true}
		$('.saveDialogRoute').find('input#idRouteName') << params.get('newCategoryName')
		$('.saveDialogRoute').find('a#btn_route_save').click()
		
		and:"Success dialog is displayed"
		expect successSent.displayed, is(true)
		waitFor {successSent.displayed==false}
		}
	@RetryOnFailure
	def "Show saved routes on map"(){
		given:"We are at RoutesPage"
		at RoutesPage
		$('a#btn_tab-routes_savedRoutesTab').click()
		waitFor {$('div.searchSavedRoutes').displayed==true}
		
		when:"I open saved route's detailed information"
		$('div#idSavedRoutesScrollPane').find('img.x').click()
		waitFor {$('span.routeDetail').displayed==true}
		
		then:"When I click Show, route is displayed on map"
		$('a.globalSearchButton').click()
		waitFor {at RoutesPage}
		waitFor {$('table#routePointTable tbody tr').size()==2}
		waitFor {$('#idRouteResult').displayed==true}
		waitFor {$('#idRouteResultActionsDiv').displayed==true}
		}
	@RetryOnFailure
	def "Share a route with another user"(){
		given:"We are at RoutesPage"
		at RoutesPage
		$('a#btn_tab-routes_savedRoutesTab').click()
		waitFor {$('div.searchSavedRoutes').displayed==true}
		
		when: "I open saved route's detailed information"
		$('span.routeDetail').click()
		waitFor {$('span.routeDetail').displayed==true}
		
		then:"I click Share and and a dialog opens"
		$('a#btn_routes_Share').click()
		waitFor {$('.shareRouteDialog').displayed==true}
		$('input#token-input-messageTo') << 'AlperTest'
		waitFor { addressList.displayed == true }					
		waitFor {addressListItems.size()>0}
		addressListItems[0].click()
		waitFor { $("ul.token-input-list-facebook li").size()>0}
		
		and:"I share with a user"
		$('.shareRouteDialog').find('a#btShareRoute').click()
		waitFor {$('ul.places li').size()>1}
		}
	@RetryOnFailure
	def "I delete route"(){
		given: "We are at My Routes page"
		waitFor {$('div.searchSavedRoutes').displayed==true}
		
		when:"I open saved route's detailed information"
		$('span.routeDetail').click()
		waitFor {$('span.routeDetail').displayed==true}
		
		then:"I click delete and the saved route will be deleted"
		$('a#btn_routes_Delete').click()
		if($('ul.places li').size()>0){
			$('input.route').click()
			$('a#btn_routes_Delete_All').click()
			waitFor {$('ul.places li').displayed==false}
			}
		}

	}


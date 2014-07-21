package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class PlacesPage extends MapHomePage {
    static url = "welcome"
    static at = { searchInput.displayed == true && searchInput.hasClass('ui-autocomplete-input') }

    static content = {
    	link(required: true) 					{ $('a#btn_tab-places') }
        searchInput(required: true) 	 		{ $("input#search_places") }
        autocompleteList(required: false)		{ $('ul.ui-autocomplete').last() }
        autocompleteListItems(required: false)	{ autocompleteList.find('li.ui-menu-item') }
                
        header(required: true)		{ $("div.subtabsCover") }
        contents(required: true)	{ $("div.subtabs") }
        
        recents(required: true) 	{ $("div#tab-places-recents") }
        enterprise(required: true) 	{ $("div#tab-places-enterprise") }
        personal(required: true) 	{ $("div#tab-places-personal") }
        
        tooltip(required: false, cache: false) { $("div.toolTipContainer") }
        tooltipClose(required: false, cache: false) { _slink('Close', tooltip.find('div.toolTipHeader')) }
		
		successDialog(required:false)	{$('.success-dialog')}
    }
}

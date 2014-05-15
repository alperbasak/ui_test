package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class RoutesPage extends MapHomePage {
    static url = "welcome"
    static at = { routing.displayed == true }

    static content = {
    	link(required: true) 			{ $('a#btn_tab-routes') }
        searchInput(required: true) 	{ $("input#searchRoute") }
        
        header(required: true)			{ $("div.subtabsCover") }
        contents(required: true)		{ $("div.subtabs") }
        
        routing(required: true)			{ $("div#tab-routes-routing") }
        savedRoutes(required: true) 	{ $("div#tab-routes-savedRoutes") }
    }
}

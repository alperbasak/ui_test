package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class AssetsPage extends MapHomePage {
    static url = "welcome"
    static at = { searchInput.displayed == true }

    static content = {
    	link(required: true) 		{ $('a#btn_tab-assets') }
        searchInput(required: true) { $("input#search_assets") }
        contents(required: true) 	{ $("div.contents") }
    }
}

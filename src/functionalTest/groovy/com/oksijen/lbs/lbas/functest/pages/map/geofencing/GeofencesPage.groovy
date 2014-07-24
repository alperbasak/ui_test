package com.oksijen.lbs.lbas.functest.pages.map.geofencing

import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage

class GeofencesPage extends GeofencingPage {
    static url = "welcome"
    static at = { geofenceActionsBtn.displayed == true }

    static content = {        
        geofenceActions(required: true) 	{ $("select#geofenceActionList") }
        geofenceActionsBtn(required: true) 	{ $("a#geofenceActionList-button") }
        geofenceActionsMenu(required: true) { $("ul#geofenceActionList-menu") }
		
		drawRect(required:false)	{$('div.gmnoprint').last().find('div').last()}	
		canvas(required:false)		{$('div#rightMapWrapper')}
		saveGeo(required:false)		{$('a.map_button',1)}
		
		successDialog(required:false)	{$('.success-dialog')}
    }
}

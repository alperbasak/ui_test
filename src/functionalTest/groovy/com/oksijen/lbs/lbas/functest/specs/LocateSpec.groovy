package com.oksijen.lbs.lbas.functest.specs

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.util.TestParams

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Base class for all specs to extend.
 * Contains simple wrapper and utility functions for accessing page content.
 * Also common spec level and method level setup / cleanup functions are included.
 *
 */

class LocateSpec extends GebSpec {	
	@Shared params = new TestParams('TestParams')
	
	// run before the first feature method
    def setupSpec() {
    	driver.manage().window().maximize()
    	
    	to LoginPage
    	
    	username = params.get('username')
        password = params.get('password')
        loginButton.click()
        	
        waitFor {at WelcomePage}
    } 
    
	// run before every feature method
    def setup() {
		    }          
	
    // run after every feature method
    def cleanup() {
		    }
	
    // run after the last feature method
    def cleanupSpec() {
    	at WelcomePage
    	logoutBtn.click()
    	waitFor {at LoginPage}
    }  
    
    // link helper methods
    def hasLink(selector, label) { _link(label, selector).displayed }   
    
    def clickLink(label) { _link(label).click() }
    
    def _link(label, selector) {
    	if(selector) {
    		selector.find(text: label, 'a')
    	} else {
    		$('a', text: label)
    	}
    } 

    def hasSLink(selector, label) { _slink(label, selector).displayed }   
    
    def clickSLink(label) { _slink(label).click() }
    
    def _slink(label, selector) { 
    	if(selector) {
    		selector.find('a').has('span', text: label)
    	} else {
    		$('a').has('span', text: label)
    	} 
    }
    
    // button helper methods
    def hasButton(selector, label) { _button(label, selector).displayed }
    
    def clickButton(label) { _button(label).click() }
    
    def _button(label, selector) {
    	if(selector) {
    		selector.find(text: label, 'button')
    	} else {
    		$('button', text: label)
    	}
    }
    
    // misc helper methods
    def clickFirstItem(selector) { selector.first().find('a').click() }
    
    def selectOption(id, val) {    	
    	js.exec "\$('select#${id}').selectmenu('value', '${val}');"
    	js.exec "\$('select#${id}').selectmenu('change');"
    }
}

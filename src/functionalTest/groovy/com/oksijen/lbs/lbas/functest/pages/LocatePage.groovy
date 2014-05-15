package com.oksijen.lbs.lbas.functest.pages

import geb.Page

/**
 * Base class for all pages to extend.
 * Contains simple wrapper and utility functions for accessing page content.
 */
class LocatePage extends Page {
    def _link(label, selector) {
    	if(selector) {
    		selector.find(text: label, 'a')
    	} else {
    		$('a', text: label)
    	}
    } 

    def _slink(label, selector) { 
    	if(selector) {
    		selector.find('a').has('span', text: label)
    	} else {
    		$('a').has('span', text: label)
    	} 
    }
    
    def _button(label, selector) {
    	if(selector) {
    		selector.find(text: label, 'button')
    	} else {
    		$('button', text: label)
    	}
    }
}

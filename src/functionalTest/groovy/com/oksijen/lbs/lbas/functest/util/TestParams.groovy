package com.oksijen.lbs.lbas.functest.util

class TestParams {
	def params = [:]
	
	TestParams(fileName) {
		def currentDir = System.getProperty("user.dir")
		def filePath = "${currentDir}\\src\\functionalTest\\resources\\${fileName}.xls"
		
    	new ExcelBuilder(filePath).eachLine {
    		def paramName = cell(0)
    		if(paramName.endsWith('[')) {
    			paramName = paramName.substring(0, paramName.length() - 1)
    			params[paramName] = []
    			
    			def paramCount = cell(1).toInteger()
    			paramCount.times { i ->
    				def value = cell(i + 2)
    				if(value == '""') {
    					value = ''
    				}
    				params[paramName] << value
    			}    			
    		} else {
    			params[paramName] = cell(1)
    		}
    	}
	}
	
	def get(param) { params[param] }
	
	def get(param, defaultValue) { params[param] ?: defaultValue }
}

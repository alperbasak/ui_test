import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

reportOnTestFailureOnly = true
autoClearCookies = true

waiting {
    timeout = 5
    retryInterval = 0.4
    
    presets {
    	slow {
    		timeout = 8
    		retryInterval = 0.6
    	}
    	fast {
    		timeout = 3
    		retryInterval = 0.2
    	}
    }
}

environments {
    // run via gradlew chromeTest
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via gradlew firefoxTest
    firefox {
        driver = { new FirefoxDriver() }
    }

    // run via gradlew ieTest
    ie {
        driver = { new InternetExplorerDriver() }
    }

    // run via gradlew phantomjsTest
    phantomjs {
        driver = { new PhantomJSDriver() }
        System.setProperty("phantomjs.binary.path", "C:\\Dev\\Test_Tools\\phantomjs-1.9.2-windows\\phantomjs.exe")
    }
}
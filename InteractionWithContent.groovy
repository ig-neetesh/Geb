@Grapes([
    @Grab("org.gebish:geb-core:0.10.0"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.43.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.43.1")
])

import geb.Browser

Browser.drive {
    go "http://google.com/ncr"
    assert title == "Google" : "Oops! This is not google search page"
    
    $("input", name: "q").value("wikipedia")
    waitFor { title.endsWith("Google Search") }
    
    def firstLink = $("li.g", 0).find("a")
    assert firstLink.text() == "Wikipedia" : "First link doesn't belongs to wikipedia"
    
    firstLink.click()
    waitFor { title == "Wikipedia" }
}

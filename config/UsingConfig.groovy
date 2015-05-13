@Grapes([
    @Grab("org.gebish:geb-core:0.10.0"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.43.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.43.1")
])

import geb.Browser
import geb.Page
import geb.Module

class PageNotFoundPage extends Page {
     static at = { $('#errorMessage').text() == 'Sorry but we could not find that page' }
}

class GoogleSearchModule extends Module {
 
    def buttonValue
 
    static content = {
        field { $("input", name: "q") }
        button(to: GoogleResultsPage) { 
            $("input", value: buttonValue)
        }
    }
}
 
class GoogleHomePage extends Page {
    static url = "/ncr"
    static at = { title == "Google" }
    static content = {
        search { module GoogleSearchModule, buttonValue: "Google Search" }
    }
}
 
class GoogleResultsPage extends Page {
    static at = { title.endsWith "Google Search" }
    static content = {
        search { module GoogleSearchModule, buttonValue: "Search" }
        results { $("li.g") }
        result { i -> results[i] }
        resultLink { i -> result(i).find("a") }
        firstResultLink { resultLink(0) }
    }
}
 
class WikipediaPage extends Page {
    static at = { title == "Wikipedia" }
}

Browser.drive {
    to GoogleHomePage
    assert at(GoogleHomePage)
    search.field.value("wikipedia")
    waitFor { at GoogleResultsPage }
    assert firstResultLink.text() == "Wikipedia"
    firstResultLink.click()
    waitFor { at WikipediaPage }
}.close()

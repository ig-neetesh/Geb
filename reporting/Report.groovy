@Grapes([
    @Grab("org.gebish:geb-core:0.10.0"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.43.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.43.1")
])

import geb.Browser

Browser.drive {
    reportGroup "google"
    go "http://google.com/ncr"
    report "home page"
 
    go "http://wikipedia.org"
    reportGroup "wikipedia"
    report "home page"
}

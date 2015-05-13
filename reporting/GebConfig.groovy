import geb.report.*

reportsDir = "geb-reports"

reportingListener = new ReportingListener() {
  void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
    reportFiles.each {
      println "[[ATTACHMENT|$it.absolutePath]]"
    }
  }
}

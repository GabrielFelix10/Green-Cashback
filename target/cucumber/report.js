$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/behavior/calculateCashback.feature");
formatter.feature({
  "name": "Calculate Cashback",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I receive a request to obtain a cashback with 2 bottles",
  "keyword": "Given "
});
formatter.match({
  "location": "CashBackStepDefs.i_receive_a_request_to_obtain_a_cashback_with_bottles(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "make  request to cashback machine",
  "keyword": "When "
});
formatter.match({
  "location": "CashBackStepDefs.make_request_to_cashback_machine()"
});

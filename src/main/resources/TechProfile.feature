 
 Feature: 

  Technician profile information updates have been received from Dispatch Portal 
 
 Scenario Outline: "<Service>" 

    Given The Request Url for service "<Service>" and the Test Case "<TestCase>" is given.
    When Hitting the service "<Service>" and the Test Case "<TestCase>".
    Then Verifying the service "<Service>" and the Test Case "<TestCase>" Response.

    Examples:

| Service | TestCase |

| Health_Checkup| Tech_Profile|

| Health_Checkup| AssignJob_Event|

| Health_Checkup| AssignJob_Event|


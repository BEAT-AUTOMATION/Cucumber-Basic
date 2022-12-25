Feature: Test Book Store Application

#Test case1 : Add new user registration
  Scenario: Create a new user
    Given  Go to Book Store
    And user clicks on Login Page
   When Open new user registration page
    When user enters the below data for new user
      |FirstName |LastName |UserName |Password |
      |TestFirstName |TestLastName|TestendUser8|Testing@1234|
   And User solves the reCaptcha box
    Then user clicks on Register button


#Test case2 : Login with newly register user
  Scenario: Login with newly registered user
    Given Go to Book Store
    And user clicks on Login Page
    When user enters the below data to login
      |UserName |Password|
      |TestendUser1|Testing@1234|
    Then User verifies login is success
      |UserName |
      |TestendUser1|
    And User logout from application


#Test case3
  Scenario: User performs search book and added to collection
    Given Go to Book Store
    And user clicks on Login Page
    When user enters the below data to login
      |UserName |Password|
      |TestendUser1|Testing@1234|
    And Navigate to Book Store for search
    And User searches book "Designing Evolvable Web APIs with ASP.NET" and Select it
    Then User adds book to collection
    And User logout from application


    #Test case4 -Viewing Profile and verifying collections
  Scenario: User validates books is added to profile
    Given Go to Book Store
    And user clicks on Login Page
    When user enters the below data to login
      |UserName |Password|
      |TestendUser1|Testing@1234|
    Then Navigate to profile and verify book "Designing Evolvable Web APIs with ASP.NET" added to collection
    And User logout from application

    #Test case5 -Delete Book from Collection and Verify
Scenario: User validates Book is deleted from Collection
  Given Go to Book Store
  And user clicks on Login Page
  When user enters the below data to login
    |UserName |Password|
    |TestendUser1|Testing@1234|
  Then Navigate to profile and verify book "Designing Evolvable Web APIs with ASP.NET" deleted from collection
  And User logout from application

   #Test case6 -Navigation
  Scenario: User validates all navigation points
    Given Go to Book Store
    And user clicks on Login Page
    When user enters the below data to login
      |UserName |Password|
      |TestendUser1|Testing@1234|
    And user verifies all the navigations in the application
    And User logout from application


  #Test case7 -Logout from Book store
  Scenario: Logout from application
    Given Go to Book Store
    And user clicks on Login Page
    When user enters the below data to login
      |UserName |Password|
      |TestendUser1|Testing@1234|
    Then User logout from application
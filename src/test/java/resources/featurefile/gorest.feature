Feature: End to End gorest testing

  Scenario: I should be able to create data sucessfully
    When I send post request to creat data
    And  I should insert name,email,gender,status
    Then User should be created successfully
    And  User Id should be created

    Scenario: I should be able to get single user data by user Id with get request
      When I send get request to fatch data by given Id
      And  I should be able to get user data succefully

      Scenario: I should be able to update data with patch request successfully
        When I send Patch request to update data
        And  I update name,email,gender,status
        Then I should be able to update user successfuly

        Scenario: I should be able to delete user id
          When I send delete request
          Then I should be able to delete record successfully
          And to verify record has been deleted
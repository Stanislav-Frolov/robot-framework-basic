*** Variables ***

${BROWSER}                       chrome 
${STEAM_MAIN}                    https://store.steampowered.com/
${STEAM_MAIN_TITLE}              Welcome to Steam
${STEAM_SIGN_IN_TITLE}           Sign In
${JOIN_STEAM_VALUE}              Join Steam
${STEAM_CREATE_ACCOUNT_TITLE}    Create Your Account

${LOGIN_BUTTON_XPATH}    xpath://a[@class= "global_action_link" and text()="login"]
${AGREE_CHECKBOX_XPATH}    xpath://input[@type="checkbox" and @name="i_agree_check"]

${USER_EMAIL}    email@hotmail.com
${NORWAY_OPTION}    NO

*** Settings ***

Library    SeleniumLibrary

*** Test Cases ***
Fill Steam Create Account Form
    When Browse Main Page
    And Go To Login Page
    And Go to Create Account Page
    Then Fill Create Account Form


*** Keywords ***

Browse Main Page
    Open Browser    ${STEAM_MAIN}    ${BROWSER}
    Maximize Browser Window
    Wait Until Element Is Visible    ${LOGIN_BUTTON_XPATH}
    Title Should Be    ${STEAM_MAIN_TITLE}      
    Wait Seconds    5
Go To Login Page
    Click Element    ${LOGIN_BUTTON_XPATH}
    Wait Until Element Is Visible    link:${JOIN_STEAM_VALUE}
    Title Should Be    ${STEAM_SIGN_IN_TITLE}
    Wait Seconds    5
Go to Create Account Page
    Click Element    link:${JOIN_STEAM_VALUE}    
    Title Should Be    ${STEAM_CREATE_ACCOUNT_TITLE}
Fill Create Account Form
    Input Text    name:email    ${USER_EMAIL}
    Input Text    name:reenter_email    ${USER_EMAIL}
    Click Element    id:country
    Select From List By Value    name:country    ${NORWAY_OPTION}
    Click Element    ${AGREE_CHECKBOX_XPATH} 
    Wait Seconds    5
    Close Browser
        
Wait Seconds
    [Arguments]    ${time}
    Sleep    ${time}s        



    
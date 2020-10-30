# SH TechTest
By Daniel Hignell

## App
- This app fetches hospital data and presents it in list format.
- Further information can be viewed by tapping on a list item
- The list can be filtered by "Sub-Type" by using the options menu on the top-right of the screen.

## Project
- When viewing the project on Android Studio, the majority of the logic comes under the `techtest.hospitals` package.
- There are a small number of tests to be found in the `(test)` and `(androidTest)` folders
- Using:
  - MVVM architecture with Repository
  - NavigationUI for navigating between the two pages and passing arguments
  - Retrofit and Moshi for network request.

## To Run App

- Ensure you have an android device running **version 7.0 or above**
- Scan this QR code and follow the steps 

![Scan](https://github.com/DanJH123/shTechTest/blob/main/download.png?raw=true) 

- or go to https://install.appcenter.ms/users/danny.hignell-gmail.com/apps/sh-tech-test/distribution_groups/distribution
- "Install Anyway" if prompted by "Play Protect" (you may need to disable this from the Play Store)
- Allow downloads from "Unknown Sources" (You may also need to enable this from the settings)
- Any issues running the app, please do not hesitate to contact me!

***NOTE: This app is confirmed to work for android 7.0 and above (may work on 5.0 or 6.0). Earlier versions have a bug related to the okHttp3 library that may have been fixed by changing the module version but could not be tested and so was left out of this release.***


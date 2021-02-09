## Overview
An app, that fetches data about 2048 app from AppAnnie server and shows on one screen sales data with bar charts and time period filter
and on the other screen sales data ranged by countries.
Documentation: https://helpcenter.appannie.com/community/s/article/4-Shared-Products-List, https://helpcenter.appannie.com/community/s/article/5-Product-Sales, https://helpcenter.appannie.com/community/s/article/1-Country-List

## Screens
<div align = "center">
    <img src = "https://user-images.githubusercontent.com/26003155/107364917-5e289780-6adc-11eb-9b16-a533efd90615.png" width="200">
    <img src = "https://user-images.githubusercontent.com/26003155/107364919-5ec12e00-6adc-11eb-840d-92406f9c5957.png" width="200">
    <img src = "https://user-images.githubusercontent.com/26003155/107364920-5f59c480-6adc-11eb-9187-3a72602667a3.png" width="200">
    <img src = "https://user-images.githubusercontent.com/26003155/107364921-5f59c480-6adc-11eb-9e15-4037e5016c9b.png" width="200">
</div>
    
## Chosen architecture
* clean = for easy support/changes
* MVVM pattern for UI = for better support, handling activity state

## Chosen libraries
* RXJava for asynchronous calls = most stable
* Dagger for DI = compile-time checking
* Retrofit for network = simplest
* MPAndroidChart for bar chart drawing = simplest

## Not implemented (time lack)
100% unit tests coverage, ui tests, more abstractions, pagination, complex filters, filtration in countries section, dividing into modules for each feature, error for each view, splitting into smaller fragments
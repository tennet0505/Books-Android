# Books_Android

A simple Android application built with Kotlin and Jetpack Compose that allows users to browse, search, and manage their favorite books. This app features a clean user interface and leverages the Room database for local storage of book information.

## Features

- **Browse Books**: View a list of available books with their cover images, titles, and authors.
- **Search Functionality**: Search for books by title or author.
- **Favorites**: Mark books as favorites and view them in a separate tab.
- **Light and Dark Themes**: Supports both light and dark mode themes.
- **Responsive UI**: Built using Jetpack Compose for a modern, responsive design.

## Table of Contents

1. [Architecture](#architecture)
2. [Design Decisions](#design-decisions)
3. [Setting Up the Project](#setting-up-the-project)
   - [Requirements](#requirements)
   - [Libraries/Frameworks Used](#librariesframeworks-used)
4. [Running the Project](#running-the-project)
   - [Clone the Repository](#clone-the-repository)
   - [Open the Project in Android Studio](#open-the-project-in-android-studio)
   - [Build & Run](#build--run)
5. [Unit Testing](#unit-testing)
   - [Unit Test Setup](#unit-test-setup)
   - [Running Tests](#running-tests)
6. [Future Improvements](#future-improvements)
7. [API](#api)
8. [Video](#video)

## 1. Architecture

The project follows the MVVM (Model-View-ViewModel) architecture pattern, promoting separation of concerns and enhancing maintainability and testability.

- **Model**:
  - Represents the data layer and business logic.
  - Includes the `Book` data model and `Room` for local data storage.

- **ViewModel**:
  - Acts as a mediator between the View and Model layers.
  - Handles data fetching, filtering, and state management (loading/error states).
  - The `BookViewModel` class publishes data updates for the View to observe.

- **View**:
  - The user interface layer built using Jetpack Compose.
  - Displays a collection of books and allows user searches.
  - Listens to the ViewModel for updates to reflect changes in the UI.

## 2. Design Decisions

- **Dependency Injection (DI)**:
  - The ViewModel uses DI for the `Repository`, allowing easy swapping of implementations (e.g., mock services for testing).

- **State Management**:
  - The app uses `State` in Jetpack Compose to manage UI state effectively, ensuring the UI responds to changes automatically.

- **Separation of Concerns**:
  - Each component (Model, ViewModel, View) has a distinct responsibility, making the code modular and easier to maintain.

- **Testability**:
  - The ViewModel is designed for easy testing with mock services to simulate API responses.

## 3. Setting Up the Project

### Requirements

- Android Studio: Version 2020.3.1 or later
- Kotlin: Version 1.5 or higher
- Android SDK: Required for building and running the app

### Libraries/Frameworks Used

- **Room**: For local storage of book information.
- **Jetpack Compose**: For modern UI development.

## 4. Running the Project

### Clone the Repository

git clone https://github.com/tennet0505/Books-Android.git
cd Books-Android


Open the Project in Android Studio
- Open the Books-Android project in Android Studio.
- Build & Run
- Select your target device or emulator.
- Click the Run button or press Shift + F10 to build and run the app.

## 5. Unit Testing
- **Unit Test Setup
- **The project includes unit tests using mock services to simulate API responses for testing the ViewModel.
- **Running Tests
- **In Android Studio, open the Test directory.
- **Click on the Run icon next to the test class or use the terminal:
./gradlew test

## 6. Future Improvements
- **Error Handling: Implement robust error handling with retry mechanisms.
- **UI Enhancements: Improve loading indicators and error state displays.
- **Offline Mode: Expand local caching to allow offline functionality.

## 7. API
- **Project uses https://mockapi.io/projects

## 8. Video
- **Video walkthrough of the app: https://drive.google.com/file/d/1NuU2FsLDUgMeEHbEK8eRD46-hr4YKXfb/view?usp=sharing
- **All screenshots:              https://drive.google.com/drive/folders/157KvkD-2xz94J7ebhsaZOiLAe5XovJqd?usp=sharing

MainScreen screenshot:
![Screenshot_2024-10-17-14-58-16-134_com example books](https://github.com/user-attachments/assets/7b46d9ef-7a61-456e-8764-8693c21deed0)

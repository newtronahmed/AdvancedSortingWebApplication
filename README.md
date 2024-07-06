# Sorting Algorithm Application Design Documentation

## Overview

This document describes the design, architecture, and components of the Sorting Algorithm application built using Java's Spring framework (not Spring Boot). The application allows users to select and apply various sorting algorithms to an array of numbers. It provides a home page to view and add algorithms, and a details page to sort arrays using the selected algorithm. The application leverages Spring HATEOAS to provide links to related algorithm endpoints.

## Architecture

The application follows a typical layered architecture with the following main layers:

### Presentation Layer

- **Home Page**: Displays the list of available sorting algorithms with buttons to view details and sort using the selected algorithm.
- **Algorithm Details Page**: Provides a form to input an array of numbers and sort it using the selected algorithm.
- **Add Algorithm Modal**: Allows users to add a new algorithm to the list.

### Service Layer

- **SortingService**: Contains the logic for different sorting algorithms (Heap Sort, Quick Sort, Merge Sort, Radix Sort, and Bucket Sort).
- **Algorithm Management**: Manages the addition of new algorithms to the algorithm map.

### Data Layer

- **Algorithm Map**: A HashMap that holds the available algorithms.

### Controller Layer

- **SortingController**: Handles API requests for fetching algorithms, sorting arrays, and adding new algorithms.

## Architecture Diagram
````
+----------------------+
|      Presentation    |
| +------------------+ |
| | Home Page        | |
| | Algorithm Details| |
| | Add Algorithm    | |
| +------------------+ |
+----------|-----------+
|
+----------v-----------+
|      Controller      |
| +------------------+ |
| | SortingController| |
| +------------------+ |
+----------|-----------+
|
+----------v-----------+
|       Service        |
| +------------------+ |
| | SortingService   | |
| | Algorithm Mgmt   | |
| +------------------+ |
+----------|-----------+
|
+----------v-----------+
|        Data          |
| +------------------+ |
| | Algorithm Map    | |
| +------------------+ |
+----------------------+
````
## Component Descriptions

### Presentation Layer

#### Home Page

- Fetches the list of available algorithms via an AJAX call to the `/api` endpoint.
- Displays the algorithms with buttons linking to the algorithm details page.
- Provides a button to open the Add Algorithm modal.

#### Algorithm Details Page

- Fetches details of the selected algorithm via an AJAX call to the `/api/{name}` endpoint.
- Provides a form to input an array of numbers.
- Submits the array to the `/api/sort/{name}` endpoint to get the sorted array.
- Displays the sorted array and provides links to other related algorithms using Spring HATEOAS.

#### Add Algorithm Modal

- Contains a form to input the name and details of a new algorithm.
- Submits the new algorithm via an AJAX call to the `/api/add` endpoint.
- Updates the list of algorithms displayed on the home page.

### Service Layer

#### SortingService

- Implements the sorting logic for various algorithms:
    - Heap Sort
    - Quick Sort
    - Merge Sort
    - Radix Sort
    - Bucket Sort

#### Algorithm Management

- Manages the addition of new algorithms to the algorithm map.

### Data Layer

#### Algorithm Map

- A HashMap that stores the available algorithms by name.

### Controller Layer

#### SortingController

- Handles API requests:
    - `GET /api`: Returns the list of available algorithms.
    - `GET /api/{name}`: Returns details of the specified algorithm.
    - `POST /api/sort/{name}`: Sorts the given array using the specified algorithm.
    - `POST /api/add`: Adds a new algorithm to the algorithm map and returns the updated list.

## Spring Modules Used

- **Spring Core**: Provides the core functionality including dependency injection.
- **Spring Web MVC**: Facilitates the creation of RESTful web services.
- **Spring HATEOAS**: Helps in building hypermedia-driven RESTful web services.
- **Spring Context**: Provides the application context configuration and bean management.
- **Spring AOP**: Used for aspect-oriented programming, though not explicitly detailed in this documentation.

## API Endpoints

- `GET /api`: Fetches the list of available algorithms.
- `GET /api/{name}`: Fetches details of the specified algorithm.
- `POST /api/sort/{name}`: Sorts the provided array using the specified algorithm.
- `POST /api/add`: Adds a new algorithm and returns the updated list.

## Flow of Actions

### Home Page Load:

- An AJAX call is made to `GET /api`.
- The response is used to populate the list of algorithms.

### Add Algorithm:

- The user fills the form in the Add Algorithm modal.
- An AJAX call is made to `POST /api/add`.
- The response updates the list of algorithms.

### View Algorithm Details:

- The user selects an algorithm from the home page.
- An AJAX call is made to `GET /api/{name}`.
- The response populates the algorithm details page.

### Sort Array:

- The user inputs an array and submits the form.
- An AJAX call is made to `POST /api/sort/{name}`.
- The response displays the sorted array and links to other algorithms.

### Try Another Algorithm:

- The user clicks a related algorithm button.
- An AJAX call is made to `POST /api/sort/{relatedAlgorithm}` with the current array.
- The response displays the sorted array using the new algorithm.

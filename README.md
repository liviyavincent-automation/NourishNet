# NOURISHNET: Smart Food Redistribution System

## Project Overview

NourishNet is a smart food redistribution platform designed to reduce food wastage and support poverty reduction by connecting food donors, NGOs, and volunteers.

The system enables surplus food from donors to be collected, managed, and redistributed to people in need through an organized digital platform.

This project contributes to:

- SDG 1: No Poverty
- SDG 2: Zero Hunger


## Problem Statement

Large amounts of edible food are wasted every day while many people struggle with food insecurity.

NourishNet provides a technology-driven solution to bridge the gap between surplus food providers and organizations working towards hunger reduction.


## Key Features

### User Management
- User registration and login
- Role-based access:
  - Donor
  - NGO
  - Volunteer

### Donor Module
- Donate surplus food
- View donation history
- Track donation status
- Receive nearest NGO recommendations

### NGO Module
- View available food donations
- Accept pickup requests
- Monitor pickup status
- Manage redistribution process

### Volunteer Module
- View assigned pickups
- Update pickup completion status
- Start live tracking

### Live GPS Tracking
- Real-time volunteer location tracking
- Interactive map using Leaflet.js
- Displays:
  - Donor location
  - NGO location
  - Volunteer location

### Smart Features
- Food freshness prediction
- Nearest NGO recommendation
- AI chatbot assistance


## Technology Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- REST APIs

### Database
- MySQL

### Frontend
- HTML
- JavaScript

### APIs and Tools
- Leaflet.js
- OpenStreetMap
- Maven
- Git/GitHub


## System Architecture
NourishNet
│
├── backend
│   ├── src/main/java
│   │   └── com.nourishnet
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── entity
│   │       └── dto
│   │
│   └── pom.xml
│
├── frontend
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── donor-dashboard.html
│   ├── ngo-dashboard.html
│   ├── volunteer-dashboard.html
│   └── javascript files
│
└── README.md
## Project Workflow

1. User registers and logs into the system.

2. Based on role, users access their respective dashboards:
   - Donor
   - NGO
   - Volunteer

3. Donor adds surplus food details.

4. System checks food freshness and recommends nearby NGOs.

5. NGO accepts the donation request.

6. Volunteer gets assigned for pickup.

7. Volunteer updates pickup status and location.

8. Live tracking displays donor, NGO, and volunteer locations.

9. Donation status is updated after successful delivery.

## Database Design

Main database tables:

- users
  - Stores user registration details and roles.

- food_donations
  - Stores donated food information.

- pickup_requests
  - Stores NGO acceptance and volunteer assignment details.

- volunteer_location
  - Stores volunteer GPS coordinates.

- chatbot_logs
  - Stores chatbot conversations.

  ## How to Run the Project

### Backend Setup

1. Install Java JDK 21 and Maven.

2. Configure MySQL database.

3. Open backend project.

4. Run Spring Boot application:

mvn spring-boot:run


Backend will start at:

http://localhost:8080


### Frontend Setup

Open frontend HTML files in a browser.

## Screenshots

Screenshots of the application:

- Login Page
- Registration Page
- Donor Dashboard
- NGO Dashboard
- Volunteer Dashboard
- GPS Tracking Map
- Chatbot Interface

## Future Enhancements

- Mobile application development
- Cloud deployment
- Advanced AI-based food demand prediction
- Route optimization for volunteers
- Push notifications

## Author

Liviya Vincent  
MCA Project  
NourishNet: Smart Food Redistribution System



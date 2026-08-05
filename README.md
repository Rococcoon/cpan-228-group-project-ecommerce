# CTRL ALT KEEB

# CPAN-228 · Web Application Development

# Project Deliverables Overview

> **A note on creativity**
>
> The requirements below describe the technical capabilities your app must demonstrate. How you present them, what you name things, how your UI is laid out, and what story your data tells—that part is entirely up to you. Aim to build something you would actually want to show in a portfolio.


---

# Deliverable 1 · 25%

## Web Front-End & Database Integration

### The Goal

Build a working web application that lets a user interact with your domain through a browser.

By the end of this deliverable your app should have:

- Real pages
- A real form
- A real database behind it

Someone should be able to open it, look around, add something, and see it persist.

## What You Need to Demonstrate

### Navigation & Pages

Your application needs:

- A home page
- At least **two additional informational pages** relevant to your domain

Requirements:

- Tell a coherent story about what your app does.
- Use semantic HTML.
- Use Thymeleaf for templating.
- The home page should be the natural entry point, not just a placeholder.

### At Least One Form That Does Something

Build a form that allows a user to add a primary entity to your system (for example, a product, course, etc.), using **Assignment 0 as your compass**.

The form should include:

- Clear labels
- Appropriate field types
- Server-side validation

Validation must ensure:

- Required fields are present
- Numeric values are within a sensible range
- Categorical fields (type, brand, category, etc.) come from predefined options

When validation fails, display clear, user-friendly error messages.

### Database Persistence

Form submissions must save to a **real database** (no in-memory lists).

Requirements:

- Use Spring Data JPA
- Define an entity with proper annotations
- Persist through a repository
- Include:
  - Generated ID
  - Timestamp

After saving, redirect the user to a page showing the newly created record.

### List View with Search & Sort

Display all saved records in a clean layout.

Support:

- Filtering by at least **two meaningful attributes**
- Sorting by at least **two fields**

Use **Spring Data JPA pagination and sorting** on the server side.

Do **not** manipulate an in-memory list.

### Sample Data on Startup

Seed the database using `data.sql`.

Requirements:

- Populate realistic sample data
- App should display records immediately after startup
- No manual data entry required

### Presentation & Styling

Use Bootstrap consistently across all pages.

Requirements:

- Professional appearance
- Responsive layout

### Grading Focus

- Controllers and routing are correct
- Thymeleaf templates render properly
- Validation rejects invalid input with clear messages
- Data saves and loads from the database
- List view filters and sorts correctly
- Sample data loads on startup
- Bootstrap styling is applied consistently

---

# Deliverable 2 · 15%

## Security & User Management

### The Goal

Add a real security model to the application built in Deliverable 1.

Different users should:

- Log in
- Have different permissions

## What You Need to Demonstrate

### Users and Registration

Implement user registration.

Requirements:

- Passwords encoded using BCrypt
- Never store plaintext passwords
- `User` entity implements `UserDetails`
- Store users using a Spring Data repository

### Roles That Make Sense for Your Domain

Create at least **three meaningful roles**.

Examples:

**E-commerce**

- Admin
- Staff
- Customer

**Learning Platform**

- Admin
- Instructor
- Student

Roles should actually control permissions—not simply exist as labels.

### Login Page

Create a custom login page.

Requirements:

- Matches the application's styling
- Displays helpful error messages for failed logins

### Protected Routes

Configure a `SecurityFilterChain`.

Public pages should include:

- Home
- About
- Registration

Authentication should be required for:

- Create
- Edit
- Delete operations

Some actions must also require specific roles.

### Admin Interface

Create an admin-only management page.

Requirements:

- View entities
- Edit entities
- Delete entities

This page must:

- Be inaccessible to non-admin users
- Clearly appear as an administrative interface

### Grading Focus

- Registration encodes passwords correctly
- Users are saved properly
- Login and logout function correctly
- Protected routes redirect unauthenticated users
- Roles enforce permissions correctly
- Admin page is functional and restricted
- Logged-in user is displayed where appropriate
- Custom login page is styled and shows errors

---

# Deliverable 3 (Final Project) · 20%

## Microservices, REST APIs & DevOps

### The Goal

Expand the application into a distributed system.

Extract one responsibility into a separate Spring Boot microservice exposing a REST API, then integrate it with the main application.

## What You Need to Demonstrate

### A Second Microservice

Build a separate Spring Boot application responsible for a specialized resource.

Examples:

**E-commerce**

- Distribution Centre Service

**Learning Platform**

- Grading Service

Requirements:

- Separate database
- Separate entities
- Independent business logic
- Not merely a pass-through

### Full REST API

Expose a complete REST API.

Endpoints should include:

- `GET` all
- `GET` by ID
- `POST`
- `PUT`
- `DELETE`

Also provide at least one custom endpoint that filters or queries using multiple parameters.

Use:

- Proper HTTP semantics
- Appropriate HTTP status codes

### Security on the Microservice

Protect endpoints using HTTP Basic Authentication.

The microservice should maintain its own user configuration:

- In-memory users **or**
- JDBC users

This configuration should be independent of the primary application.

### Spring Profiles

Configure two Spring profiles.

#### Dev

- H2 in-memory database

#### QA

- PostgreSQL
- Docker

Each profile should include:

- Separate `application.yml`
- Separate initialization script

### Primary App Consumes the Microservice

Use `RestTemplate` in the primary application.

Requirements:

- At least one form or admin action calls the microservice
- Handle failures gracefully if the microservice is unavailable
- Do not allow the primary application to crash
- Admin dashboard displays:
  - Local database data
  - Remote microservice data

```

```

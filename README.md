# BookMyHotel
## Description:
  - A single-page Airbnb-like web application

## Features:
  - Registration as a host/guest
    - Password encryption
    - Duplicated users check
  - Log in (with JWT authentication) as a host/guest
  - Stay search, reservation, listing, deletion as a guest
  - Stay adding, listing, updating, and deletion as a host

## FrontEnd
  - Typescript
  - React, with React-Bootstrap

## Backend
  - RESTful services by Spring Boot
    - definition of allowed http requests in configuration 
  - Hibernate
    - interact with database

## Databases
  - Structured data: 
    - MySQL on AWS RDS
  - Media files (e.g., images): 
    - S3 on AWS (including: bucket, user)

## Deployment
  - AWS EC2 (including: Security Groups) via Docker

## Other technologies:
  - Elasticsearch, install on EC2 AWS
    - support geo-based stay searching
  - LocationService hosted on AWS
    - Get latitude and longitude based on address

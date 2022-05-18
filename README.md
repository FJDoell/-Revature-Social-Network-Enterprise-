# Bender Social
A social media application themed around Avatar: The Last Airbender.

In this social network, everyone is friends with everyone. As a user, you will be able to register and login to your account. When you successfully login, you are automatically friends with everyone which means you will see a feed of everyone's posts. Users can create a post and like other people's posts. Users may also post comments in response to posts. Users may also choose a bending style.

## Technologies Used
* Java
* HTML
* CSS
* JavaScript
* Spring Boot - version 2.6
* Spring Data JPA
* Spring Mail
* Spring Security
* Spring Cloud AWS
* Spring Test Framework - version 5.3
* Project Lombok - version 1.18.22
* Postgresql - version 42.3.3
For testing:
* H2 Database
* Spring Test
* JUnit - version 4.13.2

## Features
List of features ready and TODOs for future development
* Users may Login and Register an account.
* Forgot Password feature sends an email.
* On the Home page, users may create new posts, like existing posts, and comment on posts.
* Images may be uploaded on new posts.
* Account name and profile picture may be updated.
* Posts may be listed by latest first, or by the logged in user.
* Censorship system for specific offensive words.
* Redirect to login for security if the user is unauthorized.

## To-do list:
* Add pagination to posts by user. Make timeline retrieve by posts.
* Add viewing other users' profiles.
* Add full automated testing using JUnit.
* Add H2 database dependency for local testing purposes.

## Getting Started
To start your own social media application, first you will need an Amazon S3 database and postgresql database.
After setting that up, set the following environment variables:
* DATABASE_ENDPOINT: The endpoint of your JDBC Postgresql database, including the port. E.g. domain:port.
* DATABASE_USERNAME: The username of the user accessing the database.
* DATABASE_PASSWORD: The password of the user accessing the database.
* Set the bucket name in the application.yml file.
* AWS_S3_KEY: The S3 key of an IAM user on amazon.
* AWS_S3_SKEY: The secret key of the IAM user.

For public use, you may deploy to and run your application on a remote server, such as EC2.

## Usage
* First, register an account. You will need to supply an email, a username, a first and last name for display, and a password.
* Login with your newly created account. Your accounts' posts will now be listed.
* You may go to 'Timeline' to list all posts.
* Now you may create a new post. Feel free to add images.
* Your new posts will be listed.
* You may update your profile information on the left side, such as setting a bending style, changing your name, or uploading a profile picture.

## License
This project uses the following license: MIT license.
Copyright (c) 2022 Frederick Doell. All Rights Reserved.

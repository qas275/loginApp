Login Application

Description:
This is a simple login application that only has 3 views, login page, home page(displays role of user and a link only managers can see and click which leads to the administrator page), administrator page. 

Login Page:
Users will land on this page with the base url and "/login" path. Any other paths that do not match the given paths will also lead back to this page.
On landing, the application will check for a JWT (WS_JWT) in the browser session storage, should it exist it will auto login the user to the home page.
Else, a form will be displayed for users to key in their username and password. The form fields are all required and on submission the app will check against the database for correct username and password pair. Only when it matches, a JWT is stored in the session storage and the page redirects to the home page.

Home Page:
On landing, the application will check for a JWT and be sent to the backend for decryption into the username and role stored in the JWT, which will be displayed on the home page. If a JWT does not exist the page will redirect to the login page.
Only those with a manager role will be able to see a special link that leads to the administrator page.

Administrator page:
On landing, the application will check for a JWT and be sent to the backend for decryption into the username and role stored in the JWT. Should the JWT not exist or the role stored in the JWT is not a manager, an alert will be raised on the browser and the application will redirect back to the home page.

Backend: Java, Spring Boot, Maven 
Frontend: Angular
Database: MySQL
\# Database Relationships



Employees

\- department\_id -> Departments.department\_id

\- user\_id -> Users.user\_id



Attendance

\- employee\_id -> Employees.employee\_id



Leave Requests

\- employee\_id -> Employees.employee\_id



Users

\- role\_id -> Roles.role\_id


# CMS Web Application - The QP Project (Qeasy Peasy) 🌱

## Overview / 개요 📖
- This project is a CMS web application developed using MySQL, Spring Boot, and MyBatis. The application provides users with the ability to register quizzes and participate in them. It includes features such as user registration, login/logout, password recovery via email, quiz creation and participation, user profile management, administration dashboard, and more.
- 이 프로젝트는 MySQL, Spring Boot 및 mybatis 를 사용하여 개발된 CMS 웹 애플리케이션입니다. QP 앱에는 사용자가 퀴즈를 등록하고 응시하는 기능을 제공합니다: 
- 사용자 등록, 로그인/로그아웃, 이메일로 비밀번호 찾기, 퀴즈 생성 및 응시, 사용자 프로필 관리, 관리자 대시보드 등 다양한 기능이 포함되어 있습니다. 

## Project Environment / 프로젝트 환경 💻

- The CMS Web Application is developed using the following technologies and tools:
- CMS 웹 애플리케이션은 다음과 같은 기술과 도구를 사용하여 개발되었습니다:

- **Backend:**
  - Java JDK version: 17.0.6
  - Spring Boot framework version: 3.0.4
  - MyBatis version: 3.5.11
  - MySQL database (Community version: 8.0.32)

- **Frontend:**
  - Falcon v3.17.0 which is built with Bootstrap v5.3.0-alpha3

- **Development Tools:**
  - IDE - IntelliJ IDEA 2023.1 (Community Edition)
  - GitHub for version control

We extend our special thanks to Shajeeb, the original author and creator of [Falcon – Admin Dashboard & WebApp Bootstrap Template](https://themes.getbootstrap.com/product/falcon-admin-dashboard-webapp-template/). 🙌 <br>

## Features / 기능들 ✨

### Authorization

- User Registration ✔️
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/d8d48321-55e2-4f5a-b091-1bdedac5706a)
- Login / Logout ✔️
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/978a40b7-2eb2-4ac6-8db4-85ecf931fbec)
- Password Recovery and Email Notification ✔️
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/555c2e09-b30f-4bde-891f-efba3dcb7126)

### Quiz
- Quiz Home Page ✔️ <br>
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/75e4a4d9-1d78-40e9-8cdd-182c2566aa22)
- Quiz Details <br>
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/b9f3352b-fd2f-494e-9689-23e944793395)
- Quiz Creating Tool
- Taking Quizzes ✔️ <br>
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/200b5528-20ca-4ac3-8d3e-dcef5ac72a7d) <br>
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/dd41c81f-fe1a-45aa-aaa3-614cdac888a9) <br>
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/6bcaeb82-067d-404f-8fe6-7fbdc93ddad3)

### MyPage

- Update Basic Information ✔️

- Update Password ✔️

- Account Deletion ✔️


### Admin

- Dashboard
- Member Management ✔️
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/d444cb6c-f180-4d78-a064-17d686c79e75)

## ER Diagram of qp project 📊
![image](https://github.com/joyccino/The-Phoenix/assets/67300266/c59bc246-2b23-4891-8fed-7444ca7f42fe)
- To implement the QP project, you can refer to the ER diagram and create the necessary tables in advance.
- Here is a list of the tables that you must create for the QP project:
1. Members: This table stores information about the members or users of the QP project. It may include fields such as member ID, name, email, and other relevant details.

2. Subjects: This table represents the subjects or categories of quizzes in the QP project. It may contain fields like subject ID, subject name, and any additional information related to subjects.

3. Quizzes: This table holds information about the quizzes available in the QP project. It may include fields like quiz ID, quiz title, subject ID (foreign key referencing the Subjects table), and other relevant details.

4. Questions: This table stores the questions associated with the quizzes. It may have fields like question ID, question text, quiz ID (foreign key referencing the Quizzes table), and any additional attributes related to questions.

5. QuizHistory: This table tracks the history of quizzes attempted by members. It may include fields such as history ID, member ID (foreign key referencing the Members table), quiz ID (foreign key referencing the Quizzes table), and other relevant details like the score or completion status.

6. QuestionOptions: This table represents the options or choices for multiple-choice questions. It may contain fields like option ID, question ID (foreign key referencing the Questions table), option text, and any additional attributes related to options.

7. VisitHistory: This table tracks the visit history of members on the QP project. It may include fields like visit ID, member ID (foreign key referencing the Members table), timestamp, and any additional details related to visits.

8. Universities: Universities: This table stores information about universities related to the QP project. It may include fields like university ID, university name, website domain and other relevant details. In the QP project, university differentiation is done based on the domain address of the email during the registration process.

- Make sure to create these tables in your database, following the appropriate schema and relationships based on the ER diagram.


## Installation / 설치 ⚙️
1. Clone the repository: `git clone https://github.com/joyccino/The-Phoenix.git`
2. Set up MySQL database and configure the connection in the application.properties file.
3. Build and run the Spring Boot application.

For detailed instructions and additional configuration options, please refer to the documentation in the repository.

## Contributing 👥
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request in the repository.

## License 📝
This project is licensed under the [MIT License](LICENSE).

## Acknowledgements 🙏
Special thanks to all contributors and open-source projects that made this application possible.

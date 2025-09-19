# 🧠 AI-Powered Quiz Application

A modern, full-stack quiz application built with React, Spring Boot, and Google's Gemini AI. Create custom quizzes, take AI-generated quizzes, and track your progress!

## ✨ Features

- 🎯 **Custom Quiz Creation**: Create your own quizzes with multiple choice questions
- 🤖 **AI-Generated Quizzes**: Generate quizzes using Google's Gemini AI
- 👤 **User Authentication**: Register, login, and manage your account
- 📊 **Progress Tracking**: View your quiz attempts and scores
- 🎨 **Modern UI**: Beautiful, responsive interface with Bootstrap
- 🔒 **Secure**: JWT-based authentication and secure API endpoints

## 🛠️ Tech Stack

### Frontend

- **React 18** - Modern UI framework
- **React Router** - Client-side routing
- **Bootstrap 5** - Responsive design
- **React Toastify** - User notifications

### Backend

- **Spring Boot 2.7** - RESTful API
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database operations
- **H2 Database** - In-memory database (development)
- **Google Gemini AI** - AI-powered quiz generation

## 🚀 Live Demo

- **Frontend**: [Deployed on Vercel](https://your-app.vercel.app)
- **Backend**: [Deployed on Render](https://your-backend.onrender.com)

## 📦 Installation

### Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- Maven (or use Maven Wrapper)
- Google Gemini API key

### Local Development

1. **Clone the repository**

   ```bash
   git clone https://github.com/Kamal-Sin/AI_Quiz_Application.git
   cd AI_Quiz_Application
   ```

2. **Install frontend dependencies**

   ```bash
   npm install
   ```

3. **Configure environment variables**

   - Create `.env` file in root directory
   - Add your Gemini API key:
     ```
     REACT_APP_GEMINI_API_KEY=your_gemini_api_key_here
     ```

4. **Start the applications**

   ```bash
   # Option 1: Use the startup script
   ./start-apps.bat  # Windows
   ./start-apps.ps1  # PowerShell

   # Option 2: Manual startup
   # Terminal 1 - Backend
   ./mvnw.cmd spring-boot:run

   # Terminal 2 - Frontend
   npm start
   ```

5. **Access the application**
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console

## 🌐 Deployment

### Frontend (Vercel)

1. Connect your GitHub repository to Vercel
2. Set environment variable: `REACT_APP_API_URL`
3. Deploy automatically

### Backend (Render)

1. Connect your GitHub repository to Render
2. Set environment variable: `GEMINI_API_KEY`
3. Configure as Java Web Service

See [DEPLOYMENT-GUIDE.md](DEPLOYMENT-GUIDE.md) for detailed instructions.

## 📁 Project Structure

```
AI_Quiz_Application/
├── src/                    # React frontend
│   ├── components/         # React components
│   ├── assets/            # Static assets
│   └── index.js           # Entry point
├── src-java/              # Spring Boot backend
│   └── main/java/com/quizapp/
│       ├── controller/    # REST controllers
│       ├── models/        # JPA entities
│       ├── services/      # Business logic
│       └── filters/       # Security filters
├── public/                # Public assets
├── pom.xml               # Maven configuration
├── package.json          # Node.js dependencies
└── README.md             # This file
```

## 🔧 Configuration

### Environment Variables

#### Frontend

- `REACT_APP_API_URL`: Backend API URL
- `REACT_APP_GEMINI_API_KEY`: Gemini API key (optional)

#### Backend

- `GEMINI_API_KEY`: Google Gemini API key
- `PORT`: Server port (auto-set by deployment platform)

### Database

- **Development**: H2 in-memory database
- **Production**: Configure via environment variables

## 🎯 API Endpoints

### Authentication

- `POST /user/register` - User registration
- `POST /user/login` - User login
- `GET /user/getUser` - Get current user

### Quizzes

- `POST /quiz/create` - Create custom quiz
- `GET /quiz/getQuiz/{id}` - Get quiz details
- `POST /quiz/submit` - Submit quiz answers
- `POST /quiz/generate-ai` - Generate AI quiz

### User Data

- `GET /user/attempted` - Get user's quiz attempts
- `GET /user/quizzes` - Get user's created quizzes

## 🤖 AI Quiz Generation

The application uses Google's Gemini AI to generate quizzes based on:

- **Grade Level**: Elementary, Middle, High School, College
- **Difficulty**: Easy, Medium, Hard
- **Subject**: Any academic subject
- **Topic**: Specific topic within the subject

## 🔒 Security & Auth

- Header-based authentication using a base64-encoded user id
  - After login, backend returns `{ id: <base64(userId)>, username }`
  - Frontend stores `id` as `pid` in `localStorage`
  - All authenticated requests include header: `pid: <base64-user-id>`
- Backend `IdFilter` validates `pid` and injects `userId` as a request attribute
- Password encryption with BCrypt
- Centralized CORS configuration in `WebConfig` (localhost, _.vercel.app, _.railway.app)
- Input validation on registration and login

### Public endpoints

- `POST /user/register`
- `POST /user/login`
- `POST /quiz/generate-ai`
- `GET /quiz/health`

### Status codes

- 200 OK for successful GET/POST with body
- 201 Created on resource creation
- 400 Bad Request for invalid input
- 401 Unauthorized for missing/invalid `pid`
- 404 Not Found when resource doesn’t exist
- 409 Conflict for business conflicts (e.g., already attempted, email exists)

## 🧪 Testing

### Frontend

```bash
npm test
```

### Backend

```bash
./mvnw.cmd test
```

## 📊 Performance

- **Frontend**: Optimized with React 18 features
- **Backend**: Spring Boot with optimized JPA queries
- **Database**: Efficient indexing and query optimization

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 🙏 Acknowledgments

- Google Gemini AI for quiz generation
- Spring Boot team for the excellent framework
- React team for the amazing frontend library
- Bootstrap team for the UI components

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/Kamal-Sin/AI_Quiz_Application/issues)
- **Documentation**: [DEPLOYMENT-GUIDE.md](DEPLOYMENT-GUIDE.md)
- **Email**: kamal364997@gmail.com

---

⭐ **Star this repository if you found it helpful!**

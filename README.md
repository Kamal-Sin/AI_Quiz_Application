# Quiz Application

A full-stack quiz application built with React (frontend) and Spring Boot (backend).

## Project Structure

```
├── src-java/main/java/com/quizapp/ # Spring Boot backend
│   ├── controller/                 # REST controllers
│   ├── models/                     # JPA entities
│   ├── services/                   # Business logic
│   ├── repository/                 # Data access layer
│   ├── dto/                        # Data transfer objects
│   ├── configurations/             # Spring configurations
│   ├── filters/                    # Custom filters
│   └── keys/                       # Composite keys
├── src-java/main/resources/        # Spring Boot resources
│   └── application.properties      # Application configuration
├── src/                            # React frontend
│   ├── components/                 # React components
│   │   ├── HomePage/               # Home page components
│   │   ├── PracticePage/           # Quiz practice components
│   │   ├── CreationsPage/          # Quiz creation components
│   │   └── RecentsPage/            # Recent quizzes components
│   ├── App.js                      # Main React component
│   └── index.js                    # React entry point
├── public/                         # React public files
├── assets/                         # Static assets (images, etc.)
├── package.json                    # Frontend dependencies
└── pom.xml                         # Backend dependencies
```

## Features

- **User Authentication**: Login system
- **Quiz Creation**: Create custom quizzes with multiple questions
- **Quiz Practice**: Take quizzes with timer and scoring
- **Recent Quizzes**: View recently attempted quizzes
- **Responsive Design**: Bootstrap-based UI

## Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- npm or yarn

## Setup and Running

### 1. Install Frontend Dependencies

```bash
npm install
```

### 2. Start Frontend (React)

```bash
npm start
```

The React app will run on http://localhost:3000

### 3. Start Backend (Spring Boot)

#### Option A: Using Maven Wrapper (Recommended)

```bash
# Set JAVA_HOME if not already set
set JAVA_HOME=C:\Program Files\Java\jdk-17

# Run the application
.\mvnw.cmd spring-boot:run
```

#### Option B: Using Maven (if installed globally)

```bash
mvn spring-boot:run
```

The Spring Boot app will run on http://localhost:8080

### 4. Database Console

- H2 Console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:quizdb`
- Username: `sa`
- Password: `password`

## API Endpoints

- `GET /api/quizzes` - Get all quizzes
- `POST /api/quizzes` - Create a new quiz
- `GET /api/quizzes/{id}` - Get quiz by ID
- `POST /api/quizzes/{id}/submit` - Submit quiz answers
- `GET /api/users` - Get user information

## Development Notes

- The application uses H2 in-memory database for development
- Frontend communicates with backend via REST API
- Bootstrap is used for styling
- React Router is used for navigation
- Toast notifications are implemented with react-toastify

## Quick Start

### Option 1: Using the Batch Script (Windows)

```bash
start-apps.bat
```

### Option 2: Manual Start

1. **Set JAVA_HOME** (if not already set):

   ```bash
   set JAVA_HOME=C:\Program Files\Java\jdk-23
   ```

2. **Start Backend**:

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

3. **Start Frontend** (in a new terminal):
   ```bash
   npm start
   ```

## Missing Assets

The following assets need to be added to the `assets/` directory:

- `menu button.jpg` - Menu button image
- `online-exam-or-test.webp` - Home page image

**Note**: The application has been modified to use placeholder elements instead of these images, so it will run without them.

## Current Status

✅ **Frontend**: React app running on http://localhost:3000 (Status: 200 OK)
✅ **Backend**: Spring Boot app running on http://localhost:8080 (Status: 401 Unauthorized - expected due to security)
✅ **Database**: H2 in-memory database accessible at http://localhost:8080/h2-console
✅ **Dependencies**: All required dependencies installed
✅ **Structure**: Proper Maven/React project structure
✅ **Compilation**: Both frontend and backend compile successfully
✅ **Applications**: Both frontend and backend are running and responding to requests
✅ **AI Quiz Generation**: Gemini API integration added (requires API key configuration)

## AI Quiz Generation Setup

To enable AI-generated quizzes using Google's Gemini API:

1. **Get a Gemini API Key**:

   - Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Create a new API key
   - Copy the API key

2. **Configure the API Key**:

   - Open `src-java/main/resources/application.properties`
   - Uncomment the line: `# gemini.api.key=YOUR_GEMINI_API_KEY_HERE`
   - Replace `YOUR_GEMINI_API_KEY_HERE` with your actual API key
   - Example: `gemini.api.key=AIzaSyC...`

3. **Restart the Backend**:

   - Stop the Spring Boot application
   - Restart it to load the new configuration

4. **Test the Feature**:
   - Go to the Practice page
   - Click on "Want to practice with A.I Generated Quiz"
   - Fill in the form (grade, difficulty, subject, topic)
   - Click "Start" to generate a quiz

**Features**:

- Generates 5 multiple-choice questions based on your specifications
- Questions are appropriate for the selected grade level and difficulty
- Automatic scoring and results display
- Option to retake quizzes
- Responsive UI with progress tracking

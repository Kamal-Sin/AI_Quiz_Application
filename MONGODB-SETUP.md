# MongoDB Atlas Setup Guide

This guide will help you set up MongoDB Atlas for production deployment of your Quiz Application.

## Step 1: Create MongoDB Atlas Account

1. Go to [MongoDB Atlas](https://www.mongodb.com/atlas)
2. Sign up for a free account
3. Create a new project

## Step 2: Create a Cluster

1. Click "Build a Database"
2. Choose "FREE" tier (M0)
3. Select your preferred cloud provider (AWS, Google Cloud, or Azure)
4. Choose a region close to your users
5. Click "Create"

## Step 3: Set Up Database Access

1. Go to "Database Access" in the left sidebar
2. Click "Add New Database User"
3. Choose "Password" authentication
4. Create a username and password (save these!)
5. Select "Read and write to any database"
6. Click "Add User"

## Step 4: Set Up Network Access

1. Go to "Network Access" in the left sidebar
2. Click "Add IP Address"
3. For production: Click "Allow Access from Anywhere" (0.0.0.0/0)
4. For development: Add your IP address
5. Click "Confirm"

## Step 5: Get Connection String

1. Go to "Database" in the left sidebar
2. Click "Connect"
3. Choose "Connect your application"
4. Copy the connection string

## Step 6: Configure Environment Variables

### Your MongoDB Atlas Connection String:

```
mongodb+srv://quizzy:quizzy@cluster0.5nc0uzo.mongodb.net/quizapp?retryWrites=true&w=majority&appName=Cluster0
```

### For Railway Deployment:

1. Go to your Railway project
2. Add these environment variables:
   ```
   SPRING_PROFILES_ACTIVE=prod
   MONGODB_URI=mongodb+srv://quizzy:quizzy@cluster0.5nc0uzo.mongodb.net/quizapp?retryWrites=true&w=majority&appName=Cluster0
   GEMINI_API_KEY=your_gemini_api_key
   ```

### For Heroku Deployment:

1. Go to your Heroku app settings
2. Add these config vars:
   ```
   SPRING_PROFILES_ACTIVE=prod
   MONGODB_URI=mongodb+srv://quizzy:quizzy@cluster0.5nc0uzo.mongodb.net/quizapp?retryWrites=true&w=majority&appName=Cluster0
   GEMINI_API_KEY=your_gemini_api_key
   ```

### For Render Deployment:

1. Go to your Render service
2. Add these environment variables:
   ```
   SPRING_PROFILES_ACTIVE=prod
   MONGODB_URI=mongodb+srv://quizzy:quizzy@cluster0.5nc0uzo.mongodb.net/quizapp?retryWrites=true&w=majority&appName=Cluster0
   GEMINI_API_KEY=your_gemini_api_key
   ```

## Step 7: Update Connection String

Replace the placeholder values in your connection string:

- `username`: Your MongoDB Atlas username
- `password`: Your MongoDB Atlas password
- `cluster`: Your cluster name

## Step 8: Deploy

1. Commit your changes to git
2. Push to your deployment platform
3. The application will automatically use MongoDB in production

## Important Notes

- **Free Tier Limits**: MongoDB Atlas free tier has 512MB storage and shared RAM
- **Security**: Never commit your connection string to git
- **Backup**: MongoDB Atlas provides automatic backups
- **Monitoring**: Use Atlas dashboard to monitor your database

## Troubleshooting

### Connection Issues:

- Check if your IP is whitelisted
- Verify username/password
- Ensure cluster is running

### Performance Issues:

- Check Atlas dashboard for slow queries
- Consider upgrading to paid tier for better performance

## Local Development

For local development, the app will still use H2 database. To use MongoDB locally:

1. Set environment variable: `SPRING_PROFILES_ACTIVE=prod`
2. Set `MONGODB_URI` to your Atlas connection string
3. Run the application

## Migration from H2 to MongoDB

When you first deploy with MongoDB:

- The database will be empty
- Users will need to register again
- All quiz data will be fresh

To migrate existing data, you would need to write a migration script.

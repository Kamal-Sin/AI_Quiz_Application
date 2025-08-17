# Deployment Guide for Quiz Application

## 🚀 Frontend Deployment (Vercel)

### Prerequisites

- GitHub repository connected to Vercel
- Vercel account (free at vercel.com)

### Step 1: Deploy Frontend to Vercel

1. **Go to [Vercel.com](https://vercel.com)**
2. **Sign in with GitHub**
3. **Click "New Project"**
4. **Import your GitHub repository**: `AI_Quiz_Application`
5. **Configure the project**:
   - **Framework Preset**: Create React App
   - **Root Directory**: `./` (root of repository)
   - **Build Command**: `npm run build`
   - **Output Directory**: `build`
   - **Install Command**: `npm install`

### Step 2: Environment Variables

Add these environment variables in Vercel:

- `REACT_APP_API_URL`: Your backend URL (e.g., `https://your-backend.onrender.com`)

### Step 3: Deploy

Click "Deploy" and wait for the build to complete.

---

## 🔧 Backend Deployment Options (FREE)

### Option 1: Render (Recommended - Completely Free)

#### Prerequisites

- Render account (free at render.com)
- GitHub repository

#### Steps:

1. **Go to [render.com](https://render.com)**
2. **Sign up with GitHub**
3. **Click "New +" → "Web Service"**
4. **Connect your GitHub repository**
5. **Configure the service**:
   - **Name**: `quiz-app-backend`
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/quiz-app-1.0-SNAPSHOT.jar`
   - **Plan**: `Free`
6. **Add environment variables**:
   - `GEMINI_API_KEY`: Your Gemini API key
7. **Click "Create Web Service"**

**Free Tier**: 750 hours/month (enough for 24/7 operation)

### Option 2: Railway (Easy Setup)

#### Prerequisites

- Railway account (free at railway.app)
- GitHub repository

#### Steps:

1. **Go to [railway.app](https://railway.app)**
2. **Sign up with GitHub**
3. **Click "New Project"**
4. **Select "Deploy from GitHub repo"**
5. **Choose your repository**
6. **Add environment variables**:
   - `GEMINI_API_KEY`: Your Gemini API key
7. **Deploy automatically**

**Free Tier**: $5 credit monthly (sufficient for small apps)

### Option 3: Fly.io (Most Generous Free Tier)

#### Prerequisites

- Fly.io account (free at fly.io)
- Fly CLI installed

#### Steps:

1. **Install Fly CLI**:

   ```bash
   # Windows (PowerShell)
   iwr https://fly.io/install.ps1 -useb | iex
   ```

2. **Sign up and login**:

   ```bash
   fly auth signup
   fly auth login
   ```

3. **Deploy**:
   ```bash
   fly launch
   fly deploy
   ```

**Free Tier**: 3 shared-cpu VMs, 3GB persistent volume storage

### Option 4: Google Cloud Run

#### Prerequisites

- Google Cloud account
- Google Cloud CLI

#### Steps:

1. **Go to [console.cloud.google.com](https://console.cloud.google.com)**
2. **Create new project**
3. **Enable Cloud Run API**
4. **Deploy via Google Cloud Console**

**Free Tier**: 2 million requests/month, 360,000 vCPU-seconds

---

## 🔗 Connect Frontend to Backend

### Update API URLs

After deploying the backend, update the frontend API calls:

1. **Find all API calls** in your React components
2. **Replace localhost URLs** with your production backend URL
3. **Use environment variables**:
   ```javascript
   const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";
   ```

### Update CORS Configuration

In your Spring Boot backend, update CORS to allow your Vercel domain:

```java
@CrossOrigin(origins = {"https://your-app.vercel.app", "http://localhost:3000"})
```

---

## 📝 Environment Variables

### Frontend (Vercel)

- `REACT_APP_API_URL`: Backend API URL
- `REACT_APP_GEMINI_API_KEY`: Gemini API key (if needed)

### Backend (Render/Railway/Fly.io)

- `GEMINI_API_KEY`: Gemini API key
- `PORT`: Server port (usually auto-set)

---

## 🧪 Testing Deployment

1. **Test Frontend**: Visit your Vercel URL
2. **Test Backend**: Test API endpoints
3. **Test Database**: Verify data persistence
4. **Test AI Features**: Ensure Gemini API works

---

## 🔄 Continuous Deployment

### Automatic Deployments

- **Vercel**: Automatically deploys on every push to main branch
- **Render**: Automatic deployments enabled by default
- **Railway**: Automatic deployments enabled by default
- **Fly.io**: Can be configured for automatic deployment

### Manual Deployments

```bash
# Frontend (Vercel)
git push origin main  # Triggers automatic deployment

# Backend (Render/Railway)
# Automatic with git push to main branch
```

---

## 🐛 Troubleshooting

### Common Issues:

1. **CORS Errors**: Update backend CORS configuration
2. **API 404**: Check API URLs in frontend
3. **Database Issues**: Verify database connection strings
4. **Build Failures**: Check build logs in deployment platform

### Debug Commands:

```bash
# Check Render logs
# Use Render dashboard

# Check Railway logs
# Use Railway dashboard

# Check Fly.io logs
fly logs

# Test API locally
curl https://your-backend.onrender.com/health
```

---

## 📊 Monitoring

### Vercel Analytics

- Built-in analytics for frontend
- Performance monitoring
- Error tracking

### Render/Railway Monitoring

- Application metrics
- Error logs
- Performance monitoring

---

## 🔒 Security Considerations

1. **Environment Variables**: Never commit API keys
2. **CORS**: Restrict to specific domains
3. **HTTPS**: Always use HTTPS in production
4. **API Keys**: Rotate keys regularly

---

## 🎯 Final Checklist

- [ ] Frontend deployed to Vercel
- [ ] Backend deployed to Render/Railway/Fly.io
- [ ] Database configured and working
- [ ] API URLs updated in frontend
- [ ] CORS configured correctly
- [ ] Environment variables set
- [ ] AI features working
- [ ] User registration/login working
- [ ] Quiz creation and taking working
- [ ] Recent attempts working
- [ ] All features tested in production

---

## 🚀 Quick Deploy Commands

```bash
# Deploy to Vercel (automatic with git push)
git add .
git commit -m "Ready for deployment"
git push origin main

# Backend deployment is automatic with git push
# Just configure in Render/Railway/Fly.io dashboard
```

---

## 💰 Cost Comparison

| Platform             | Free Tier          | Paid Plans    |
| -------------------- | ------------------ | ------------- |
| **Render**           | 750 hours/month    | $7/month      |
| **Railway**          | $5 credit/month    | Pay per usage |
| **Fly.io**           | 3 VMs, 3GB storage | $1.94/month   |
| **Google Cloud Run** | 2M requests/month  | Pay per usage |
| **Heroku**           | ❌ No free tier    | $7/month      |

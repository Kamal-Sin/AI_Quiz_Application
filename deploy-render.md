# 🚀 Quick Render Deployment Guide

## Step-by-Step Render Deployment

### 1. Prepare Your Repository

Make sure your code is pushed to GitHub:

```bash
git add .
git commit -m "Ready for Render deployment"
git push origin main
```

### 2. Deploy to Render

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
6. **Add Environment Variables**:
   - **Key**: `GEMINI_API_KEY`
   - **Value**: Your Gemini API key
7. **Click "Create Web Service"**

### 3. Get Your Backend URL

After deployment, Render will give you a URL like:
`https://quiz-app-backend.onrender.com`

### 4. Update Vercel Environment Variables

1. **Go to your Vercel dashboard**
2. **Select your project**
3. **Go to Settings → Environment Variables**
4. **Add**:
   - **Name**: `REACT_APP_API_URL`
   - **Value**: `https://quiz-app-backend.onrender.com`
5. **Redeploy** your Vercel app

### 5. Test Your Deployment

- **Frontend**: Visit your Vercel URL
- **Backend**: Test `https://quiz-app-backend.onrender.com/health`
- **Full App**: Test registration, login, quiz creation, AI features

## 🎯 Render Free Tier Benefits

- ✅ **750 hours/month** (enough for 24/7 operation)
- ✅ **Automatic deployments** from GitHub
- ✅ **HTTPS by default**
- ✅ **Custom domains** support
- ✅ **No credit card required**

## 🔧 Troubleshooting

### If Build Fails:

1. Check the build logs in Render dashboard
2. Ensure `system.properties` has `java.runtime.version=17`
3. Verify `Procfile` has correct jar name

### If App Won't Start:

1. Check the logs in Render dashboard
2. Verify environment variables are set
3. Ensure port is set correctly (Render sets `PORT` automatically)

### If API Calls Fail:

1. Check CORS configuration in your backend
2. Verify the backend URL in Vercel environment variables
3. Test the backend URL directly

## 📞 Support

- **Render Documentation**: [docs.render.com](https://docs.render.com)
- **Render Community**: [community.render.com](https://community.render.com)

#!/bin/bash

echo "🚀 Quiz Application Deployment Script"
echo "======================================"

# Check if git is initialized
if [ ! -d ".git" ]; then
    echo "❌ Git repository not found. Please initialize git first."
    exit 1
fi

# Check if remote origin exists
if ! git remote get-url origin > /dev/null 2>&1; then
    echo "❌ No remote origin found. Please add your GitHub repository."
    exit 1
fi

echo "📦 Building frontend..."
npm run build

if [ $? -eq 0 ]; then
    echo "✅ Frontend build successful"
else
    echo "❌ Frontend build failed"
    exit 1
fi

echo "🔧 Building backend..."
./mvnw.cmd clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Backend build successful"
else
    echo "❌ Backend build failed"
    exit 1
fi

echo "📝 Committing changes..."
git add .
git commit -m "Deploy: Ready for production deployment"

echo "🚀 Pushing to GitHub..."
git push origin main

echo ""
echo "🎉 Deployment initiated!"
echo ""
echo "📋 Next Steps:"
echo "1. Frontend (Vercel):"
echo "   - Go to https://vercel.com"
echo "   - Import your GitHub repository"
echo "   - Deploy automatically"
echo ""
echo "2. Backend (Heroku):"
echo "   - Install Heroku CLI"
echo "   - Run: heroku login"
echo "   - Run: heroku create your-quiz-backend"
echo "   - Run: heroku buildpacks:set heroku/java"
echo "   - Run: heroku addons:create heroku-postgresql:mini"
echo "   - Run: git push heroku main"
echo ""
echo "3. Update Environment Variables:"
echo "   - Set REACT_APP_API_URL in Vercel"
echo "   - Set GEMINI_API_KEY in Heroku"
echo ""
echo "📖 See DEPLOYMENT-GUIDE.md for detailed instructions"

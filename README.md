# AgriOS - Smart Agriculture Backend

AgriOS Backend is a high-performance Spring Boot 4 REST API powering the AgriOS platform. It features role-based access control (Farmer, Officer, Admin), weather metrics tracking, crop cycle advisory engines, sensor telemetry processing, and JWT-authenticated endpoints.

---

## 🚀 Live Deployment on Render (Web Service)

This repository includes a multi-stage `Dockerfile` and is pre-configured for deployment as a **Web Service** on [Render.com](https://render.com).

### Step-by-Step Render Setup:

#### 1. Provision a Database
You can use either:
- **Render PostgreSQL (Easiest)**: On Render Dashboard, click **New +** -> **PostgreSQL**. Give it a name (e.g., `agrios-db`). Once created, copy the **Internal Database URL** (or External Database URL).
- **External MySQL (Aiven / TiDB / Clever Cloud / Railway)**: Create a free MySQL database and copy your connection string, username, and password.

#### 2. Create the Web Service
1. In Render Dashboard, click **New +** and select **Web Service**.
2. Connect your GitHub repository (`agrios-backend`).
3. Choose **Docker** as the runtime (Render will automatically detect the `Dockerfile`).
4. Set the configuration:
   - **Name**: `agrios-backend`
   - **Region**: Choose the same region as your database
   - **Branch**: `main`
   - **Instance Type**: Free
5. Scroll down to **Environment Variables** and add:
   - For PostgreSQL:
     - `DATABASE_URL`: `jdbc:postgresql://<host>:<port>/<dbname>`
     - `DATABASE_USERNAME`: `<username>`
     - `DATABASE_PASSWORD`: `<password>`
   - For MySQL:
     - `DATABASE_URL`: `jdbc:mysql://<host>:<port>/<dbname>?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
     - `DATABASE_USERNAME`: `<username>`
     - `DATABASE_PASSWORD`: `<password>`
   - (Note: `PORT` is automatically injected by Render, and the application binds to `${PORT:8080}`)
6. Click **Deploy Web Service**.

Once deployed, copy your backend URL (e.g., `https://agrios-backend.onrender.com`). You will set this as `VITE_API_URL` in the frontend Render deployment.

---

## 💻 Local Development

1. **Prerequisites:**
   - Java 21 JDK
   - Local MySQL running on port 3306 with database `agrios`

2. **Run locally:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

3. The server starts at `http://localhost:8080`.

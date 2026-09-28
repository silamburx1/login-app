// Change this to your deployed backend URL, e.g. https://your-backend.onrender.com
const API_BASE_URL = "https://login-app-backend-nwzk.onrender.com";

const form = document.getElementById("loginForm");
const message = document.getElementById("message");

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value;

  message.textContent = "";
  message.className = "message";

  try {
    const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const data = await response.json();

    if (response.ok) {
      message.textContent = data.message || "Login successful!";
      message.classList.add("success");
      // Example: store a token/flag and redirect
      // localStorage.setItem("loggedIn", "true");
      // window.location.href = "dashboard.html";
    } else {
      message.textContent = data.message || "Invalid username or password.";
      message.classList.add("error");
    }
  } catch (err) {
    message.textContent = "Could not reach the server. Please try again.";
    message.classList.add("error");
  }
});

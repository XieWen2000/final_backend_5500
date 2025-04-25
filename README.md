# Food Delivery System

A full-stack food delivery system designed to provide a seamless experience for customers, dashers (delivery personnel), and administrators. The system handles real-time order placement, dasher assignments, delivery tracking, and efficient data storage using modern web technologies and cloud infrastructure.

## How to run this web?
1. make sure you have our code *5500-final-frontend* and *final_backend_5500*;

2. modified the environment variable in "resources"-> "application.properties",
   `MONGODB_URI=<mongodb-connection-string>`
3. run  `npm run dev`;

4. If there is no browser open automatically, runs the app in the development mode.
   Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
   (Please refer 5500-final-frontend for more React information).

## 🛠️ Tech Stack

- **Backend**: Java + Spring Boot
- **Frontend**: React / Redux / RTK Query / HTML / CSS
- **Database**: MongoDB

---

### Functionality & Requirements Fulfillment

- 🔒 **Authentication**: SignUp, SignIn, SignOut for all user types
- 📦 **Customer Portal**: Browse menus, add items to cart, place and track orders
- 🍜 **Restaurant** : CRUD operations for menu items, view and accept orders
- 🚗 **Dasher**: Check orders, accepted orders, update order status

---

## 🧠 Architecture & Design

- 🧩 **Modular Design**: Separation of concerns between services (frontend, backend, data)
- 📐 **Object-Oriented Programming**: Core entities like `User`, `Dasher`, `Order` follow SOLID principles
- 💾 **MongoDB**: Scalable storage
- 🔧 **Microservice-Friendly**: Easily extendable to a microservice architecture

---
## 🎨 UI/UX Design

- Intuitive navigation with responsive design
- Clean layout with real-time visual feedback
- Mobile-first approach for dashers and customers on the go
- Clear prompts and order progress tracking

---
## 🧹 Code Quality & Structure

- 📁 Clear package separation (`controller`, `service`, `repository`, `model`)
- 📝 Necessary Javadoc and inline comments for methods

---
## 🤝 Team Collaboration

- 👥 Contributed to code, documentation, UI and presentation
- 🧠 Agile-style development with regular standups and Git collaboration
- 📂 Branching strategy used for feature development and code reviews

---


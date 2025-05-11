# 🎮 Arkanoid-Style Java Game

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Features](#features)
- [Running the Project](#running-the-project)
- [Dependencies](#dependencies)

---

## Overview

This project implements an **Arkanoid-style game** in Java, developed using full Object-Oriented Programming principles.  
The game includes a paddle, bouncing balls, multiple rows of destructible blocks, a scoring system, and frame-based animation.  
Rendering and input handling are done using the external `biuoop` library (from Bar-Ilan University’s OOP course).

---

## Project Structure

- `game.Game` – Main game logic and loop controller
- `geometry.*` – Points, lines, rectangles, velocities
- `sprites.*` – Paddle, blocks, balls, sprite collections
- `listeners.*` – Event listeners (block removal, scoring, etc.)
- `collision.*` – Collision detection utilities
- `biuoop.*` – External library for graphics and keyboard input (JAR)

---

## Features
- 🎮 User-controlled paddle (keyboard-based)
- 🟡 Three balls with different angles
- 🟥 57 blocks arranged in 6 colorful rows
- 🧱 Walls and a death zone (bottom)
- 💥 Collision system with region-based paddle logic
- 🧠 Score tracking with real-time display
- 🎯 Win/lose conditions + score bonus for full block clearance
- ✅ Modular and testable design

---

## Running the Project

### Prerequisites

- Java 8 or higher
- `biuoop-1.4.jar` placed inside a folder (e.g., `lib/`)
- Optional: `checkstyle-8.44-all.jar` for code style checks
- Ant installed (or compile manually)

---

### 💡 WSL Tip

> ✅ **If you are using VS Code on Windows**, it's highly recommended to run the project using **WSL (Windows Subsystem for Linux)**.  
> This avoids classpath issues and provides a consistent environment for compiling and running Java.

---

### Option 1: Using Ant

```bash
ant compile
ant run
```

### Option 2: Manually (without Ant)
```bash
javac -cp biuoop-1.4.jar -d bin src/**/*.java
java -cp biuoop-1.4.jar:bin game.Ass5Game
```

> For Windows, replace : with ; in the classpath.

---

## Dependencies
The following libraries are required but not included in the repository due to licensing:

- biuoop-1.4.jar – GUI and keyboard input support
- checkstyle-8.44-all.jar – Code style checker (optional)
> Please contact your instructor or course staff to obtain these files and place them in the project root.

---

## Notes
- The game ends when all blocks are destroyed or all balls are lost.
- When all blocks are cleared, a bonus score of 100 is awarded.
- This project was developed for academic purposes and demonstrates modular OOP design in Java

### running example
![Brick-Breaker](https://github.com/user-attachments/assets/375e2caf-4804-4e8e-9fd4-c2d7263fd26b)

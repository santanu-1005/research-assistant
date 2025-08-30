# Research Assistant Chrome Extension

An AI-powered Chrome extension designed to assist users in summarizing content and managing research notes. It integrates with a Spring Boot backend that uses the Gemini API to generate summaries based on selected text and allows users to save notes locally.

![Extension Screenshot](extension-screenshot.png) <!-- Replace with your screenshot -->

---

## Features

- **Summarize Text:** Select any text on the web and summarize it with a single click.
- **Save Notes:** Easily store your research notes in the extension.
- **Side Panel Integration:** The extension works as a side panel within the browser for easy access.

---

## Prerequisites

Before running this extension, make sure you have the following:

- **Google Chrome** (latest version preferred).
- **Spring Boot Backend** configured with the **Gemini API** for text summarization. 

---

## Backend Setup (Spring Boot with Gemini API)

### 1. Clone the Backend Repository

If you haven't already, clone the backend repository for the Spring Boot application:

```bash
git clone https://github.com/your-username/research-assistant.git
cd research-assistant/assistant
```

### 2. Setup Spring Boot Application
Update the application.properties (or application.yml) in your Spring Boot project with the required settings for connecting to the Gemini API.
```bash
gemini.api.url=http://gemini-api-url.com
gemini.api.key=your-gemini-api-key
```

### 3. Build the Spring Boot Application
To build the Spring Boot application, use the following command:
If maven is installed in your machine
```bash
mvn clean install
```
or
```bash
.\mvnw.cmd clean package
```

### 4. Start the Spring Boot Application
To run the Spring Boot application:
```bash
mvn spring-boot:run
```
or 
```bash
.\mvnw.cmd spring-boot:run
```

### 5. API Endpoints
The Spring Boot application will expose an endpoint for summarization:
```bash
POST http://localhost:8080/api/research/process
```

## Usage

### 1. Access the Side Panel
Once installed, you can access the Research Assistant extension via the Chrome Side Panel:

- Click the extension icon on the toolbar.
- Alternatively, right-click on a webpage and select Research Assistant from the context menu to open the side panel.

### 2. Summerize Text
- Select text on any webpage.
- Open the Research Assistant side panel.
- Click Summarize. The selected text will be summarized by the Gemini API through your Spring Boot backend.

### 3. Save Notes
- Use the Research Notes section to write down your notes.
- Click Save Notes to store them locally in Chrome's storage.

## Further Extension-Related Queries

If you have any issues or need more information about managing or troubleshooting Chrome extensions, you can refer to Chrome's official documentation at:
- Chrome Extensions Documentation 
- chrome://extensions/ page
 for managing installed extensions, enabling developer mode, and troubleshooting.

Additionally, if you're experiencing issues with permissions or service workers, ensure the extension manifest and API calls are configured correctly.

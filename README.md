# i2i Systems Academy - Apache Kafka Assignment

This repository contains the Apache Kafka assignment for the i2i Systems Summer Internship. It demonstrates local and cloud deployments of a Java-based Kafka Producer and Consumer.

## 📌 Project Overview
The project strictly follows the assignment syllabus:
1. Running an Apache Kafka container locally using Docker Compose.
2. Creating simple Java Producer and Consumer applications.
3. Publishing a **Custom Java Object** (JSON format) from the Producer to a specific Kafka topic.
4. Reading the custom object from the Consumer and printing it to the console.
5. Replicating the exact setup on a **Google Cloud Virtual Machine (VM)**.

## 🛠️ Prerequisites
* Java (JDK 21)
* Apache Maven
* Docker & Docker Compose
* Google Cloud VM 

---

## 🚀 1. Local Environment Setup & Deployment

### Step 1: Start Apache Kafka
Open your terminal and start the Kafka container using Docker:
```bash
docker-compose up -d
```

### Step 2: Run the Consumer (Listener)
Open a terminal in the project folder and start the Consumer application:
```bash
mvn exec:java -Dexec.mainClass="com.i2i.BasicConsumer"
```
*The Consumer will start and wait for incoming messages.*

### Step 3: Run the Producer (Sender)
Open a **second terminal** and run the Producer application to send the custom object:
```bash
mvn exec:java -Dexec.mainClass="com.i2i.BasicProducer"
```

**Result:** The Producer successfully sends the object, and the Consumer terminal instantly prints the custom object data:
`Yakalanan Obje -> Kullanıcı: user123 | Mesaj: Bu bir custom object mesajidir!`

---

## ☁️ 2. Cloud Deployment (Google Cloud VM)

After successful local testing, the entire architecture was deployed to a Google Cloud VM.

### Deployment Steps:
1. Connected to the Google Cloud VM via **SSH**.
2. Cloned the GitHub repository and navigated to the project directory:
   ```bash
   cd staj-projesi/i2i-Academy-ApacheKafka-6/kafka-assignment
   ```
3. Installed Maven and compiled the project on the cloud:
   ```bash
   mvn clean compile
   ```
4. Opened **two separate SSH terminals** to run the applications simultaneously:
   * **Terminal 1:** Started the Consumer.
   * **Terminal 2:** Started the Producer and sent the custom object.
5. **Result:** The cloud deployment was  successful. The Producer sent the data, and the Consumer caught the custom object on the cloud infrastructure.

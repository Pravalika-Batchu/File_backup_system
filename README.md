
# File Backup System (Java Swing)

## Description
This is a simple file backup system built using Java Swing. The application allows you to select a file from your system, specify a backup file name, and save the backup in a predefined folder. It features an easy-to-use interface with a progress bar that shows the status of the backup process in real-time.

---

## Features
- Select any file from your computer.
- Enter a custom backup file name.
- The backup file is automatically saved to a specific folder on your system.
- Progress bar displays the copy status as the file is backed up.
- Exit button to close the application at any point.

---

## How to Run the Application

### Prerequisites
- Java JDK 8 or later installed on your machine.

### Steps to Run
1. Save the provided code into a file named `Main.java`.
2. Open your terminal or command prompt.
3. Navigate to the directory where `Main.java` is saved.
4. Compile the program by running:
   ```
   javac Main.java
   ```
5. Run the compiled program:
   ```
   java Main
   ```

---

## How the Backup Process Works
- After you select a file, you will be asked to enter a backup file name.
- The backup file will be saved automatically in a fixed folder (the path is already specified in the code).
- You only need to enter the **file name**, not the full path. For example:  
  ```
  mybackup.txt
  ```

---

## Backup File Location
All backups are stored in a single location that you can set in the source code. Look for the following line in your code:  
```java
File backup_file = new File("address_to_ur_backup_file_location" + inputText);
```

Replace `"address_to_ur_backup_file_location"` with the path to the folder where you want all backup files to be stored.  
For example:  
```java
File backup_file = new File("C:/Users/YourUsername/Desktop/BackupFolder/" + inputText);
```

---

## Example Usage
1. Launch the application.
2. Click on the "Choose a file" button and select the file you want to back up.
3. Click on "Confirm/Proceed."
4. Enter the backup file name when prompted (without specifying any folder path).
5. The backup file will be saved to the folder specified in your code.
6. The progress bar will update as the backup completes.
7. Once the process is complete, you'll see a message confirming the backup.

---

## Suggestions for Future Improvements
- Allow the user to select the backup folder at runtime.
- Create the backup folder automatically if it doesn't already exist.
- Add file type validation or filters.
- Append timestamps to backup file names to avoid accidental overwrites.

---

## Author
Pravalika Batchu

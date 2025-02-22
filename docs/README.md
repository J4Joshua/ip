# **Ekko User Guide**  
_A smart task management chatbot with a GUI interface_

## **Product Screenshot**
![Ekko](Ui.png)

## **Introduction**  
Ekko is an intelligent chatbot that helps users manage their tasks efficiently. Users can add, delete, find, and mark tasks using simple commands. Ekko provides an intuitive **Graphical User Interface (GUI)** to interact with users effectively.

---

## **Adding Deadlines**  
You can add a **Deadline task** by specifying the task description and the deadline date/time.

### **Usage:**  
```sh
deadline <task description> /by <date-time>
```

### **Example:**  
```sh
deadline Submit report /by 06/10/2025 1800
```

### **Expected Output:**
```
Got it! I've added this task:
  Submit report (by: Oct 06 2025, 6:00 PM)
Now you have 1 task in the list.
```

---

## **Adding Events**  
An **Event task** requires a description, start time, and end time.

### **Usage:**  
```sh
event <task description> /from <start date-time> /to <end date-time>
```

### **Example:**  
```sh
event Team meeting /from 12/10/2025 1400 /to 12/10/2025 1600
```

### **Expected Output:**
```
Got it! I've added this task:
  Team meeting (from: Oct 12 2025, 2:00 PM to: Oct 12 2025, 4:00 PM)
Now you have 2 tasks in the list.
```

---

## **Adding To-Do Tasks**  
A **To-Do task** requires only a description.

### **Usage:**  
```sh
todo <task description>
```

### **Example:**  
```sh
todo Buy groceries
```

### **Expected Output:**
```
Got it! I've added this task:
  Buy groceries
Now you have 3 tasks in the list.
```

---

## **Listing All Tasks**  
Ekko can display all current tasks.

### **Usage:**  
```sh
list
```

### **Expected Output:**
```
Here are your tasks:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: Oct 06 2025, 6:00 PM)
3. [E][ ] Team meeting (from: Oct 12 2025, 2:00 PM to: Oct 12 2025, 4:00 PM)
```

---

## **Marking a Task as Done**  
You can mark a task as completed by specifying its **task number**.

### **Usage:**  
```sh
mark <task number>
```

### **Example:**  
```sh
mark 1
```

### **Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

---

## **Unmarking a Task**  
If a task was marked by mistake, you can undo it.

### **Usage:**  
```sh
unmark <task number>
```

### **Example:**  
```sh
unmark 1
```

### **Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
```

---

## **Finding Tasks**  
You can search for tasks containing a specific **keyword**.

### **Usage:**  
```sh
find <keyword>
```

### **Example:**  
```sh
find report
```

### **Expected Output:**
```
Here are the matching tasks:
1. [D][ ] Submit report (by: Oct 06 2025, 6:00 PM)
```

---

## **Deleting a Task**  
To remove a task from the list, specify its **task number**.

### **Usage:**  
```sh
delete <task number>
```

### **Example:**  
```sh
delete 2
```

### **Expected Output:**
```
Noted! I've removed this task:
  [D][ ] Submit report (by: Oct 06 2025, 6:00 PM)
Now you have 2 tasks in the list.
```

---

## **Exiting Ekko**  
To exit the application, use the `bye` command.

### **Usage:**  
```sh
bye
```

### **Expected Output:**  
```
Bye. Hope to see you again soon!
```

*(The program will close automatically.)*

---

## **Additional Features**
### **Feature Sort - Sorting Tasks**
Ekko allows you to sort tasks based on deadlines or descriptions.

### **Usage:**  
```sh
sort
```

### **Expected Output:**
```
Tasks sorted successfully.
```

*(Tasks will now be displayed in order.)*

---

### **Notes:**
- Dates must be formatted as `d/M/yyyy HHmm`, e.g., `06/10/2025 1800`.
- Task numbers are **1-based** (i.e., first task is numbered **1**).
- Commands are **case-sensitive**.
- Ensure tasks are **saved** before exiting.

---

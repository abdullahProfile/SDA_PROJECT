
    <h1>Group Management - FYP Management System</h1>

<div class="container">

  <h2>Overview</h2>
    <p>The **Group Management** module is part of the **FYP Management System** that handles functionalities related to managing groups for final year projects (FYP). It includes creating groups, assigning advisors, adding students to groups, and managing group memberships. The key files in this functionality are:</p>
    <ul>
        <li><strong>Group.java</strong>: Manages the creation of groups and their associated data, such as group ID and members.</li>
        <li><strong>Advisor.java</strong>: Represents an advisor who is assigned to a group and manages group-related tasks.</li>
        <li><strong>Main.java</strong>: The entry point that runs the application and interacts with the user.</li>
        <li><strong>GroupService.java</strong>: Contains the business logic for group management, such as adding students, assigning advisors, and managing groups.</li>
    </ul>

  <h2>How the Group Management Module Works</h2>
    <p>The Group Management module works as follows:</p>
    <ol>
        <li>The program initializes and loads the existing groups and advisors.</li>
        <li>Users (students) can join groups by providing the group ID. The system verifies whether they are eligible to join.</li>
        <li>Each group is associated with an advisor who can approve or reject student requests to join the group.</li>
        <li>The system allows administrators to view group details and manage students within groups.</li>
    </ol>

  <h2>How to Run the Group Management Functionality</h2>
    <h3>Step 1: Compile the Java Files</h3>
    <p>To start, open your terminal or command prompt, navigate to the folder containing the Java files, and compile them:</p>
    <pre><code>javac *.java</code></pre>

  <h3>Step 2: Run the Application</h3>
    <p>Once the files are compiled, run the <code>Main.java</code> class to start the Group Management System:</p>
    <pre><code>java Main</code></pre>

  <h3>Step 3: Interact with the System</h3>
    <ul>
        <li>Upon running, the system will ask whether the user is a new student or an existing student.</li>
        <li>Students can then provide a group ID to join an existing group.</li>
        <li>Group management and advisor assignments are handled via the <code>GroupService</code> class.</li>
    </ul>

  <h2>File Descriptions</h2>

  <h3><strong>Group.java</strong></h3>
    <p>This class represents a group in the system. It handles tasks such as:</p>
    <ul>
        <li>Creating and initializing a new group with a unique ID.</li>
        <li>Adding students to a group.</li>
        <li>Managing group-related data such as the group name and members.</li>
    </ul>

  <h3><strong>Advisor.java</strong></h3>
    <p>This class represents an advisor assigned to a group. Advisors have the authority to:</p>
    <ul>
        <li>Approve or reject students who want to join a group.</li>
        <li>View the students in their assigned group.</li>
        <li>Manage the progress and activities related to their group.</li>
    </ul>

  <h3><strong>Main.java</strong></h3>
    <p>This is the entry point for the Group Management system. It serves the following purposes:</p>
    <ul>
        <li>Asks the user if they are a new student or an existing one.</li>
        <li>Allows students to register, log in, and join a group.</li>
        <li>Serves as the interface between the user and the Group Management system.</li>
    </ul>

  <h3><strong>GroupService.java</strong></h3>
    <p>This class contains the business logic for managing groups. It handles operations such as:</p>
    <ul>
        <li>Creating and initializing groups.</li>
        <li>Assigning advisors to groups.</li>
        <li>Adding and removing students from groups.</li>
        <li>Handling group-related operations and interactions.</li>
    </ul>

  <h2>File Structure</h2>
    <p>The **Group Management** folder contains the following files:</p>
    <ul>
        <li><code>Group.java</code>: Defines the structure and behavior of groups.</li>
        <li><code>Advisor.java</code>: Defines the structure and behavior of advisors.</li>
        <li><code>Main.java</code>: The entry point for running the system.</li>
        <li><code>GroupService.java</code>: Contains the main business logic for group and advisor management.</li>
    </ul>

  <h2>Troubleshooting</h2>
    <p>If you encounter issues while running the system, here are some troubleshooting tips:</p>
    <ul>
        <li>Ensure that all Java files are compiled correctly before running the program.</li>
        <li>If the program isn't working as expected, verify that the input (such as group IDs) is valid.</li>
        <li>Check for any missing dependencies or errors in the logic within each Java class.</li>
    </ul>

  <h2>Contributing</h2>
    <p>If you'd like to contribute to the Group Management module, feel free to fork the repository, make changes, and submit a pull request. Contributions are welcome!</p>

  <h2>License</h2>
    <p>This project is open-source and available under the <a href="https://opensource.org/licenses/MIT" target="_blank">MIT License</a>.</p>

  <h2>Author</h2>
    <p><strong>Your Name</strong></p>
    <p>GitHub: <a href="https://github.com/abdullahProfile?tab=repositories" target="_blank">GitHub Account Also Explore my other projects</a></p>
    <p>Email: <a href="mailto:abdullahdev88@gmail.com</a></p>

</div>

<footer>
    <p>&copy; 2025 FYP Management System. All rights reserved.</p>
</footer>

</body>

# Donezo User Guide
Donezo is your all-in-one task manager for managing your **TODOs**, _**Deadlines**_ and **Events**!
It is optimised for use via a **Command Line Interface (CLI)**, while having the **_benefits_** of a 
**Graphical User Interface**.

// Product screenshot goes here

// Product intro goes here

## Quick Look - Features
> [!Note]
> - Words in `UPPER_CASE` will be what you supply as the user
> - Items in square brackets are optional

### List Command : `list`
Shows a List of all the Tasks/Notes stored by Donezo
**Default behaviour of list without specifying the type will show a list of tasks**
Format: `list [/type TYPE]`
> [!Tip]
> You can choose to list either notes or tasks in the `TYPE` parameter

Examples:
- `list` : Shows a List of Tasks
- `list /t tasks` : Shows a List of Tasks
- `list /type notes` : Shows a List of Notes

### Deadline Command : `deadline`
Creates a new Deadline Task
Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DUEDATE`

> [!IMPORTANT]
> The argument `DEADLINE_DUEDATE` should be in the following format: `DD/MM/YYYY HHmm`
> `HHmm` is in 24 Hours Format:
> E.g 4pm is 1600; 5:30pm is 1730; 7:45am is 0745

Examples:
- `deadline CS2103 IP Due /by 21/02/2025 1600`

### Event Command : `event`
Creates a new Event Task
Format: `event EVENT_DESCRIPTION /from EVENT_START_DATETIME /to EVENT_END_DATETIME`

> [!IMPORTANT]
> The argument `EVENT_START_DATETIME` should be in the following format: `DD/MM/YYYY HHmm`
> The argument `EVENT_END_DATETIME` should be in the following format: `DD/MM/YYYY HHmm` OR `HHmm`
> For `EVENT_END_DATETIME`, if no date is provided, date used will be the date input in the EVENT_START_DATETIME
> `HHmm` is in 24 Hours Format:
> E.g 4pm is 1600; 5:30pm is 1730; 7:45am is 0745

Examples:
- `event NUS Event 1 /from 24/02/2025 1700 /to 24/02/2025 1900`
- `event Korea Trip /from 24/02/2025 1700 /to 28/02/2025 1900`
- `event Tutoring Session /from 24/02/2025 1700 /to 1900`

### Todo Command : `todo`
Creates a new Todo Task
Format: `todo TODO_DESCRIPTION`

Examples:
- `todo Finish Tutorial 6`
- `todo Study Lecture 5`

### Mark Command : `mark`
Marks the specified task as done
Format: `mark INDEX`
- Mark the task complete at the specified `INDEX`
- The `INDEX` refers to the index number shown in the displayed tasks list
- The `INDEX` **MUST BE A POSITIVE INTEGER** : 1,2,3...

Examples:
- `list` followed by `mark 2` will mark the 2nd Task in the list as complete

### Unmark Command : `unmark`
Marks the specified task as incomplete
Format: `unmark INDEX`
- Mark the task incomplete at the specified `INDEX`
- The `INDEX` refers to the index number shown in the displayed tasks list
- The `INDEX` **MUST BE A POSITIVE INTEGER** : 1,2,3...

Examples:
- `list` followed by `mark 3` will mark the 3rd Task in the list as incomplete

### Delete Command : `delete`

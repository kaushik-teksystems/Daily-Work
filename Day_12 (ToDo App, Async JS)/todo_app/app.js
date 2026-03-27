let todos = JSON.parse(localStorage.getItem('todos')) || [];

renderTodos();

document.getElementById('todo-input').addEventListener('input', (e) => e.target.setCustomValidity(""));
document.getElementById('todo-time-required').addEventListener('input', (e) => e.target.setCustomValidity(""));

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos))
}

function addTodo() {
    const input1 = document.getElementById('todo-input');
    const input2 = document.getElementById('todo-time-required');
    
    const task = input1.value.trim();
    const timeRequired = input2.value.trim();

     if (task === '') {
        input1.setCustomValidity("Please enter a valid task name.");
        input1.reportValidity();
        return;
    }

    if (timeRequired === '' || parseFloat(timeRequired) < 0) {
        input2.setCustomValidity(timeRequired === '' ? "Please enter time." : "Time can't be negative.");
        input2.reportValidity();
        return;
    }

    const urgency = document.querySelector('input[name="urgency"]:checked').value;

    todos.push({ text: task, timeRequired, urgency, completed: false });

    input1.value = '';
    input2.value = '';
    saveTodos();
    renderTodos();
}

function deleteTodo(index) {
    todos.splice(index, 1);
    saveTodos();
    renderTodos();
}

function toggleComplete(index) {
    todos[index].completed = !todos[index].completed;
    saveTodos();
    renderTodos();
}

function renderTodos() {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';

    todos.forEach((todo, index) => {
        const li = document.createElement('li');

        if (todo.completed) li.classList.add('completed');

        const urgencyTag = todo.urgency === 'Urgent'  ? `<span class="badge badge-urgent">Urgent</span>` : '';

        li.innerHTML = `
          <div class="task-content">
            <div style="display: flex; align-items: center; gap: 8px;">
                <span style="font-weight:600;">${todo.text}</span>
                ${urgencyTag}
            </div>
            <span style="font-size:12px; color:#666;">${todo.timeRequired} hours</span>
        </div>
        <div class="actions">
            <button class="tick-btn" onclick="toggleComplete(${index})">✔</button>
            <button class="delete-btn" onclick="deleteTodo(${index})">✕</button>
        </div>
        `;

        list.appendChild(li);
    });
}
renderTodos();

function sort(){
    todos.sort((a, b) => a.text.toLowerCase().localeCompare(b.text.toLowerCase()));
    saveTodos(); 
    renderTodos();
}
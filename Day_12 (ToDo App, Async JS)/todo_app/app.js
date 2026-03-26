let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos))
}

function addTodo() {
    const input1 = document.getElementById('todo-input');
    const task = input1.value.trim();

    const input2 = document.getElementById('todo-time-required');
    const timeRequired = input2.value.trim();

    if (task == '') return;
    if (timeRequired == '') return;

    todos.push({ text: task, timeRequired, completed: false });

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

        li.innerHTML = `
        <div>
            <span style="display:block; font-weight:600;">${todo.text}</span>
            <span style="font-size:12px; color:#666;">${todo.timeRequired} hours</span>
            
        </div>
        <div>
            <button class="tick-btn" onclick="toggleComplete(${index})">✔</button>
            <button class="delete-btn" onclick="deleteTodo(${index})">✕</button>
        </div>
        `;

        list.appendChild(li);
    });
}

renderTodos();
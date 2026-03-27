let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos))
}

function addTodo() {
    const input1 = document.getElementById('todo-input');
    const task = input1.value.trim();

    const input2 = document.getElementById('todo-time-required');
    const timeRequired = input2.value.trim();

    const urgency = document.querySelector('input[name="urgency"]:checked').value;
    
    if (task === '' || timeRequired === '') return;

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
    todos.sort((e1,e2) => {
        const e1_text = e1.text.toLowerCase();
        const e2_text = e2.text.toLowerCase();

        return e1_text.localeCompare(e2_text);
    });
    renderTodos();
}
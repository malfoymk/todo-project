const toDoInput = document.querySelector('.todo-input');
const toDoBtn = document.querySelector('.todo-btn');
const toDoList = document.querySelector('.todo-list');
const lightTheme = document.querySelector('.light-theme');
const darkerTheme = document.querySelector('.darker-theme');

toDoBtn.addEventListener('click', addToDo);
toDoList.addEventListener('click', deletecheck);
document.addEventListener("DOMContentLoaded", getTodos);
lightTheme.addEventListener('click', () => changeTheme('light'));
darkerTheme.addEventListener('click', () => changeTheme('darker'));

let savedTheme = localStorage.getItem('savedTheme');
savedTheme === null ?
    changeTheme('standard')
    : changeTheme(localStorage.getItem('savedTheme'));

async function addToDo(event) {
    event.preventDefault();

    const todoName = toDoInput.value;

    if (todoName === '') {
        alert("Você deve escrever algo!");
        return;
    }

    const todoData = {
        name: todoName,
        description: '',
        status: false
    };

    try {
        const response = await fetch('/api/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todoData)
        });

        if (!response.ok) {
            throw new Error('Erro ao adicionar a tarefa');
        }

        const createdTask = await response.json();

        const toDoDiv = createToDoElement(createdTask);
        toDoList.appendChild(toDoDiv);

        toDoInput.value = '';
    } catch (error) {
        console.error('Erro ao adicionar a tarefa:', error.message);
    }
}

function createToDoElement(task) {
    const toDoDiv = document.createElement("div");
    toDoDiv.classList.add('todo', `${savedTheme}-todo`);

    const newToDo = document.createElement('li');
    newToDo.innerText = task.name;
    newToDo.classList.add('todo-item');
    toDoDiv.appendChild(newToDo);

    const checked = document.createElement('button');
    checked.innerHTML = '<i class="fas fa-check"></i>';
    checked.classList.add('check-btn', `${savedTheme}-button`);
    toDoDiv.appendChild(checked);

    const deleted = document.createElement('button');
    deleted.innerHTML = '<i class="fas fa-trash"></i>';
    deleted.classList.add('delete-btn', `${savedTheme}-button`);
    toDoDiv.appendChild(deleted);

    return toDoDiv;
}

async function getTodos() {
    try {
        const response = await fetch('/api/tasks');
        const tasks = await response.json();

        tasks.forEach(task => {
            const toDoDiv = createToDoElement(task);
            toDoList.appendChild(toDoDiv);
        });
    } catch (error) {
        console.error('Erro ao buscar tarefas:', error.message);
    }
}

async function deletecheck(event) {
    const item = event.target;

    if (item.classList[0] === 'delete-btn') {
        const todoDiv = item.parentElement;
        const taskId = todoDiv.dataset.taskId;

        try {
            const response = await fetch(`/api/tasks/${taskId}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir a tarefa');
            }

            todoDiv.remove();
        } catch (error) {
            console.error('Erro ao excluir a tarefa:', error.message);
        }
    }

    if (item.classList[0] === 'check-btn') {
        const todoDiv = item.parentElement;
        todoDiv.classList.toggle("completed");
    }
}

function changeTheme(color) {
    localStorage.setItem('savedTheme', color);
    savedTheme = localStorage.getItem('savedTheme');

    document.body.className = color;

    color === 'darker' ? 
        document.getElementById('title').classList.add('darker-title')
        : document.getElementById('title').classList.remove('darker-title');

    document.querySelector('input').className = `${color}-input`;

    document.querySelectorAll('.todo').forEach(todo => {
        Array.from(todo.classList).some(item => item === 'completed') ? 
            todo.className = `todo ${color}-todo completed`
            : todo.className = `todo ${color}-todo`;
    });

    document.querySelectorAll('button').forEach(button => {
        Array.from(button.classList).some(item => {
            if (item === 'check-btn') { 
                button.className = `check-btn ${color}-button`;  
            } else if (item === 'delete-btn') {
                button.className = `delete-btn ${color}-button`; 
            } else if (item === 'todo-btn') {
                button.className = `todo-btn ${color}-button`;
            }
        });
    });
}

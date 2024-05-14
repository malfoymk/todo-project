document.addEventListener('DOMContentLoaded', () => {
    const toDoInput = document.querySelector('.todo-input');
    const toDoBtn = document.querySelector('.todo-btn');
    const toDoList = document.querySelector('#myUnOrdList ul');
    const lightTheme = document.querySelector('.light-theme');
    const darkerTheme = document.querySelector('.darker-theme');

    if (toDoBtn) {
        toDoBtn.addEventListener('click', addToDo);
    }

    if (lightTheme) {
        lightTheme.addEventListener('click', () => changeTheme('light'));
    }

    if (darkerTheme) {
        darkerTheme.addEventListener('click', () => changeTheme('darker'));
    }
    document.addEventListener('DOMContentLoaded', function() {
        const toDoBtn = document.querySelector('.todo-btn');
        if (toDoBtn) {
            toDoBtn.addEventListener('click', addToDo);
        }
    });
    
    function addToDo(event) {
        event.preventDefault();
        // Resto da função addToDo
    }
    
    async function addToDo(event) {
        event.preventDefault();

        const todoName = toDoInput.value;

        if (todoName === '') {
            alert("Você deve escrever algo!");
            return;
        }

        const todoData = {
            description: todoName
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
            const newToDo = document.createElement('li');
            newToDo.textContent = createdTask.description;
            toDoList.appendChild(newToDo);
            toDoInput.value = '';
        } catch (error) {
            console.error('Erro ao adicionar a tarefa:', error.message);
        }
    }

    async function getTodos() {
        try {
            const response = await fetch('/api/tasks');
            if (!response.ok) {
                throw new Error('Erro ao buscar tarefas');
            }
            const tasks = await response.json();
            tasks.forEach(task => {
                const newToDo = document.createElement('li');
                newToDo.textContent = task.description;
                toDoList.appendChild(newToDo);
            });
        } catch (error) {
            console.error('Erro ao buscar tarefas:', error.message);
        }
    }

    function changeTheme(color) {
        document.body.className = color;
    }

    // Chamar a função para obter as tarefas quando a página for carregada
    getTodos();
});
async function deleteCheck(event) {
    const item = event.target;

    if (item.classList.contains('delete-btn')) {
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

    if (item.classList.contains('check-btn')) {
        const todoDiv = item.parentElement;
        todoDiv.classList.toggle("completed");
    }
}

function changeTheme(color) {
    localStorage.setItem('savedTheme', color);
    savedTheme = localStorage.getItem('savedTheme');

    document.body.className = color;

    const title = document.getElementById('title');
    if (color === 'darker') {
        title.classList.add('darker-title');
    } else {
        title.classList.remove('darker-title');
    }

    document.querySelector('input').className = `${color}-input`;

    document.querySelectorAll('.todo').forEach(todo => {
        if (todo.classList.contains('completed')) {
            todo.className = `todo ${color}-todo completed`;
        } else {
            todo.className = `todo ${color}-todo`;
        }
    });

    document.querySelectorAll('button').forEach(button => {
        if (button.classList.contains('check-btn')) {
            button.className = `check-btn ${color}-button`;
        } else if (button.classList.contains('delete-btn')) {
            button.className = `delete-btn ${color}-button`;
        } else if (button.classList.contains('todo-btn')) {
            button.className = `todo-btn ${color}-button`;
        }
    });
}
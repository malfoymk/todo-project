package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.Tarefas;
import repository.TarefasRepository;
import java.util.Optional;

@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;

    @Autowired
    public TarefasService(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }
    
    public void excluirTarefa(Long id) {
        Optional<Tarefas> tarefasOptional = tarefasRepository.findById(id);
        if (tarefasOptional.isPresent()) {
            tarefasRepository.delete(tarefasOptional.get());
        } else {
            throw new RuntimeException("Tarefa não encontrada: " + id);
        }
    }
}

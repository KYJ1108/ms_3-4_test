package com.example.ms1.note.notebook;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public Notebook getNotebook(Long notebookId) {
        return notebookRepository.findById(notebookId).orElseThrow();
    }

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public List<Notebook> getTopNotebookList() {
        return notebookRepository.findByParentIsNull();
    }

    public void delete(Long id) {
        notebookRepository.deleteById(id);
    }

    public Notebook updateName(Long id, String name) {
        Notebook notebook = getNotebook(id);
        notebook.setName(name);
        return notebookRepository.save(notebook);
    }

    public void move(Long id, Long destinationId) {
        Notebook target = getNotebook(id);
        Notebook destination = getNotebook(destinationId);

        target.setParent(destination);
        notebookRepository.save(target);
    }

    public List<Notebook> getSearchedNotebookList(String keyword) {
        return notebookRepository.findByNameContaining(keyword);
    }
}
package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note saveDefault() {
        Note note = new Note();
        note.setTitle("new title..");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public List<Note> getNoteListByNotebook(Notebook targetNotebook) {
        return noteRepository.findByNotebook(targetNotebook);
    }

    public List<Note> getSearchedNoteList(String keyword) {
        return noteRepository.findByTitleContaining(keyword);
    }

    public List<Note> getSortedListByCreateDate(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByCreateDateDesc(targetNotebook);
    }

    public List<Note> getSortedListByTitle(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByTitle(targetNotebook);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
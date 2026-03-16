package com.example.app.controller;

import com.example.app.entity.Note;
import com.example.app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.saveNote(note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Integer id, @RequestBody Note note) {
        return noteService.getNoteById(id)
                .map(existing -> {
                    note.setIdNote(id);
                    return ResponseEntity.ok(noteService.updateNote(note));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        if (noteService.getNoteById(id).isPresent()) {
            noteService.deleteNoteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

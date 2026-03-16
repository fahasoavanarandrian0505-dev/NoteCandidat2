package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.app.entity.Note;
import com.example.app.service.NoteService;
import com.example.app.service.CandidatService;
import com.example.app.service.MatiereService;
import com.example.app.service.CorrecteurService;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteWebController {

    private final NoteService noteService;
    private final CandidatService candidatService;
    private final MatiereService matiereService;
    private final CorrecteurService correcteurService;

    public NoteWebController(NoteService noteService, 
                            CandidatService candidatService,
                            MatiereService matiereService,
                            CorrecteurService correcteurService) {
        this.noteService = noteService;
        this.candidatService = candidatService;
        this.matiereService = matiereService;
        this.correcteurService = correcteurService;
    }

    @GetMapping("")
    public String listNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        model.addAttribute("candidats", candidatService.getAllCandidats());
        model.addAttribute("matieres", matiereService.getAllMatieres());
        model.addAttribute("correcteurs", correcteurService.getAllCorrecteurs());
        return "notes";
    }

    @PostMapping("/creer")
    public String createNote(@RequestParam Integer idCandidat,
                            @RequestParam Integer idMatiere,
                            @RequestParam Integer idCorrecteur,
                            @RequestParam Double note) {
        Note newNote = new Note();
        newNote.setCandidat(candidatService.getCandidatById(idCandidat).orElse(null));
        newNote.setMatiere(matiereService.getMatiereById(idMatiere).orElse(null));
        newNote.setCorrecteur(correcteurService.getCorrecteurById(idCorrecteur).orElse(null));
        newNote.setNote(note);
        noteService.saveNote(newNote);
        return "redirect:/notes";
    }

    @PostMapping("/supprimer/{id}")
    public String deleteNote(@PathVariable Integer id) {
        noteService.deleteNoteById(id);
        return "redirect:/notes";
    }
}

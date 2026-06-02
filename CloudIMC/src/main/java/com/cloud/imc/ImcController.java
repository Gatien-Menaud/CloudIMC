package com.cloud.imc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImcController {

    @Autowired
    private ImcRecordRepository imcRecordRepository;

    // 1. PAGE D'ACCUEIL
    @GetMapping("/")
    public String showHome() {
        return "index";
    }

    // 2. PAGE DE RÉSULTAT
    @PostMapping("/calculate")
    public String calculateAndSave(
            @RequestParam("name") String name,
            @RequestParam("weight") double weight,
            @RequestParam("height") double height,
            Model model) {

        // Calcul de l'IMC
        double mHeight = height / 100;
        double imc = weight / (mHeight * mHeight);
        imc = Math.round(imc * 100.0) / 100.0;

        // Détermination de la catégorie
        String category = "Normal";
        if (imc < 18.5) {
            category = "Underweight";
        } else if (imc >= 25.0) {
            category = "Overweight";
        }

        // Sauvegarde dans le conteneur MySQL Docker
        ImcRecord record = new ImcRecord(name, weight, height, imc, category);
        imcRecordRepository.save(record);

        // Envoi des données spécifiques à la page result.html
        model.addAttribute("currentName", name);
        model.addAttribute("currentWeight", weight);
        model.addAttribute("currentHeight", height);
        model.addAttribute("currentImc", imc);
        model.addAttribute("currentCategory", category);

        return "result";
    }

    // 3. PAGE D'HISTORIQUE
    @GetMapping("/history")
    public String showHistory(Model model) {
        // Récupère tous les enregistrements depuis MySQL
        List<ImcRecord> allRecords = imcRecordRepository.findAll();
        
        // Envoie la liste au fichier history.html sous le nom "records"
        model.addAttribute("records", allRecords);
        
        return "history";
    }
}
package com.projeto.Nunes.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.Nunes.model.Nunes;
import com.projeto.Nunes.repository.NunesRepo;

@Controller
public class NunesController {

    @Autowired
    private NunesRepo nunesRepo;

    @GetMapping( {"/" ,"/pagina"})
    public String mostrarFormulario(Model model) {
        Nunes nunes = new Nunes();
        model.addAttribute("nunes", nunes); // Passando o objeto para o formulário
        return "index"; // Nome do template Thymeleaf
    }

    @PostMapping("/pagina")
    public String novoUsuario(@ModelAttribute("nunes") Nunes nunes) {
        nunesRepo.save(nunes); // Salvando o objeto Nunes no banco de dados
        return "redirect:/pagina"; 
    }
    
    @GetMapping ("/portiforio")
    public String Port (Model model) {
    	return "portiforio";
    }
    
    
    
    
    
    
    
    
    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf() throws IOException {
        // Localiza o arquivo PDF no diretório estático
        ClassPathResource pdfFile = new ClassPathResource("/static/docs/Portifório.pdf");

        // Lê o arquivo como um array de bytes
        byte[] pdfBytes = Files.readAllBytes(pdfFile.getFile().toPath());

        // Prepara os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=meu-arquivo.pdf");

        // Retorna o arquivo com os cabeçalhos corretos
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

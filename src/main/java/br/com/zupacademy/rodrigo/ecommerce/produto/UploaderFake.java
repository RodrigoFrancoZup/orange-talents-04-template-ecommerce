package br.com.zupacademy.rodrigo.ecommerce.produto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {
    /**
     *
     * @param imagens
     * @return links gerados para imagens
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://bucket.io/"
                        +imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}

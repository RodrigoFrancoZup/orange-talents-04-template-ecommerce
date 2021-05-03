package br.com.zupacademy.rodrigo.ecommerce.categoria;


public class CategoriaResponse {

    private Long id;
    private String nome;
    private CategoriaResponse categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = null;
        if (categoria.getCategoriaMae() != null) {
            this.categoriaMae = new CategoriaResponse(categoria.getCategoriaMae());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaResponse getCategoriaMae() {
        return categoriaMae;
    }
}

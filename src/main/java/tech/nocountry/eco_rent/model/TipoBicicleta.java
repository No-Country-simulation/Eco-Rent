package tech.nocountry.eco_rent.model;

public enum TipoBicicleta {
    URBANA("Bicicleta Urbana"),
    CARRETERA("Bicicleta de carretera"),
    ELECTRICA("Bicicleta eléctrica");

    private final String descripcion;

    TipoBicicleta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

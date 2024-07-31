package tech.nocountry.eco_rent.model;

public enum TipoBicicleta {
  MOUNTAIN("Mountain Bike"),
  ROAD("Bicicleta de Carretera"),
  CITY("Bicicleta Urbana"),
  HYBRID("Bicicleta Híbrida"),
  ELECTRIC("Bicicleta Eléctrica"),
  BMX("BMX"),
  FOLDING("Bicicleta Plegable"),
  CRUISER("Bicicleta Cruiser"),
  TOURING("Bicicleta de Touring"),
  TANDEM("Bicicleta Tándem");

  private final String descripcion;

  TipoBicicleta(String descripcion) {
      this.descripcion = descripcion;
  }

  public String getDescripcion() {
      return descripcion;
  }
}

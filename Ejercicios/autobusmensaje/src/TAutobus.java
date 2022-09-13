public class TAutobus {
    private int superficiePublicitaria;
    private int kilometros;
    private int id;
    private TMensaje tMensaje;

    TMensaje getTMensaje() {
        return this.tMensaje;
    }

    public void setTMensaje(TMensaje value) {
        this.tMensaje = value;
    }

    public int getSuperficiePublicitaria() {
        return this.superficiePublicitaria;
    }

    public void setSuperficiePublicitaria(int value) {
        this.superficiePublicitaria = value;
    }

    public int getKilometros() {
        return this.kilometros;
    }

    public void setKilometros(int value) {
        this.kilometros = value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

}

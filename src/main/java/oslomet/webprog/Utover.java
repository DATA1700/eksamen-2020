package oslomet.webprog;

public class Utover {
    private String fornavn;
    private String etternavn;
    private String klubb;
    private String epost;
    private String passord;

    public Utover(String fornavn, String etternavn, String klubb, String epost, String passord) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.klubb = klubb;
        this.epost = epost;
        this.passord = passord;
    }

    public Utover() {
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getKlubb() {
        return klubb;
    }

    public void setKlubb(String klubb) {
        this.klubb = klubb;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}

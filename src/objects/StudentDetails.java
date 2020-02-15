package objects;

public class StudentDetails {
	
	private int indeks;
	private String id_predmeta;
	private int godina_roka;
	private String oznaka_roka;
	private int godina;
	private String semestar;
	private String datum_prijave;
	private String nacin_prijave;
	private int broj_polaganja;
	private String status_prijave;
	private String datum_pismenog;
	private String bodovi_pismenog;
	private String datum_usmenog;
	private String bodovi_usmenog;
	private int bodovi;
	private int ocena;
	private String nastavnik;
	private String napomena;
	
	public StudentDetails(){	
		indeks = 0;
	}

	public StudentDetails(int indeks, String id_predmeta, int godina_roka, String oznaka_roka, int godina,
			String semestar, String datum_prijave, String nacin_prijave, int broj_polaganja, String status_prijave,
			String datum_pismenog, String bodovi_pismenog, String datum_usmenog, String bodovi_usmenog, int bodovi,
			int ocena, String nastavnik, String napomena) {
		this.indeks = indeks;
		this.id_predmeta = id_predmeta;
		this.godina_roka = godina_roka;
		this.oznaka_roka = oznaka_roka;
		this.godina = godina;
		this.semestar = semestar;
		this.datum_prijave = datum_prijave;
		this.nacin_prijave = nacin_prijave;
		this.broj_polaganja = broj_polaganja;
		this.status_prijave = status_prijave;
		this.datum_pismenog = datum_pismenog;
		this.bodovi_pismenog = bodovi_pismenog;
		this.datum_usmenog = datum_usmenog;
		this.bodovi_usmenog = bodovi_usmenog;
		this.bodovi = bodovi;
		this.ocena = ocena;
		this.nastavnik = nastavnik;
		this.napomena = napomena;
	}
	
	public int getIndeks() {
		return indeks;
	}
	
	public String getStringIndeks(){
		return indeks+"";
	}

	public void setIndeks(int indeks) {
		this.indeks = indeks;
	}

	public String getId_predmeta() {
		return id_predmeta;
	}

	public void setId_predmeta(String id_predmeta) {
		this.id_predmeta = id_predmeta;
	}

	public int getGodina_roka() {
		return godina_roka;
	}
	
	public String getStringGodina_roka() {
		return godina_roka +"";
	}

	public void setGodina_roka(int godina_roka) {
		this.godina_roka = godina_roka;
	}

	public String getOznaka_roka() {
		return oznaka_roka;
	}

	public void setOznaka_roka(String oznaka_roka) {
		this.oznaka_roka = oznaka_roka;
	}

	public int getGodina() {
		return godina;
	}
	
	public String getStringGodina() {
		return godina +"";
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}

	public String getDatum_prijave() {
		return datum_prijave;
	}

	public void setDatum_prijave(String datum_prijave) {
		this.datum_prijave = datum_prijave;
	}

	public String getNacin_prijave() {
		return nacin_prijave;
	}

	public void setNacin_prijave(String nacin_prijave) {
		this.nacin_prijave = nacin_prijave;
	}

	public int getBroj_polaganja() {
		return broj_polaganja;
	}
	
	public String getStringBroj_polaganja() {
		return broj_polaganja +"";
	}

	public void setBroj_polaganja(int broj_polaganja) {
		this.broj_polaganja = broj_polaganja;
	}

	public String getStatus_prijave() {
		return status_prijave;
	}

	public void setStatus_prijave(String status_prijave) {
		this.status_prijave = status_prijave;
	}

	public String getDatum_pismenog() {
		return datum_pismenog;
	}

	public void setDatum_pismenog(String datum_pismenog) {
		this.datum_pismenog = datum_pismenog;
	}

	public String getBodovi_pismenog() {
		return bodovi_pismenog;
	}

	public void setBodovi_pismenog(String bodovi_pismenog) {
		this.bodovi_pismenog = bodovi_pismenog;
	}

	public String getDatum_usmenog() {
		return datum_usmenog;
	}

	public void setDatum_usmenog(String datum_usmenog) {
		this.datum_usmenog = datum_usmenog;
	}

	public String getBodovi_usmenog() {
		return bodovi_usmenog;
	}

	public void setBodovi_usmenog(String bodovi_usmenog) {
		this.bodovi_usmenog = bodovi_usmenog;
	}

	public int getBodovi() {
		return bodovi;
	}

	public String getStringBodovi() {
		return bodovi +"";
	}
	
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public int getOcena() {
		return ocena;
	}

	public String getStringOcena() {
		return ocena +"";
	}
	
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(String nastavnik) {
		this.nastavnik = nastavnik;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	@Override
	public String toString() {
		return "StudentDetails [indeks=" + indeks + ", id_predmeta=" + id_predmeta + ", godina_roka=" + godina_roka
				+ ", oznaka_roka=" + oznaka_roka + ", godina=" + godina + ", semestar=" + semestar + ", datum_prijave="
				+ datum_prijave + ", nacin_prijave=" + nacin_prijave + ", broj_polaganja=" + broj_polaganja
				+ ", status_prijave=" + status_prijave + ", datum_pismenog=" + datum_pismenog + ", bodovi_pismenog="
				+ bodovi_pismenog + ", datum_usmenog=" + datum_usmenog + ", bodovi_usmenog=" + bodovi_usmenog
				+ ", bodovi=" + bodovi + ", ocena=" + ocena + ", nastavnik=" + nastavnik + ", napomena=" + napomena
				+ "]";
	}
}

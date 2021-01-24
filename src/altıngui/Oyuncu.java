package altıngui;


public abstract class Oyuncu
{
	private String oyuncuAdi;
	int kasadakiAltın=200;
        int adımSayısı=0;
        int toplamAdımSayısı;
        int harcananAltınMiktarı=0;
	int toplananAltınMiktarı=0;

    public Oyuncu(String oyuncuAdi, int adımSayısı, int toplamAdımSayısı, int harcananAltınMiktarı, int toplananAltınMiktarı) {
        this.oyuncuAdi = oyuncuAdi;
        this.adımSayısı = adımSayısı;
        this.toplamAdımSayısı = toplamAdımSayısı;
        this.harcananAltınMiktarı = harcananAltınMiktarı;
        this.toplananAltınMiktarı = toplananAltınMiktarı;
    }
        

	
	public Oyuncu() {
		
	}
	
	public String getOyuncuAdi() {
		return oyuncuAdi;
	}
	public void setOyuncuAdi(String oyuncuAdi) {
		this.oyuncuAdi = oyuncuAdi;
	}
	
	
        public int getAdımSayısı() {
		return adımSayısı=3;
	}
	public void setAdımSayısı(int adımSayısı) {
		this.adımSayısı = adımSayısı;
	}

    public int getKasadakiAltın() {
        return kasadakiAltın;
    }

    public void setKasadakiAltın(int kasadakiAltın) {
        this.kasadakiAltın = kasadakiAltın;
    }

}


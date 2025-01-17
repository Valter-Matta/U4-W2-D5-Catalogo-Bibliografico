package org.example;
abstract class ElementoCatalogo {
	private final String isbn;
	private final String titolo;
	private final int annoPubblicazione;
	private final int numeroPagine;

	public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno: " + annoPubblicazione + ", Pagine: " + numeroPagine;
	}
}

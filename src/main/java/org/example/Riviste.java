package org.example;


class Rivista extends ElementoCatalogo {
	private final Periodicity periodicita;

	public Rivista (String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicity periodicita) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString () {
		return super.toString() + ", Periodicità: " + periodicita;
	}
}






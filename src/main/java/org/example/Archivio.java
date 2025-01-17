package org.example;


import java.util.*;
import java.util.stream.Collectors;

class Archivio {
	private final Map<String, ElementoCatalogo> catalogo;

	public Archivio() {
		this.catalogo = new HashMap<>();
	}

	// Aggiunta di un elemento
	public void aggiungiElemento(ElementoCatalogo elemento) {
		if (catalogo.containsKey(elemento.getIsbn())) {
			System.out.println("Errore: elemento con ISBN già esistente.");
		} else {
			catalogo.put(elemento.getIsbn(), elemento);
			System.out.println("Elemento aggiunto correttamente.");
		}
	}

	// Ricerca per ISBN
	public ElementoCatalogo ricercaPerIsbn(String isbn) throws ElementoNonTrovatoException {
		if (!catalogo.containsKey(isbn)) {
			throw new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato.");
		}
		return catalogo.get(isbn);
	}

	// Rimozione di un elemento
	public void rimuoviElemento(String isbn) {
		if (catalogo.remove(isbn) != null) {
			System.out.println("Elemento rimosso correttamente.");
		} else {
			System.out.println("Errore: elemento con ISBN non trovato.");
		}
	}

	// Ricerca per anno di pubblicazione
	public List<ElementoCatalogo> ricercaPerAnno(int anno) {
		return catalogo.values().stream()
			.filter(e -> e.getAnnoPubblicazione() == anno)
			.collect(Collectors.toList());
	}

	// Ricerca per autore
	public List<Libro> ricercaPerAutore(String autore) {
		return catalogo.values().stream()
			.filter(e -> e instanceof Libro && ((Libro) e).getAutore().equalsIgnoreCase(autore))
			.map(e -> (Libro) e)
			.collect(Collectors.toList());
	}

	// Aggiornamento di un elemento
	public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) {
		if (catalogo.containsKey(isbn)) {
			catalogo.put(isbn, nuovoElemento);
			System.out.println("Elemento aggiornato correttamente.");
		} else {
			System.out.println("Errore: elemento con ISBN non trovato.");
		}
	}

	// Statistiche del catalogo
	public void stampaStatistiche() {
		long numeroLibri = catalogo.values().stream().filter(e -> e instanceof Libro).count();
		long numeroRiviste = catalogo.values().stream().filter(e -> e instanceof Rivista).count();
		Optional<ElementoCatalogo> maxPagine = catalogo.values().stream().max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine));
		double mediaPagine = catalogo.values().stream().mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0);

		System.out.println("Statistiche del catalogo:");
		System.out.println("Numero totale di libri: " + numeroLibri);
		System.out.println("Numero totale di riviste: " + numeroRiviste);
		maxPagine.ifPresent(e -> System.out.println("Elemento con il maggior numero di pagine: " + e));
		System.out.println("Media delle pagine di tutti gli elementi: " + mediaPagine);
	}
}
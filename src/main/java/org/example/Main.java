package org.example;

import java.util.List;
import java.util.Scanner;

enum Periodicity {
	SETTIMANALE, MENSILE, SEMESTRALE
}

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		Archivio archivio = new Archivio();


		while (true) {
			System.out.println("\nScegli un'operazione:");
			System.out.println("1. Aggiungi elemento");
			System.out.println("2. Cerca elemento per ISBN");
			System.out.println("3. Rimuovi elemento");
			System.out.println("4. Cerca elementi per anno di pubblicazione");
			System.out.println("5. Cerca libri per autore");
			System.out.println("6. Aggiorna elemento");
			System.out.println("7. Visualizza statistiche del catalogo");
			System.out.println("0. Esci");
			System.out.print("Scelta: ");

			int scelta = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			try {
				switch (scelta) {
					case 1 -> {
						System.out.print("Inserisci ISBN: ");
						String isbn = scanner.nextLine();
						System.out.print("Inserisci titolo: ");
						String titolo = scanner.nextLine();
						System.out.print("Inserisci anno di pubblicazione: ");
						int anno = scanner.nextInt();
						System.out.print("Inserisci numero di pagine: ");
						int pagine = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Tipo elemento (1 = Libro, 2 = Rivista): ");
						int tipo = scanner.nextInt();
						scanner.nextLine();

						if (tipo == 1) {
							System.out.print("Inserisci autore: ");
							String autore = scanner.nextLine();
							System.out.print("Inserisci genere: ");
							String genere = scanner.nextLine();
							archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
						} else if (tipo == 2) {
							System.out.print("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
							Periodicity periodicita = Periodicity.valueOf(scanner.nextLine().toUpperCase());
							archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
						} else {
							System.out.println("Tipo non valido.");
						}

					}
					case 2 -> {
						// Ricerca per ISBN
						System.out.print("Inserisci ISBN da cercare: ");
						String isbnRicerca = scanner.nextLine();
						try {
							ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbnRicerca);
							System.out.println("Elemento trovato: " + elemento.getIsbn() + " titolo: " + elemento.getTitolo());
						} catch (ElementoNonTrovatoException e) {
							System.out.println(e.getMessage());
						}

					}
					case 3 -> {
						// Rimozione per ISBN
						System.out.print("Inserisci ISBN da rimuovere: ");
						String isbnRimozione = scanner.nextLine();
						try {
							archivio.rimuoviElemento(isbnRimozione);
							System.out.println("Elemento rimosso.");
						} catch (Exception e) {
							System.out.println("Elemento non trovato");
						}
					}
					case 4 -> {
						// Ricerca per anno
						System.out.print("Inserisci anno da cercare: ");
						int annoRicerca = scanner.nextInt();
						scanner.nextLine(); // Clear buffer
						List<ElementoCatalogo> elementiAnno = archivio.ricercaPerAnno(annoRicerca);
						if (elementiAnno.isEmpty()) {
							System.out.println("Nessun elemento trovato per l'anno " + annoRicerca);
						} else {
							System.out.println("Elementi trovati:");
							for (ElementoCatalogo e : elementiAnno) {
								System.out.println(e.getIsbn() + " - " + e.getTitolo());
							}
						}
					}
					case 5 -> {
						// Ricerca per autore
						System.out.print("Inserisci autore da cercare: ");
						String autoreRicerca = scanner.nextLine();
						List<Libro> libriAutore = archivio.ricercaPerAutore(autoreRicerca);
						if (libriAutore.isEmpty()) {
							System.out.println("Nessun libro trovato per l'autore " + autoreRicerca);
						} else {
							System.out.println("Libri trovati:");
							for (Libro l : libriAutore) {
								System.out.println(l.getIsbn() + " - " + l.getTitolo());
							}
						}

					}
					case 6 -> {
						// Aggiorna elemento
						System.out.print("Inserisci ISBN da aggiornare: ");
						String isbnUpdate = scanner.nextLine();
						try {
							ElementoCatalogo vecchioElemento = archivio.ricercaPerIsbn(isbnUpdate); //mi serve per exception
							System.out.print("Nuovo titolo: ");
							String nuovoTitolo = scanner.nextLine();
							System.out.print("Nuovo anno di pubblicazione: ");
							int nuovoAnno = scanner.nextInt();
							System.out.print("Nuovo numero di pagine: ");
							int nuovePagine = scanner.nextInt();
							scanner.nextLine(); // Clear buffer


							Libro nuovoLibro = new Libro(isbnUpdate, nuovoTitolo, nuovoAnno, nuovePagine, "Nuovo Autore", "Nuovo Genere");
							archivio.aggiornaElemento(isbnUpdate, nuovoLibro);
							System.out.println("Elemento aggiornato.");
						} catch (ElementoNonTrovatoException e) {
							System.out.println(e.getMessage());
						}
					}
					case 7 -> // Statistiche
						archivio.stampaStatistiche();
					case 0 -> {
						// Exit
						System.out.println("Uscita dal programma.");
						return;
					}
					default -> System.out.println("Scelta non valida.");

				}
			} catch (Exception e) {
				System.out.println("Si è verificato un errore: " + e.getMessage());
				scanner.nextLine();
			}
		}
	}
}
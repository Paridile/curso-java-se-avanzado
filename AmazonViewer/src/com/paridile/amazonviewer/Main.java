package com.paridile.amazonviewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.print.attribute.standard.PrinterMakeAndModel;

import com.paridile.amazonviewer.model.Book;
import com.paridile.amazonviewer.model.Chapter;
import com.paridile.amazonviewer.model.Magazine;
import com.paridile.amazonviewer.model.Movie;
import com.paridile.amazonviewer.model.Serie;
import com.paridile.makereport.Report;
import com.paridile.util.AmazonUtil;

/**
 * <h1>AmazonViewer</h1>
 * <p>
 * 		AmazonViewer es un programa que permite visualizar peliculas,
 * 		series con sus respectivos capitulos, libros y revistas. Te permite generar
 * 		reportes generales y con fecha y dia
 * </p>
 * <p>
 * 		Existen algunas reglas como que todos los elementos pueden
 * 		ser visuaizados o leidos a exepcion de las revistas, estas solo
 * 		pueden ser vistas a modo de exposiscion sin ser leidas.
 * </p>
 * @author pablo
 * @version 1.1
 * @since 2021
 * */
public class Main {

	public static void main(String[] args) {
		showMenu();
	}
	
	public static void showMenu() {
		int exit = 0;
		do {		
			System.out.println("BIENVENIDOS AMAZON VIEWER");			
			System.out.println("");
			System.out.println("Selecciona el n�mero de la opci�n deseada");
			System.out.println("1. Movies");
			System.out.println("2. Series");
			System.out.println("3. Books");
			System.out.println("4. Magazines");
			System.out.println("5. Report");
			System.out.println("6. Report Today");
			System.out.println("0. Exit");
			
			//Leer la respuesta del usuario
			int response = AmazonUtil.validateUserResponseMenu(0, 6);

			switch (response) {
				case 0:
					//salir
					exit = 0;
					break;
				case 1:
					showMovies();
					break;
				case 2:
					showSeries();
					break;
				case 3:
					showBooks();
					break;
				case 4:
					showMagazines();
					break;
				case 5:
					makeReport();
					exit = 1;
					break;
				case 6:
					makeReport(new Date());
					exit = 1;
					break;
	
				default:
					System.out.println();
					System.out.println("....�Selecciona una opci�n!!....");
					System.out.println();
					exit = 1;
					break;
			}
			
			
		}while(exit != 0);
	}
	
	static ArrayList<Movie> movies = new ArrayList<Movie>();
	public static void showMovies() {
		movies = Movie.makeMoviesList();
		int exit = 1;		
		do {
			System.out.println();
			System.out.println(":: MOVIES ::");
			System.out.println();
			
			AtomicInteger atomicInteger = new AtomicInteger(1);
			movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement() + ". " + m.getTitle() + " Visto: " + m.isViewed()));
			//movies.forEach(System.out::println);
	//		for (int i = 0; i < movies.size(); i++) { //1. Movie 1
//				System.out.println(i+1 + ". " + movies.get(i).getTitle() + " Visto: " + movies.get(i).isViewed());
			//}
			
			
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, movies.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
				break;
			}
			if (response > 0) {
				Movie movieSelected = movies.get(response-1);
				movieSelected.view();
			}
			
			
		}while(exit !=0);
		
	}
	static ArrayList<Serie> series = Serie.makeSeriesList();
	public static void showSeries() {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: SERIES ::");
			System.out.println();
			
			for (int i = 0; i < series.size(); i++) { //1. Serie 1
				System.out.println(i+1 + ". " + series.get(i).getTitle() + " Visto: " + series.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, series.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			if(response > 0) {
				showChapters(series.get(response-1).getChapters());
			}
			
			
		}while(exit !=0);
	}
	
	public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: CHAPTERS ::");
			System.out.println();
			
			
			for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
				System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, chaptersOfSerieSelected.size());
			
			if(response == 0) {
				exit = 0;
			}
			
			
			if(response > 0) {
				Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
				chapterSelected.view();
			}
		}while(exit !=0);
	}
	
	static ArrayList<Book> books= Book.makeBookList();
	public static void showBooks() {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: BOOKS ::");
			System.out.println();
			
			for (int i = 0; i < books.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + books.get(i).getTitle() + " Le�do: " + books.get(i).isReaded());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, books.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			if(response > 0) {
				Book bookSelected = books.get(response-1);
				bookSelected.view();
			}
			
		}while(exit !=0);
	}
	
	public static void showMagazines() {
		 ArrayList<Magazine> magazines = Magazine.makeMagazineList();
		int exit = 0;
		do {
			System.out.println();
			System.out.println(":: MAGAZINES ::");
			System.out.println();
			
			for (int i = 0; i < magazines.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + magazines.get(i).getTitle());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, 0);
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			
		}while(exit !=0);
	}
	
	public static void makeReport() {
		
		Report report = new Report();
		report.setNameFile("reporte");
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
		StringBuilder contentReport = new StringBuilder();
		
		movies.stream().filter(m -> m.getIsViewed())
		.forEach(m->contentReport.append(m.toString()+"\n"));
		
		//Predicate<Serie> seriesviewed = s -> s.getIsViewed();
		//series.stream().filter(seriesviewed);
				
		//Consumer<Serie> seriesEach = m->contentReport.append(m.toString()+"\n");
		
			
		Consumer<Serie> seriesEach = s -> {
			ArrayList<Chapter> chapters = s.getChapters();
			chapters.stream().filter(c -> c.getIsViewed()).
			forEach(c->contentReport.append(c.toString()+"\n"));;
		};
		series.stream().forEach(seriesEach);;
		
		
		books.stream().filter(b -> b.getIsReaded())
		.forEach(b->contentReport.append(b.toString()+"\n"));
		/*		
		for (Movie movie : movies) {
			if (movie.getIsViewed()) {
				contentReport += movie.toString() + "\n";
				
			}
		}
		/*
		for (Serie serie : series) {
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.getIsViewed()) {
					//contentReport += chapter.toString() + "\n";
					
				}
			}	
		}
		
		
		for (Book book : books) {
			if (book.getIsReaded()) {
				//contentReport += book.toString() + "\n";
				
			}
		}
		*/

		report.setContent(contentReport.toString());
		report.makeReport();
		System.out.println("Reporte Generado");
		System.out.println();
	}
	
	public static void makeReport(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date);
		Report report = new Report();
		
		report.setNameFile("reporte" + dateString);
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
				
		SimpleDateFormat dfNameDays = new SimpleDateFormat("yyyy-MM-dd");
		dateString = dfNameDays.format(date);
		//String contentReport = "Date: " + dateString + "\n\n\n";
		String dateConsulted[] = dateString.split(" ");
		String dateViewed[];
		
		StringBuilder contentReport = new StringBuilder();
		contentReport.append("Date: " + dateString + "\n\n\n");
						
		movies.stream().filter(m -> m.getIsViewed())
		.filter( m -> m.getDateViewed().split(" ")[0].equals(dateConsulted[0]))
		.forEach(m->contentReport.append(m.toString()+"\n"));
							
		Consumer<Serie> seriesEach = s -> {
			ArrayList<Chapter> chapters = s.getChapters();
			chapters.stream().filter(c -> c.getIsViewed()).
			forEach(c->contentReport.append(c.toString()+"\n"));;
		};
		
		series.stream()
		.filter( s -> s.getDateViewed().split(" ")[0].equals(dateConsulted[0]))
		.forEach(seriesEach);
		
		
		books.stream().filter(b -> b.getIsReaded())		
		.forEach(b->contentReport.append(b.toString()+"\n"));
		
		/*
		for (Movie movie : movies) {			
			dateViewed = movie.getDateViewed().split(" ");			
			if (movie.getIsViewed() && dateViewed[0].equals(dateConsulted[0])) {
				contentReport += movie.toString() + "\n";
				
			}
		}
		
		for (Serie serie : series) {
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.getIsViewed()) {
					contentReport += chapter.toString() + "\n";
					
				}
			}
		}
		
		for (Book book : books) {
			if (book.getIsReaded()) {
				contentReport += book.toString() + "\n";
				
			}
		}
		*/
		report.setContent(contentReport.toString());
		report.makeReport();
		
		System.out.println("Reporte Generado");
		System.out.println();
	}
	
}
















import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { Book } from "../entities/book"
import { Genre } from "../entities/genre"
import { Author } from "../entities/author"
import { BookService } from '../services/book.service';
import { GenreService } from '../services/genre.service';
import { AuthorService } from '../services/author.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-book.update',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './book.update.component.html',
  styleUrl: './book.update.component.css'
})
export class BookUpdateComponent {
  book: Book = new Book();
  genreList: Genre[] = [];
  authorList: Author[] = [];
  id: string;

  constructor(private bookService: BookService, 
              private router: Router, 
              private activateRoute: ActivatedRoute,
              private genreService: GenreService, 
              private authorService: AuthorService) {
    this.id = activateRoute.snapshot.params["id"];
  }

  async ngOnInit(){
    this.book = await this.bookService.getBook(this.id);
    this.genreList = await this.genreService.getGenres();
    this.authorList = await this.authorService.getAuthors();
    console.log(this.book)
  }

  async updateBook() {
    await this.bookService.updateBook(this.book);
    this.router.navigate(["/books"]);
  }
}

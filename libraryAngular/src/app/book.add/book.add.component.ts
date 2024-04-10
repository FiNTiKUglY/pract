import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { Book } from "../entities/book";
import { Genre } from "../entities/genre"
import { Author } from "../entities/author"
import { BookService } from '../services/book.service';
import { AuthorService } from '../services/author.service';
import { GenreService } from '../services/genre.service';
import { UUID } from "angular2-uuid";
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-book.add',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './book.add.component.html',
  styleUrl: './book.add.component.css'
})

export class BookAddComponent {
  book: Book = new Book();
  genreList: Genre[] = [];
  authorList: Author[] = [];

  constructor(private bookService: BookService,
              private genreService: GenreService, 
              private authorService: AuthorService,
              private router: Router) {}

  async ngOnInit(){
    this.genreList = await this.genreService.getGenres();
    this.authorList = await this.authorService.getAuthors();
  }

  async addBook() {
    this.book.id = UUID.UUID();
    await this.bookService.addBook(this.book);
    this.router.navigate(["/books"]);
  }
}

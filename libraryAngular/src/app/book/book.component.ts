import { Component } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../entities/book';
import { RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-book',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './book.component.html'
})

export class BookComponent {
  books: Book[] = [];

  constructor(private bookService: BookService) {}

  async ngOnInit(){
    this.books = await this.bookService.getBooks();
  }

  async removeBook(id: string) {
    await this.bookService.deleteBook(id)
    this.ngOnInit();
  }
}

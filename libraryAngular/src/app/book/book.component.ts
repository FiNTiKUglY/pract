import { Component } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../entities/book';

@Component({
  selector: 'app-book',
  standalone: true,
  imports: [],
  templateUrl: './book.component.html',
  styleUrl: './book.component.css'
})

export class BookComponent {
  books: Book[] = [];

  constructor(private bookService: BookService) {}

  async ngOnInit(){
    this.books = await this.bookService.getBooks();
  }
}

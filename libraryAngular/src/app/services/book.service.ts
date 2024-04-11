import { Injectable } from '@angular/core';
import axios from "axios"
import {Book} from "../entities/book"

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }

  async getBooks() {
    let value: Book[] = [];
    await axios.get(
        "http://localhost:8080/api/books"
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async getBook(id: string) {
    let value = new Book();
    await axios.get(
        "http://localhost:8080/api/books/" + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async deleteBook(id: string) {
    let value = new Book();
    await axios.delete(
        'http://localhost:8080/api/books/delete/' + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async addBook(book: Book) {
    let value = new Book();
    await axios.post(
        'http://localhost:8080/api/books/add',
        book
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async updateBook(book: Book) {
    let value = new Book();
    await axios.post(
        'http://localhost:8080/api/books/update/' + book.id,
        book
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }
}

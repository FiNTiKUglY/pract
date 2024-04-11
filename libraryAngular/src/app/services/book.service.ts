import { Injectable } from '@angular/core';
import axios from "axios"
import {Book} from "../entities/book"
import { ANGULAR_APP_API_URL } from "../contastants/env"

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }

  async getBooks() {
    let value: Book[] = [];
    await axios.get(
        ANGULAR_APP_API_URL + `/books`
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
        ANGULAR_APP_API_URL + `/books/${id}`
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
        ANGULAR_APP_API_URL + `/books/delete/${id}`
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
        ANGULAR_APP_API_URL + `/books/add`,
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
        ANGULAR_APP_API_URL + `/books/update/${book.id}`,
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

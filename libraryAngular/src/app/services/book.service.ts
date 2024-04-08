import { Injectable } from '@angular/core';
import axios from "axios"
import {Book} from "../entities/book"

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }

  async getBooks() {
    let response = await axios.get(
        "http://localhost:8080/api/books"
    )
    return response.data
  }

  async getBook(id: string) {
      let response = await axios.get(
          "http://localhost:8080/api/books/" + id
      )
      return response.data
  }

  async deleteBook(id: string) {
      let response = await axios.delete(
          'http://localhost:8080/api/books/delete/' + id
      )
      return response.data
  }

  async addBook(book: Book) {
      let response = await axios.post(
          'http://localhost:8080/api/books/add',
          book
      )
      return response.data
  }

  async updateBook(book: Book) {
      let response = await axios.post(
          'http://localhost:8080/api/books/update/' + book.id,
          book
      )
      return response.data
  }
}

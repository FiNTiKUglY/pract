import { Injectable } from '@angular/core';
import axios from "axios"


@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor() { }

  async getAuthors() {
      let response = await axios.get(
          "http://localhost:8080/api/authors"
      )
      return response.data
  }

  async getAuthor(id: string) {
      let response = await axios.get(
          "http://localhost:8080/api/authors/" + id
      )
      return response.data
  }

  async deleteAuthor(id: string) {
      let response = await axios.delete(
          'http://localhost:8080/api/authors/delete/' + id
      )
      return response.data
  }

  async addAuthor(author: Author) {
      let response = await axios.post(
          'http://localhost:8080/api/authors/add',
          author
      )
      return response.data
  }

  async updateAuthor(author: Author) {
      let response = await axios.post(
          'http://localhost:8080/api/authors/update/' + author.id,
          author
      )
      return response.data
  }
}

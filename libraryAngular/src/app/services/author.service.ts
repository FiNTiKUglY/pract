import { Injectable } from '@angular/core';
import axios from "axios"
import { Author } from "../entities/author"


@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor() { }

  async getAuthors() {
    let value: Author[] = [];
    await axios.get(
        "http://localhost:8080/api/authors"
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async getAuthor(id: string) {
    let value = new Author();
    await axios.get(
        "http://localhost:8080/api/authors/" + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async deleteAuthor(id: string) {
    let value = new Author();
    await axios.delete(
        'http://localhost:8080/api/authors/delete/' + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async addAuthor(author: Author) {
    let value = new Author();
    await axios.post(
        'http://localhost:8080/api/authors/add',
        author
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async updateAuthor(author: Author) {
    let value = new Author();
    await axios.post(
        'http://localhost:8080/api/authors/update/' + author.id,
        author
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

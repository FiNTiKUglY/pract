import { Injectable } from '@angular/core';
import axios from "axios"
import { Author } from "../entities/author"
import { ANGULAR_APP_API_URL } from "../contastants/env"


@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor() { }

  async getAuthors() {
    let value: Author[] = [];
    await axios.get(
        ANGULAR_APP_API_URL + `/authors`
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
        ANGULAR_APP_API_URL + `/authors/${id}`
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
        ANGULAR_APP_API_URL + `/authors/delete/${id}`
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
        ANGULAR_APP_API_URL + `/authors/add`,
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
        ANGULAR_APP_API_URL + `/authors/update/${author.id}`,
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

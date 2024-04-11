import { Injectable } from '@angular/core';
import axios from "axios"
import {Genre} from "../entities/genre"

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  async getGenres() {
    let value: Genre[] = [];
    await axios.get(
        "http://localhost:8080/api/genres"
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async getGenre(id: string) {
    let value = new Genre();
    await axios.get(
        "http://localhost:8080/api/genres/" + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async deleteGenre(id: string) {
    let value = new Genre();
    await axios.delete(
        'http://localhost:8080/api/genres/delete/' + id
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async addGenre(genre: Genre) {
    let value = new Genre();
    await axios.post(
        'http://localhost:8080/api/genres/add',
        genre
    )
    .then((response) => {
        value = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return value;
  }

  async updateGenre(genre: Genre) {
    let value = new Genre();
    await axios.post(
        'http://localhost:8080/api/genres/update/' + genre.id,
        genre
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

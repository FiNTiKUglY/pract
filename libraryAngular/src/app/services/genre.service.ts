import { Injectable } from '@angular/core';
import axios from "axios"
import {Genre} from "../entities/genre"
import { ANGULAR_APP_API_URL } from "../contastants/env"

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  async getGenres() {
    let value: Genre[] = [];
    await axios.get(
        ANGULAR_APP_API_URL + `/genres`
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
        ANGULAR_APP_API_URL + `/genres/${id}`
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
        ANGULAR_APP_API_URL + `/genres/delete/${id}`
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
        ANGULAR_APP_API_URL + `/genres/add`,
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
        ANGULAR_APP_API_URL + `/genres/update/${genre.id}`,
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

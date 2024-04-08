import { Injectable } from '@angular/core';
import axios from "axios"
import {Genre} from "../entities/genre"

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor() { }

  async getGenres() {
    let response = await axios.get(
        "http://localhost:8080/api/genres"
    )
    return response.data
  }

  async getGenre(id: string) {
      let response = await axios.get(
          "http://localhost:8080/api/genres/" + id
      )
      return response.data
  }

  async deleteGenre(id: string) {
      let response = await axios.delete(
          'http://localhost:8080/api/genres/delete/' + id
      )
      return response.data
  }

  async addGenre(genre: Genre) {
      let response = await axios.post(
          'http://localhost:8080/api/genres/add',
          genre
      )
      return response.data
  }

  async updateGenre(genre: Genre) {
      let response = await axios.post(
          'http://localhost:8080/api/genres/update/' + genre.id,
          genre
      )
      return response.data
  }
}

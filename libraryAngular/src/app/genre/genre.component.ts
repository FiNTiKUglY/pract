import { Component } from '@angular/core';
import { GenreService } from '../services/genre.service';
import { Genre } from '../entities/genre';
import { RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-genre',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './genre.component.html'
})
export class GenreComponent {
  genres: Genre[] = [];

  constructor(private genreService: GenreService) {}

  async ngOnInit(){
    this.genres = await this.genreService.getGenres();
  }
  
  async removeGenre(id: string) {
    await this.genreService.deleteGenre(id)
    this.ngOnInit();
  }
}

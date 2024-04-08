import { Component } from '@angular/core';
import { GenreService } from '../services/genre.service';
import { Genre } from '../entities/genre';

@Component({
  selector: 'app-genre',
  standalone: true,
  imports: [],
  templateUrl: './genre.component.html',
  styleUrl: './genre.component.css'
})
export class GenreComponent {
  genres: Genre[] = [];

  constructor(private genreService: GenreService) {}

  async ngOnInit(){
    this.genres = await this.genreService.getGenres();
  }
}

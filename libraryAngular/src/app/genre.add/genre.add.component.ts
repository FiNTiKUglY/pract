import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { Genre } from "../entities/genre"
import { GenreService } from '../services/genre.service';
import { UUID } from "angular2-uuid";

@Component({
  selector: 'app-genre.add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './genre.add.component.html',
  styleUrl: './genre.add.component.css'
})
export class GenreAddComponent {
  genre: Genre = new Genre();

  constructor(private genreService: GenreService, private router: Router) {}

  async addGenre() {
    this.genre.id = UUID.UUID();
    await this.genreService.addGenre(this.genre);
    this.router.navigate(["/genres"]);
  }
}

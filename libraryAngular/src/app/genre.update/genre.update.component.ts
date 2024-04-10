import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { Genre } from "../entities/genre"
import { GenreService } from '../services/genre.service';

@Component({
  selector: 'app-genre.update',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './genre.update.component.html'
})
export class GenreUpdateComponent {
  genre: Genre = new Genre();
  id: string;

  constructor(private genreService: GenreService, private router: Router, private activateRoute: ActivatedRoute) {
    this.id = activateRoute.snapshot.params["id"];
  }

  async ngOnInit(){
    this.genre = await this.genreService.getGenre(this.id);
  }

  async updateGenre() {
    await this.genreService.updateGenre(this.genre);
    this.router.navigate(["/genres"]);
  }
}

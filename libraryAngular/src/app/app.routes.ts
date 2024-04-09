import { BookComponent }   from "./book/book.component";
import { BookAddComponent }   from "./book.add/book.add.component";
import { BookUpdateComponent }   from "./book.update/book.update.component";
import { GenreComponent }   from "./genre/genre.component";
import { GenreAddComponent }   from "./genre.add/genre.add.component";
import { GenreUpdateComponent }   from "./genre.update/genre.update.component";

import { Routes } from '@angular/router';

export const routes: Routes = [
    { path: "books", component: BookComponent},
    { path: "genres", component: GenreComponent},
    { path: "books/add", component: BookAddComponent},
    { path: "books/update/:id", component: BookUpdateComponent},
    { path: "genres/add", component: GenreAddComponent},
    { path: "genres/update/:id", component: GenreUpdateComponent}
];

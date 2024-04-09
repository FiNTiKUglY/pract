import { BookComponent }   from "./book/book.component";
import { BookAddComponent }   from "./book.add/book.add.component";
import { BookUpdateComponent }   from "./book.update/book.update.component";
import { GenreComponent }   from "./genre/genre.component";
import { GenreAddComponent }   from "./genre.add/genre.add.component";
import { GenreUpdateComponent }   from "./genre.update/genre.update.component";

import { Routes } from '@angular/router';

const bookRoutes: Routes = [
    { path: "add", component: BookAddComponent},
    { path: "update/:id", component: BookUpdateComponent}
];

const genreRoutes: Routes = [
    { path: "add", component: GenreAddComponent},
    { path: "update/:id", component: GenreUpdateComponent}
];

export const routes: Routes = [
    { path: "books", component: BookComponent, children: bookRoutes},
    { path: "genres", component: GenreComponent, children: genreRoutes},
];

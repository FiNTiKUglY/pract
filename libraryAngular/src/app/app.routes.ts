import { HomeComponent } from "./home.component";
import { BookComponent }   from "./book.component";
import { BookAddComponent }   from "./book.add.component";
import { BookUpdateComponent }   from "./book.update.component";
import { GenreComponent }   from "./genre.component";
import { GenreAddComponent }   from "./genre.add.component";
import { GenreUpdateComponent }   from "./genre.update.component";

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
    { path: "", component: HomeComponent}
];

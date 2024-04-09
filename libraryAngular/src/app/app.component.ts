import { Component} from "@angular/core";
import { RouterOutlet, RouterLink } from "@angular/router";
 
@Component({
    selector: "app-root",
    standalone: true,
    imports: [RouterOutlet, RouterLink],
    template: `<div>
                  <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                          <a class="nav-link" routerLink="/books">Книги</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" routerLink="/genres">Жанры</a>
                        </li>
                    </ul>
                </nav>
                  <router-outlet></router-outlet>
                </div>`,
})
export class AppComponent {}
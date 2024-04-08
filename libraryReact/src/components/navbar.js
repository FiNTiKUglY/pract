import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <Link class="nav-link" to={`/books`}>Книги</Link>
                </li>
                <li class="nav-item">
                    <Link class="nav-link" to={`/genres`}>Жанры</Link>
                </li>
            </ul>
        </nav>
    );
}
import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <ul className="navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to={`/books`}>Книги</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to={`/genres`}>Жанры</Link>
                </li>
            </ul>
        </nav>
    );
}
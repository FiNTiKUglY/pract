import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to={`/signin`}>Войти</Link>
                </li>
                <li>
                    <Link to={`/signup`}>Регистрация</Link>
                </li>
            </ul>
        </nav>
    );
}
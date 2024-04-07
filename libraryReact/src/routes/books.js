import React, { useState, useEffect } from 'react';
import { getBooks, deleteBook, getBook, addBook, updateBook } from "../api/bookService"
import { getAuthors } from "../api/authorService"
import { getGenres } from "../api/genreService"
import { Link, useLoaderData, useNavigate } from "react-router-dom";
import Select  from "react-select";

export async function loader({ params }) {
    const book = await getBook(params.bookId);
    return { book };
}

export default function Books() {
    const [books, setBooks] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadBooks()
    }, [reload]);

    return (
        <section >
            <div>
                <h2>Книги</h2>
                
                <table className="table table-striped table-hover">   
                    <thead>
                        <tr>
                            <th>
                                Название
                            </th>
                            <th>
                                Автор
                            </th>
                            <th>
                                Описание
                            </th>
                            <th>
                                Цена
                            </th>
                            <th>
                                Ссылка загрузки
                            </th>
                            <th>
                                Ссылка загрузки
                            </th>
                            <th>
                                Жанры
                            </th>
                            <th>
                                <Link to="add/" className="btn btn-success">Добавить</Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {books.map(book =>
                            <tr key={book.id}>
                                <td>
                                    {book.title}
                                </td>
                                <td>
                                    {book.author.surname} {book.author.name}
                                </td>
                                <td>
                                    {book.shortDescription}
                                </td>
                                <td>
                                    {book.cost}
                                </td>
                                <td>
                                    {book.downloadLink}
                                </td>
                                <td>
                                    {book.imageLink}
                                </td>
                                <td>
                                    {book.genres.map(genre=>
                                        genre.name
                                    )}
                                </td>
                                <td>
                                    <Link to={`update/${book.id}`} className="btn btn-info">Изменить</Link>
                                    |
                                    <button 
                                        onClick={() => removeBook(book.id)} 
                                        value={book.id} 
                                        className="btn btn-danger">
                                            Удалить
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );

    async function loadBooks() {
        const books = await getBooks()
        setBooks(books)
    }

    async function removeBook(id) {
        await deleteBook(id)
        setReload(!reload)
    }
}

export function BooksAdd() {
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [shortDescription, setShortDescription] = useState('');
    const [cost, setCost] = useState('');
    const [downloadLink, setDownloadLink] = useState('');
    const [imageLink, setImageLink] = useState('');
    const [author, setAuthor] = useState();
    const [genres, setGenres] = useState();

    let authorsList = [];
    let genresList = [];

    loadAuthors();
    loadGenres();

    return (
        <section class="form-container">
            <div class="form">
                <input
                    placeholder="Название"
                    class="form-control"
                    type="text"
                    onChange={(e) => setTitle(e.target.value)}
                />
                <Select
                    options={authorsList}
                    placeholder="Автор"
                    value={author}
                    onChange={handleSelectAuthor}
                    isSearchable={true}
                />
                <textarea
                    placeholder="Описание"
                    class="form-control"
                    type="text"
                    onChange={(e) => setShortDescription(e.target.value)}
                />
                <Select
                    options={genresList}
                    placeholder="Жанры"
                    value={genres}
                    onChange={handleSelectGenre}
                    isSearchable={true}
                    isMulti
                />
                <input
                    placeholder="Цена"
                    class="form-control"
                    type="number"
                    onChange={(e) => setCost(e.target.value)}
                />
                <input
                    placeholder="Ссылка загрузки"
                    class="form-control"
                    type="text"
                    onChange={(e) => setDownloadLink(e.target.value)}
                />
                <input
                    placeholder="Ссылка изображения"
                    class="form-control"
                    type="text"
                    onChange={(e) => setImageLink(e.target.value)}
                />
                <button
                    onClick={addBookClick}>
                    Добавить
                </button>
            </div>
        </section>
    );

    function handleSelectAuthor(data) {
        setAuthor(data);
    }

    function handleSelectGenre(data) {
        setGenres(data);
    }

    async function loadAuthors() {
        const authors = await getAuthors()
        authors.map(author =>
            authorsList.push({ value: author, label: `${author.surname} ${author.name}`})    
        )
    }

    async function loadGenres() {
        const genres = await getGenres()
        genres.map(genre =>
            genresList.push({ value: genre, label: genre.name})    
        )
    }

    async function addBookClick() {
        let book = {}
        book.id = crypto.randomUUID();
        book.title = title
        book.shortDescription = shortDescription
        book.cost = cost
        book.author = author.value
        book.genres = genres.map(genre => genre.value)
        book.downloadLink = downloadLink
        book.imageLink = imageLink
        console.log(book)
        await addBook(book)
        navigate('/books')
    }
}

export function BooksUpdate() {
    const navigate = useNavigate();

    const { book } = useLoaderData();
    const [title, setTitle] = useState('');
    const [shortDescription, setShortDescription] = useState('');
    const [cost, setCost] = useState('');
    const [downloadLink, setDownloadLink] = useState('');
    const [imageLink, setImageLink] = useState('');
    const [author, setAuthor] = useState();
    const [genres, setGenres] = useState();

    let authorsList = [];
    let genresList = [];

    loadAuthors();
    loadGenres();

    return (
        <section class="form-container">
            <div class="form">
                <input
                    placeholder="Название"
                    class="form-control"
                    type="text"
                    onChange={(e) => setTitle(e.target.value)}
                />
                <Select
                    options={authorsList}
                    placeholder="Автор"
                    onChange={handleSelectAuthor}
                    isSearchable={true}
                />
                <textarea
                    placeholder="Описание"
                    class="form-control"
                    type="text"
                    onChange={(e) => setShortDescription(e.target.value)}
                />
                <Select
                    options={genresList}
                    placeholder="Жанры"
                    onChange={handleSelectGenre}
                    isSearchable={true}
                    isMulti
                />
                <input
                    placeholder="Цена"
                    class="form-control"
                    type="number"
                    onChange={(e) => setCost(e.target.value)}
                />
                <input
                    placeholder="Ссылка загрузки"
                    class="form-control"
                    type="text"
                    onChange={(e) => setDownloadLink(e.target.value)}
                />
                <input
                    placeholder="Ссылка изображения"
                    class="form-control"
                    type="text"
                    onChange={(e) => setImageLink(e.target.value)}
                />
                <button
                    onClick={updateBookClick}>
                    Изменить
                </button>
            </div>
        </section>
    );

    function handleSelectAuthor(data) {
        setAuthor(data);
    }

    function handleSelectGenre(data) {
        setGenres(data);
    }

    async function loadAuthors() {
        const authors = await getAuthors()
        authors.map(author =>
            authorsList.push({ value: author, label: `${author.surname} ${author.name}`})    
        )
    }

    async function loadGenres() {
        const genres = await getGenres()
        genres.map(genre =>
            genresList.push({ value: genre, label: genre.name})    
        )
    }

    async function updateBookClick() {
        book.title = title
        book.shortDescription = shortDescription
        book.cost = cost
        book.author = author.value
        book.genres = genres.map(genre => genre.value)
        book.downloadLink = downloadLink
        book.imageLink = imageLink
        await updateBook(book)
        navigate('/books')
    }
}

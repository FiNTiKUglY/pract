import React, { useEffect, useState } from 'react';
import { getAuthors } from "../api/authorService"
import { getGenres } from "../api/genreService"
import Select  from "react-select";
import { useLoaderData, useNavigate } from "react-router-dom";
import { getBook, updateBook } from "../api/bookService"

export async function loader({ params }) {
    const book = await getBook(params.bookId);
    return { book };
}

export default function BooksUpdate() {
    const navigate = useNavigate();

    const { book } = useLoaderData();
    const [title, setTitle] = useState(book.title);
    const [shortDescription, setShortDescription] = useState(book.shortDescription);
    const [cost, setCost] = useState(book.cost);
    const [downloadLink, setDownloadLink] = useState(book.downloadLink);
    const [imageLink, setImageLink] = useState(book.imageLink);
    const [author, setAuthor] = useState({value: book.author, label: `${book.author.surname} ${book.author.name}`});
    const [genres, setGenres] = useState(book.genres.map(genre => ({value: genre, label: `${genre.name}`})));
    
    let authorsList = [];
    let genresList = [];

    loadAuthors();
    loadGenres();

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <Select
                    options={authorsList}
                    defaultValue={ author }
                    placeholder="Автор"
                    onChange={handleSelectAuthor}
                    isSearchable={true}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    value={shortDescription}
                    onChange={(e) => setShortDescription(e.target.value)}
                />
                <Select
                    options={genresList}
                    defaultValue={genres}
                    placeholder="Жанры"
                    onChange={handleSelectGenre}
                    isSearchable={true}
                    isMulti
                />
                <input
                    placeholder="Цена"
                    className="form-control"
                    type="number"
                    value={cost}
                    onChange={(e) => setCost(e.target.value)}
                />
                <input
                    placeholder="Ссылка загрузки"
                    className="form-control"
                    type="text"
                    value={downloadLink}
                    onChange={(e) => setDownloadLink(e.target.value)}
                />
                <input
                    placeholder="Ссылка изображения"
                    className="form-control"
                    type="text"
                    value={imageLink}
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
